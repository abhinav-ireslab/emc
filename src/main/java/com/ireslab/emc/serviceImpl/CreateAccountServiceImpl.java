/**
 * 
 */
package com.ireslab.emc.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import com.ireslab.emc.model.AccountDetails;
import com.ireslab.emc.model.ClientCredentials;
import com.ireslab.emc.model.ClientProfile;
import com.ireslab.emc.model.ClientProfileResponse;
import com.ireslab.emc.model.ClientRegistrationRequest;
import com.ireslab.emc.model.ClientRegistrationResponse;
import com.ireslab.emc.model.Error;
import com.ireslab.emc.service.ClientUserInfoService;
import com.ireslab.emc.service.CreateAcountService;
import com.ireslab.emc.util.Status;

/**
 * @author Akhilesh
 *
 */

public class CreateAccountServiceImpl implements CreateAcountService {
	
	

	@Autowired
	private ModelMapper modelMapper;
	

	@Autowired
	private ClientUserInfoService clientUserInfoService;

	@Override
	public Map<String, String> addAccount(AccountDetails accountDetails) {

		//modelMapper = new ModelMapper();
		//ClientProfile accountEntity = modelMapper.map(accountDetails, ClientProfile.class);
		
	

		//AccountDetail accountByUserName = accountRepository.findByUsername(accountDetails.getUsername());
		//AccountDetail accountByEmail = accountRepository.findByEmail(accountDetails.getEmail());
		
		ClientProfile accountByEmail = clientUserInfoService.getClientByEmailId(accountDetails.getEmail());
		ClientProfile accountByUserName = clientUserInfoService.getClientByUserName(accountDetails.getUsername());

		Map<String, String> map = new HashMap<>();

		if (accountByUserName.getClientCorrelationId() == null && accountByEmail.getClientCorrelationId() == null) {

			// TODO to save client/s details in electra_schema class
			ClientProfile clientProfile = new ClientProfile();
			clientProfile.setClientCorrelationId(accountDetails.getClientCorrelationId());
			clientProfile.setClientName(accountDetails.getCompany_name());
			clientProfile.setContactNumber1(accountDetails.getContactNumber());
			clientProfile.setDescription(accountDetails.getDescription());
			clientProfile.setEmailAddress(accountDetails.getEmail());
			clientProfile.setClientStatus(accountDetails.getStatus());
			clientProfile.setUserName(accountDetails.getUsername());
			clientProfile.setPassword(accountDetails.getPassword());
			clientProfile.setAccount_type(accountDetails.getAccount_type());
			//clientProfile.setDescription(description);
			
			clientProfile.setTestnetAccount(true);

			List<ClientProfile> clientProfiles = new ArrayList<>();
			clientProfiles.add(clientProfile);

			ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest();
			clientRegistrationRequest.setClientProfile(clientProfiles);

			ClientRegistrationResponse clientRegistrationResponse = clientUserInfoService
					.createClient(clientRegistrationRequest);
			List<Error> errors = clientRegistrationResponse.getErrors();

			if (errors.isEmpty()) {
				//accountRepository.save(accountEntity);
				// status = true;

				map.put("message", "Client created successfully");
				map.put("success", "true");
			} else {

				if (errors.get(0).getCode() == 1303) {

					map.put("message", "Client creation faild due to stellar account creation.");
					map.put("success", "false");
				} else {

				}
				// status = false;
			}

		} else {
			String message = "message : ";

			if (accountByUserName.getClientCorrelationId() != null || accountByEmail.getClientCorrelationId() != null) {

				if (accountByUserName.getClientCorrelationId() != null) {

					map.put("code", "9002");
					if (accountByUserName.getClientStatus().equalsIgnoreCase(Status.TERMINATED.name())) {
						message = "Account with given username is Terminated";

					} else if (accountByUserName.getClientStatus().equalsIgnoreCase(Status.SUSPENDED.name())) {
						message = "Duplicate user name entered.";
					}
					else {
						message = "Duplicate user name entered.";
					}

				} else if (accountByEmail.getClientCorrelationId() != null) {
					map.put("code", "9001");
					message = "Duplicate email address entered.";
				}

				map.put("message", "Client creation faild[" + message + "].");
				map.put("success", "false");
				// status = false;
			}
		}

		return map;
	}

