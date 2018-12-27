/**
 * 
 */
package com.ireslab.emc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.emc.model.AccountDetails;
import com.ireslab.emc.model.ClientProfile;
import com.ireslab.emc.model.ClientRegistrationRequest;
import com.ireslab.emc.model.ClientRegistrationResponse;
import com.ireslab.emc.properties.EmcProperties;
import com.ireslab.emc.service.ClientUserInfoService;
import com.ireslab.emc.service.CreateAcountService;
import com.ireslab.emc.serviceImpl.EmailServiceImpl;
import com.ireslab.emc.util.EMCUtility;

/**
 * Class used to create Client's and their credentials.
 * 
 * @author Akhilesh
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(value = "/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
public class CreateAccountController {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CreateAccountController.class);

	@Autowired
	public EmcProperties emcProperties;



	@Autowired
	private ObjectWriter objectWriter;

	@Autowired
	private CreateAcountService acountService;
	
	@Autowired
	private ClientUserInfoService clientUserInfoService;
	@Autowired
	private EmailServiceImpl emailServiceImpl;

	@RequestMapping(method = RequestMethod.POST, value = "addAccount")
	public Map<String, String> addAccount(HttpServletRequest req, @RequestBody AccountDetails accountDetails)
			throws JsonProcessingException {

		log.info("Client registration request received " + objectWriter.writeValueAsString(accountDetails));

		String newPassword = EMCUtility.getInstance().createPaswordChars();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		accountDetails.setPassword(bCryptPasswordEncoder.encode(newPassword));
		String client_correlation_id = EMCUtility.geUUID();

		accountDetails.setClientCorrelationId(client_correlation_id);
		Map<String, String> addAccount = acountService.addAccount(accountDetails);
		//System.out.println("ACCOUNT CREATED :" + addAccount);
		accountDetails.setPassword(newPassword);

		HashMap<String, String> map = new HashMap<>();
		if (addAccount.get("success").equals("true")) {

			//AccountDetail account = clientRepo.findByEmail(accountDetails.getEmail());
			ClientProfile account = clientUserInfoService.getClientByEmailId(accountDetails.getEmail());
			emailServiceImpl.sendEmail(accountDetails.getEmail(), "Welcome To Electra",
					createEmailContent(accountDetails, req, account));
			map.put("success", "true");
			map.put("client_correlation_id", client_correlation_id);
			map.put("code", "9000");

		} else {

			map.put("success", "false");
			map.put("message", addAccount.get("message"));
			map.put("code", addAccount.get("code"));
		}

		// TODO update values in client table
		return map;
	}

	@RequestMapping("/getAllClient")
	public List<AccountDetails> getAllAccounts() {
		return acountService.getAllAccountDetails();
	}

	@RequestMapping("/getaccount")
	public AccountDetails getAccount(
			@RequestParam(name = "clientCorrelationId", required = true) String clientCorrelationId) {
		log.info("Client getaccount request received for clientCorrelationId :" + clientCorrelationId);
		AccountDetails account = acountService.getAccount(clientCorrelationId);
		try {
			log.debug("getaccount response received :" + objectWriter.writeValueAsString(account));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// log.info("Sending getaccount response received for clientCorrelationId " +
		// account);
		return account;
	}

	/**
	 * API updates details for a particular user account
	 * 
	 * @return
	 * 
	 * @throws JsonProcessingException
	 */
	// @RequestMapping(method = RequestMethod.POST, value =
	// "clientupdate/{client_correlation_id}")
	@RequestMapping(value = "clientupdate", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientRegistrationResponse> clientUpdate(@RequestBody AccountDetails accountDetails)
			throws JsonProcessingException {

		log.info("Client update request received " + objectWriter.writeValueAsString(accountDetails));
		ClientRegistrationResponse clientRegistrationResponse = acountService.updateAccount(accountDetails);

		return new ResponseEntity<>(clientRegistrationResponse, HttpStatus.OK);
	}

	private String createEmailContent(AccountDetails accountDetails, HttpServletRequest req, ClientProfile account) {

		log.info("Generate random 36-character string token for reset password");
		String vToken = UUID.randomUUID().toString();
		account.setResetToken(vToken);

		// Save token to database
		log.info("Saving token to database");
		//clientRepo.save(account);
		
		
		ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest();
		List<ClientProfile> profileList = new ArrayList<>();
		profileList.add(account);
		clientRegistrationRequest.setClientProfile(profileList);
		try {
			log.info("Client update detail request received " + objectWriter.writeValueAsString(profileList));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ClientRegistrationResponse registrationResponse = clientUserInfoService
				.updateClientDetail((ClientRegistrationRequest) clientRegistrationRequest.setClientId(account.getClientCorrelationId()));
		
		
		String password_rest_link = emcProperties.baseUrl + String.format(emcProperties.passwordResetPage, vToken);
		//System.out.println(password_rest_link);

		String emailContent = "<!DOCTYPE html><html lang=\"da\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><meta charset=\"utf-8\" /><link href=\"https://fonts.googleapis.com/css?family=Open+Sans\" rel=\"stylesheet\"></head><body style=\"font-family: 'Open Sans', sans-serif;\"><table style=\"color: #080808;\" border=\"0\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#c6c1cc\"><tbody><tr style=\"background:#c1c4cc;border:none\"><td style=\"padding-left:30px;padding-top:15px;padding-bottom:15px\"><img style=\"hight:auto; width:70px;\" src='http://ec2-13-250-226-42.ap-southeast-1.compute.amazonaws.com:4200/assets/images/email-logo-electra.png' alt=\"Electra\" style=\"max-height: 70px;max-width: 250px;\"></td><td style=\"color: #fff;padding-right:30px;font-size: 10px;padding-top: 15px; padding-bottom: 15px; text-align: right;\"></td></tr><tr><td colspan=\"2\" style=\"font-size:10px;color:#393744;padding-left:30px;padding-right:30px;padding-top:20px;\">\n"
				+ "        Welcome: " + accountDetails.getCompany_name() + "<br>\n <br>\n"
				+ "        Below is your account details.<br>\n " + "        Mail: " + accountDetails.getEmail()
				+ "<br>\n" + "        Contact No: " + accountDetails.getContactNumber() + "<br>\n"
				+ "        UserID: " + accountDetails.getUsername() + "<br>\n" + "        Company Name: "
				+ accountDetails.getCompany_name() + "<br>\n" + "    </td></tr>\n"
				+ "    <tr><td colspan=\"2\" style=\"font-size:10px;color:#393744;padding-left:30px;padding-right:30px;padding-bottom:0px;padding-top:5px;\"><p>You have been added by Electra as a new user. Please provide other details to complete your signup process.</p></td></tr><tr><td colspan=\"2\" style=\"font-size: 10px;color: #393744;padding-left:30px;padding-right:30px;padding-bottom:5px;padding-top:5px;\">"
				+ "<a href='" + password_rest_link
				+ "'><div style=\\\"cursor:pointer; width: 50%;border:none;background-color: #DC7633;font-size: 17px;color: #fdfdfc;padding:10px\\\">Configure your password.</div></a>"
				+ "</td></tr><tr><td colspan=\"2\" style=\"font-size:10px;color:#393744;padding-left:30px;padding-right:30px;padding-bottom:5px;padding-top:18px;\">Once you have configure your account, you will be able to easily manage your company dashboard.</td>"
				+ "</tr><tr><td colspan=\"2\" style=\"font-size: 10px;color: #393744;padding-left:30px;padding-right:30px;padding-top:10px; font-weight:400\">Electra Team<br><br>\n"
				+ "\n" + "</td></tr></tbody></table></body></html>\n" + "";

		return emailContent;
	}
}
