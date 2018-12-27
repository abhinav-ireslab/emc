package com.ireslab.emc.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.ireslab.emc.dto.ExchangeDto;
import com.ireslab.emc.model.ClientCredentials;

import com.ireslab.emc.model.AccountDetailsResponse;
import com.ireslab.emc.model.ClentAgentInvitationRequest;
import com.ireslab.emc.model.ClentAgentInvitationResponse;
import com.ireslab.emc.model.ClientAssetTokenRequest;
import com.ireslab.emc.model.ClientAssetTokenResponse;
import com.ireslab.emc.model.ClientPageResponse;
import com.ireslab.emc.model.ClientProfile;
import com.ireslab.emc.model.ClientProfileResponse;
import com.ireslab.emc.model.ClientRegistrationRequest;
import com.ireslab.emc.model.ClientRegistrationResponse;
import com.ireslab.emc.model.ClientUsersInfoResponse;
import com.ireslab.emc.model.CountryListResponse;
import com.ireslab.emc.model.ExchangeResponse;
import com.ireslab.emc.model.FilterLedgerRequest;
import com.ireslab.emc.model.FilterLedgerResponse;
import com.ireslab.emc.model.ProductGroupResponse;
import com.ireslab.emc.model.ProductRequest;
import com.ireslab.emc.model.ProductResponse;
import com.ireslab.emc.model.SaveProductRequest;
import com.ireslab.emc.model.SaveProductResponse;
import com.ireslab.emc.model.SignupRequest;
import com.ireslab.emc.model.SubscriptionPlanRequest;
import com.ireslab.emc.model.SubscriptionPlanResponse;
import com.ireslab.emc.model.TransactionPurposeRequest;
import com.ireslab.emc.model.TransactionPurposeResponse;
import com.ireslab.emc.model.UserAgentRegistrationRequest;
import com.ireslab.emc.model.UserAgentRegistrationResponse;
import com.ireslab.emc.model.UserAgentResponse;
import com.ireslab.emc.model.UserRegistrationRequest;
import com.ireslab.emc.model.UserRegistrationResponse;
import com.ireslab.sendx.electra.model.ProductConfigurationResponse;


public interface ClientUserInfoService {

	public ResponseEntity<?> getClientUserBalance(String userCorrelationId);

	boolean invokeUserOnboardingApi(SignupRequest signupRequest);

	// public ClientUsersInfoResponse getClientUsersList(); // Dummy method
	public ClientUsersInfoResponse getClientUsersList(String userCorrelationId);

	public ClientRegistrationResponse createClient(ClientRegistrationRequest clientRegistrationRequest);

	public ClientRegistrationResponse updateClient(ClientRegistrationRequest clientRegistrationRequest);

	public ClientCredentials getClientCredential(String clientCorrelationId);

	public ClientAssetTokenResponse clientAssetTokenConfiguration(ClientAssetTokenRequest clientAssetTokenRequest);

	public ClientProfileResponse clientDetails(String clientCorrelationId);

	public ClientRegistrationResponse activateClientCredential(ClientRegistrationRequest setClientId);

	public ClientAssetTokenResponse allClientAssetToken(String clientCorrelationId, String page, String size);

	public AccountDetailsResponse getAccountDetailsByPublicKey(String publicKey);

	public ExchangeResponse getAllExchangeDetails();

	public UserRegistrationResponse updateUser(UserRegistrationRequest setClientId, String userCorrelationId);

	public UserAgentResponse updateAgent(UserAgentRegistrationRequest userAgentRegistrationRequest);

	public UserAgentRegistrationResponse getAgentList(String userCorrelationId);

	public ClientProfile getClientByUserName(String userName);

	public ClientProfile getClientByEmailId(String emailId);

	public ClientProfile getClientByCorrelationId(String correlationId);

	public ClientProfile getClientByResetToken(String token);

	public ClientRegistrationResponse updateClientDetail(ClientRegistrationRequest clientRegistrationRequest);

	public ClientRegistrationResponse getAllClientData();

	public ClientPageResponse getAllClientCustom(Pageable pageable, String clientCorrelationId);

	public FilterLedgerResponse getLedger(FilterLedgerRequest filterLedgerRequest, String page, String size);

	public ClentAgentInvitationResponse saveClientAgentInvitationDetails(
			ClentAgentInvitationRequest clentAgentInvitationRequest);

	public CountryListResponse getCountryLists(String clientCorrelationId);

	public ExchangeResponse updateExchangeDetails(ExchangeDto clientExchangeRequest);

	public void downloadExcelAndCsv(FilterLedgerRequest filterLedgerRequest, HttpServletResponse res);

	public ProductGroupResponse getProductGroupList(String clientCorrelationId);

	public SaveProductResponse saveProduct(SaveProductRequest productRequest);

	public SaveProductResponse editProduct(SaveProductRequest productRequest);

	public SaveProductResponse deleteProduct(String productCode);
	
	public ProductResponse getProducts(ProductRequest productRequest, String clientCorrelationId);

	public TransactionPurposeResponse getAllTransactionPurposeList(String clientCorrelationId);

	public TransactionPurposeResponse addAndUpdateTransactionPurpose(TransactionPurposeRequest transactionPurposeRequest);

	public TransactionPurposeResponse deleteTransactionPurpose(TransactionPurposeRequest transactionPurposeRequest);

	public FilterLedgerResponse getOnlineOfflineLedger(FilterLedgerRequest filterLedgerRequest, String page,
			String size);

	public SubscriptionPlanResponse addUpdateSubscriptionPlan(SubscriptionPlanRequest subscriptionPlanRequest);

	public SubscriptionPlanResponse deleteSubscriptionPlan(SubscriptionPlanRequest subscriptionPlanRequest);

	public SubscriptionPlanResponse getSubscriptionPlanListByCountry(String countryId);

	public SubscriptionPlanResponse getSubscriptionPlanListForAdmin(String page, String size);

	
	

}