	@Override
	public List<AccountDetails> getAllAccountDetails() {
		List<ClientProfile> accountsEntity = new ArrayList<>();
		//accountRepository.findAll().forEach(accountsEntity::add);
		ClientRegistrationResponse clientRegistrationResponse = clientUserInfoService.getAllClientData();
		clientRegistrationResponse.getClients().forEach(accountsEntity::add);

		java.lang.reflect.Type targetListType = new TypeToken<List<ClientProfile>>() {
		}.getType();
		List<AccountDetails> accountDetails = modelMapper.map(accountsEntity, targetListType);

		return accountDetails;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ireslab.emc.service.CreateAcountService#updateAccount(com.ireslab.emc.
	 * model.AccountDetails)
	 */
	@Override
	public ClientRegistrationResponse updateAccount(AccountDetails accountDetails) {

		//AccountDetail account = accountRepository.findByClientCorrelationId(accountDetails.getClientCorrelationId());
		
		ClientProfile account = clientUserInfoService.getClientByCorrelationId(accountDetails.getClientCorrelationId());
		/*
		 * modelMapper = new ModelMapper(); Account accountEntity =
		 * modelMapper.map(accountDetails, Account.class);
		 */
		account.setCompanyAddress(accountDetails.getCompanyAddress());
		account.setCompanyCity(accountDetails.getCompanyCity());
		account.setCompanyContact(accountDetails.getCompanyContact());
		account.setCompanyCountry(accountDetails.getCompanyCountry());
		account.setCompanyFax(accountDetails.getCompanyFax());
		account.setCompanyPinCode(accountDetails.getCompanyPinCode());
		account.setCompanyState(accountDetails.getCompanyState());
		account.setDescription(accountDetails.getDescription());
		
		if(accountDetails.getGstNumber()!=null) {
			account.setGstNumber(accountDetails.getGstNumber());
		}
		

		// TODO update client data to electra database.
		// ClientUserInfoServiceImpl.updateClient(clientRegistrationRequest);
		System.out.println("updating user..!!");
		//accountRepository.save(account);
		ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest();
		List<ClientProfile> profileList = new ArrayList<>();
		profileList.add(account);
		clientRegistrationRequest.setClientProfile(profileList);
		
		ClientRegistrationResponse registrationResponse = clientUserInfoService
				.updateClientDetail((ClientRegistrationRequest) clientRegistrationRequest.setClientId(account.getClientCorrelationId()));
		//System.out.println("user updated..!!");
		List<AccountDetails> clientProfiles = new ArrayList<>();
		clientProfiles.add(accountDetails);

		//ClientRegistrationResponse clientRegistrationResponse = new ClientRegistrationResponse();
		registrationResponse.setCode(7000);
		registrationResponse.setMessage("client updated successfully");
		registrationResponse.setAccountDetails(clientProfiles);

		return registrationResponse;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ireslab.emc.service.CreateAcountService#getAccount(java.lang.String)
	 */
	@Override
	public AccountDetails getAccount(String client_correlation_id) {
		// System.out.println("Getting Client Account details for client_correlation_id
		// :"+client_correlation_id);
		//AccountDetail accountEntity = accountRepository.findByClientCorrelationId(client_correlation_id);
		ClientProfile accountEntity = clientUserInfoService.getClientByCorrelationId(client_correlation_id);
		java.lang.reflect.Type targetListType = new TypeToken<AccountDetails>() {
		}.getType();
		AccountDetails accountDetail = modelMapper.map(accountEntity, targetListType);

		// TODO get client security configuration
		// System.out.println("Getting ClientCredential details for
		// client_correlation_id :"+client_correlation_id);

		ClientCredentials clientCredential = clientUserInfoService.getClientCredential(client_correlation_id);
		/*if("ACTIVE".equals(clientCredential.getStatus())) {
			
			accountDetail.setClientCredentialActivated(true);
		}else {
			accountDetail.setClientCredentialActivated(false);
		}*/
		// System.out.println("Setting ClientCredential details for
		// client_correlation_id for response :"+client_correlation_id);
		accountDetail.setClientApiKey(clientCredential.getClientApiKey());
		accountDetail.setClientApiSecret(clientCredential.getClientApiSecret());
		// System.out.println("ClientCredential details for response have been set for
		// client_correlation_id :"+client_correlation_id);

		// System.out.println("Getting Client details for client_correlation_id
		// :"+client_correlation_id);
		ClientProfileResponse clientDetails = clientUserInfoService.clientDetails(client_correlation_id);

		accountDetail.setIssuingAccountPublicKey(clientDetails.getIssuingAccountPublicKey());
		accountDetail.setBaseTxnAccountPublicKey(clientDetails.getBaseTxnAccountPublicKey());

		return accountDetail;
	}

}
