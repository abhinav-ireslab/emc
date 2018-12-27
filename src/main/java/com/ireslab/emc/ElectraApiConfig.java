package com.ireslab.emc;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import com.ireslab.emc.util.Constants;

@Component
@PropertySource(value = Constants.ELECTRA_API_CONFIG_FILE)
@ConfigurationProperties("electra")
public class ElectraApiConfig {

	private String apiVersion;
	private String baseUrl;
	private String headerContentType;
	private String headerAccept;
	private String headerAuthorization;
	private String clientCorrelationId;
	private String tokenCorrelationId;
	private String clientId;
	private String clientSecret;
	private String grantType;
	private String authTokenEndpointUrl;
	private String userOnboardingApiEndpointUrl;
	private String loadTokensApiEndpointUrl;
	private String transferTokensApiEndpointUrl;
	private String cashoutTokensApiEndpointUrl;
	private String transactionHistoryApiEndpointUrl;
	private String clientUsersApiEndpointUrl;
	private String clientUserBalanceApiEndpointUrl;
	private String clientUesrId;
	private String clientPassword;
	private String clientRegistrationApiEndpointUrl;
	private String clientBaseUrl;
	private String clientUpdationApiEndpointUrl;
	private String userUpdationApiEndpointUrl;
	private String agentUpdationApiEndpointUrl;
	private String agentListApiEndpointUrl;
	private String clientCredentialApiEndpointUrl;
	private String clientAssetTokenApiEndpointUrl;
	private String clientDetailsApiEndpointUrl;
	private String clientCredentialActivationApiEndpointUrl;
	private String clientTokenDetailsApiEndpointUrl;
	private String clientTokenDetailsPagesApiEndpointUrl;
	private String accountDetailsByPublicKeyApiEndpointUrl;
	private String exchangeDetailsApiEndpointUrl;
	private String clientLedgerDetailsPagesApiEndpointUrl;

	private String clientByUsernameApiEndpointUrl;
	private String clientByEmailApiEndpointUrl;
	private String clientByCorrelationidApiEndpointUrl;
	private String clientByResetTokenApiEndpointUrl;
	private String clientDetailUpdationApiEndpointUrl;
	private String allClientDataApiEndpointUrl;
	private String allClientCustomApiEndpointUrl;
	private String clientAgentInvitationApiEndpointUrl;
	private String countryListApiEndpointUrl;
	private String updateExchangeApiEndpointUrl;
	private String transactionLimitDataApiEndpointUrl;
	private String ekycEkybApprovelListApiEndpointUrl;
	private String ekycEkybApprovedListApiEndpointUrl;
	private String ekycEkybApproveApiEndpointUrl;
	private String downloadExcelAndCsvApiEndpointUrl;
	private String productGroupsApiName;
	private String saveProductApiName;
	private String editProductApiName;
	private String deleteProductApiName;
	private String getProductApiName;
	
	private String allTransactionPurposeApiEndpointUrl;
	private String saveUpdateTransactionPurposeApiEndpointUrl;
	private String deleteTransactionPurposeApiEndpointUrl;
	private String onlineOfflineLedgerApiEndpointUrl;
	
	private String adminByUsernameApiEndpointUrl;
	private String adminByEmailApiEndpointUrl;
	private String updateAdminDetailsApiEndpointUrl;
	private String adminByResetTokenApiEndpointUrl;
	
	private String addUpdateSubscriptionPlanApiEndpointUrl;
	private String deleteSubscriptionPlanApiEndpointUrl;
	private String subscriptionPlanListApiEndpointUrl;
	private String subscriptionPlanListForAdminApiEndpointUrl;
	private String getAllPaymentTermsApiEndpointUrl;
	private String getChaptersListApiEndpointUrl;
	private String allHsnListBasedOnChapterApiEndpointUrl;
	private String getAllSacListBasedOnSearchApiEndpointUrl;
	private String excelLoadDataApiEndpointUrl;
	private String settlementReportListApiEndpointUrl;
	private String settlementReportUpdateApiEndpointUrl;

	

	public String getSettlementReportListApiEndpointUrl() {
		return settlementReportListApiEndpointUrl;
	}

	public void setSettlementReportListApiEndpointUrl(String settlementReportListApiEndpointUrl) {
		this.settlementReportListApiEndpointUrl = settlementReportListApiEndpointUrl;
	}

	public String getSettlementReportUpdateApiEndpointUrl() {
		return settlementReportUpdateApiEndpointUrl;
	}

	public void setSettlementReportUpdateApiEndpointUrl(String settlementReportUpdateApiEndpointUrl) {
		this.settlementReportUpdateApiEndpointUrl = settlementReportUpdateApiEndpointUrl;
	}

	public String getGetAllSacListBasedOnSearchApiEndpointUrl() {
		return getAllSacListBasedOnSearchApiEndpointUrl;
	}

	public void setGetAllSacListBasedOnSearchApiEndpointUrl(String getAllSacListBasedOnSearchApiEndpointUrl) {
		this.getAllSacListBasedOnSearchApiEndpointUrl = getAllSacListBasedOnSearchApiEndpointUrl;
	}

	public String getAllHsnListBasedOnChapterApiEndpointUrl() {
		return allHsnListBasedOnChapterApiEndpointUrl;
	}

	public void setAllHsnListBasedOnChapterApiEndpointUrl(String allHsnListBasedOnChapterApiEndpointUrl) {
		this.allHsnListBasedOnChapterApiEndpointUrl = allHsnListBasedOnChapterApiEndpointUrl;
	}

	public String getGetChaptersListApiEndpointUrl() {
		return getChaptersListApiEndpointUrl;
	}

	public void setGetChaptersListApiEndpointUrl(String getChaptersListApiEndpointUrl) {
		this.getChaptersListApiEndpointUrl = getChaptersListApiEndpointUrl;
	}

	public String getGetAllPaymentTermsApiEndpointUrl() {
		return getAllPaymentTermsApiEndpointUrl;
	}

	public void setGetAllPaymentTermsApiEndpointUrl(String getAllPaymentTermsApiEndpointUrl) {
		this.getAllPaymentTermsApiEndpointUrl = getAllPaymentTermsApiEndpointUrl;
	}

	public String getGetProductApiName() {
		return getProductApiName;
	}

	public void setGetProductApiName(String getProductApiName) {
		this.getProductApiName = getProductApiName;
	}

	public String getProductGroupsApiName() {
		return productGroupsApiName;
	}

	public void setProductGroupsApiName(String productGroupsApiName) {
		this.productGroupsApiName = productGroupsApiName;
	}

	public String getSaveProductApiName() {
		return saveProductApiName;
	}

	public void setSaveProductApiName(String saveProductApiName) {
		this.saveProductApiName = saveProductApiName;
	}

	public String getEditProductApiName() {
		return editProductApiName;
	}

	public void setEditProductApiName(String editProductApiName) {
		this.editProductApiName = editProductApiName;
	}

	public String getDeleteProductApiName() {
		return deleteProductApiName;
	}

	public void setDeleteProductApiName(String deleteProductApiName) {
		this.deleteProductApiName = deleteProductApiName;
	}

	public String getEkycEkybApproveApiEndpointUrl() {
		return ekycEkybApproveApiEndpointUrl;
	}

	public void setEkycEkybApproveApiEndpointUrl(String ekycEkybApproveApiEndpointUrl) {
		this.ekycEkybApproveApiEndpointUrl = ekycEkybApproveApiEndpointUrl;
	}

	public String getEkycEkybApprovedListApiEndpointUrl() {
		return ekycEkybApprovedListApiEndpointUrl;
	}

	public void setEkycEkybApprovedListApiEndpointUrl(String ekycEkybApprovedListApiEndpointUrl) {
		this.ekycEkybApprovedListApiEndpointUrl = ekycEkybApprovedListApiEndpointUrl;
	}

	public String getEkycEkybApprovelListApiEndpointUrl() {
		return ekycEkybApprovelListApiEndpointUrl;
	}

	public void setEkycEkybApprovelListApiEndpointUrl(String ekycEkybApprovelListApiEndpointUrl) {
		this.ekycEkybApprovelListApiEndpointUrl = ekycEkybApprovelListApiEndpointUrl;
	}

	public String getTransactionLimitDataApiEndpointUrl() {
		return transactionLimitDataApiEndpointUrl;
	}

	public void setTransactionLimitDataApiEndpointUrl(String transactionLimitDataApiEndpointUrl) {
		this.transactionLimitDataApiEndpointUrl = transactionLimitDataApiEndpointUrl;
	}

	public String getUpdateExchangeApiEndpointUrl() {
		return updateExchangeApiEndpointUrl;
	}

	public void setUpdateExchangeApiEndpointUrl(String updateExchangeApiEndpointUrl) {
		this.updateExchangeApiEndpointUrl = updateExchangeApiEndpointUrl;
	}

	public String getClientAgentInvitationApiEndpointUrl() {
		return clientAgentInvitationApiEndpointUrl;
	}

	public void setClientAgentInvitationApiEndpointUrl(String clientAgentInvitationApiEndpointUrl) {
		this.clientAgentInvitationApiEndpointUrl = clientAgentInvitationApiEndpointUrl;
	}

	public String getExchangeDetailsApiEndpointUrl() {
		return exchangeDetailsApiEndpointUrl;
	}

	public void setExchangeDetailsApiEndpointUrl(String exchangeDetailsApiEndpointUrl) {
		this.exchangeDetailsApiEndpointUrl = exchangeDetailsApiEndpointUrl;
	}

	public String getAccountDetailsByPublicKeyApiEndpointUrl() {
		return accountDetailsByPublicKeyApiEndpointUrl;
	}

	public void setAccountDetailsByPublicKeyApiEndpointUrl(String accountDetailsByPublicKeyApiEndpointUrl) {
		this.accountDetailsByPublicKeyApiEndpointUrl = accountDetailsByPublicKeyApiEndpointUrl;
	}

	public String getClientTokenDetailsPagesApiEndpointUrl() {
		return clientTokenDetailsPagesApiEndpointUrl;
	}

	public void setClientTokenDetailsPagesApiEndpointUrl(String clientTokenDetailsPagesApiEndpointUrl) {
		this.clientTokenDetailsPagesApiEndpointUrl = clientTokenDetailsPagesApiEndpointUrl;
	}

	public String getClientTokenDetailsApiEndpointUrl() {
		return clientTokenDetailsApiEndpointUrl;
	}

	public void setClientTokenDetailsApiEndpointUrl(String clientTokenDetailsApiEndpointUrl) {
		this.clientTokenDetailsApiEndpointUrl = clientTokenDetailsApiEndpointUrl;
	}

	public String getClientCredentialActivationApiEndpointUrl() {
		return clientCredentialActivationApiEndpointUrl;
	}

	public void setClientCredentialActivationApiEndpointUrl(String clientCredentialActivationApiEndpointUrl) {
		this.clientCredentialActivationApiEndpointUrl = clientCredentialActivationApiEndpointUrl;
	}

	public String getClientDetailsApiEndpointUrl() {
		return clientDetailsApiEndpointUrl;
	}

	public void setClientDetailsApiEndpointUrl(String clientDetailsApiEndpointUrl) {
		this.clientDetailsApiEndpointUrl = clientDetailsApiEndpointUrl;
	}

	public String getClientAssetTokenApiEndpointUrl() {
		return clientAssetTokenApiEndpointUrl;
	}

	public void setClientAssetTokenApiEndpointUrl(String clientAssetTokenApiEndpointUrl) {
		this.clientAssetTokenApiEndpointUrl = clientAssetTokenApiEndpointUrl;
	}

	public String getClientCredentialApiEndpointUrl() {
		return clientCredentialApiEndpointUrl;
	}

	public void setClientCredentialApiEndpointUrl(String clientCredentialApiEndpointUrl) {
		this.clientCredentialApiEndpointUrl = clientCredentialApiEndpointUrl;
	}

	public String getClientUpdationApiEndpointUrl() {
		return clientUpdationApiEndpointUrl;
	}

	public void setClientUpdationApiEndpointUrl(String clientUpdationApiEndpointUrl) {
		this.clientUpdationApiEndpointUrl = clientUpdationApiEndpointUrl;
	}

	/**
	 * @return the userUpdationApiEndpointUrl
	 */
	public String getUserUpdationApiEndpointUrl() {
		return userUpdationApiEndpointUrl;
	}

	/**
	 * @param userUpdationApiEndpointUrl
	 *            the userUpdationApiEndpointUrl to set
	 */
	public void setUserUpdationApiEndpointUrl(String userUpdationApiEndpointUrl) {
		this.userUpdationApiEndpointUrl = userUpdationApiEndpointUrl;
	}

	public String getClientBaseUrl() {
		return clientBaseUrl;
	}

	public void setClientBaseUrl(String clientBaseUrl) {
		this.clientBaseUrl = clientBaseUrl;
	}

	public String getClientRegistrationApiEndpointUrl() {
		return clientRegistrationApiEndpointUrl;
	}

	public void setClientRegistrationApiEndpointUrl(String clientRegistrationApiEndpointUrl) {
		this.clientRegistrationApiEndpointUrl = clientRegistrationApiEndpointUrl;
	}

	public String getClientUesrId() {
		return clientUesrId;
	}

	public void setClientUesrId(String clientUesrId) {
		this.clientUesrId = clientUesrId;
	}

	/**
	 * @return the apiVersion
	 */
	public String getApiVersion() {
		return apiVersion;
	}

	/**
	 * @param apiVersion
	 *            the apiVersion to set
	 */
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	/**
	 * @return the baseUrl
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @param baseUrl
	 *            the baseUrl to set
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	/**
	 * @return the headerContentType
	 */
	public String getHeaderContentType() {
		return headerContentType;
	}

	/**
	 * @param headerContentType
	 *            the headerContentType to set
	 */
	public void setHeaderContentType(String headerContentType) {
		this.headerContentType = headerContentType;
	}

	/**
	 * @return the headerAccept
	 */
	public String getHeaderAccept() {
		return headerAccept;
	}

	/**
	 * @param headerAccept
	 *            the headerAccept to set
	 */
	public void setHeaderAccept(String headerAccept) {
		this.headerAccept = headerAccept;
	}

	/**
	 * @return the headerAuthorizationKey
	 */
	public String getHeaderAuthorization() {
		return headerAuthorization;
	}

	/**
	 * @param headerAuthorizationKey
	 *            the headerAuthorizationKey to set
	 */
	public void setHeaderAuthorization(String headerAuthorization) {
		this.headerAuthorization = headerAuthorization;
	}

	/**
	 * @return the clientCorrelationId
	 */
	public String getClientCorrelationId() {
		return clientCorrelationId;
	}

	/**
	 * @param clientCorrelationId
	 *            the clientCorrelationId to set
	 */
	public void setClientCorrelationId(String clientCorrelationId) {
		this.clientCorrelationId = clientCorrelationId;
	}

	/**
	 * @return the tokenCorrelationId
	 */
	public String getTokenCorrelationId() {
		return tokenCorrelationId;
	}

	/**
	 * @param tokenCorrelationId
	 *            the tokenCorrelationId to set
	 */
	public void setTokenCorrelationId(String tokenCorrelationId) {
		this.tokenCorrelationId = tokenCorrelationId;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the clientSecret
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * @param clientSecret
	 *            the clientSecret to set
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	/**
	 * @return the grantType
	 */
	public String getGrantType() {
		return grantType;
	}

	/**
	 * @param grantType
	 *            the grantType to set
	 */
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	/**
	 * @return the authTokenEndpointUrl
	 */
	public String getAuthTokenEndpointUrl() {
		return authTokenEndpointUrl;
	}

	/**
	 * @param authTokenEndpointUrl
	 *            the authTokenEndpointUrl to set
	 */
	public void setAuthTokenEndpointUrl(String authTokenEndpointUrl) {
		this.authTokenEndpointUrl = authTokenEndpointUrl;
	}

	/**
	 * @return the userOnboardingApiEndpointUrl
	 */
	public String getUserOnboardingApiEndpointUrl() {
		return userOnboardingApiEndpointUrl;
	}

	/**
	 * @param userOnboardingApiEndpointUrl
	 *            the userOnboardingApiEndpointUrl to set
	 */
	public void setUserOnboardingApiEndpointUrl(String userOnboardingApiEndpointUrl) {
		this.userOnboardingApiEndpointUrl = userOnboardingApiEndpointUrl;
	}

	/**
	 * @return the loadTokensApiEndpointUrl
	 */
	public String getLoadTokensApiEndpointUrl() {
		return loadTokensApiEndpointUrl;
	}

	/**
	 * @param loadTokensApiEndpointUrl
	 *            the loadTokensApiEndpointUrl to set
	 */
	public void setLoadTokensApiEndpointUrl(String loadTokensApiEndpointUrl) {
		this.loadTokensApiEndpointUrl = loadTokensApiEndpointUrl;
	}

	/**
	 * @return the transferTokensApiEndpointUrl
	 */
	public String getTransferTokensApiEndpointUrl() {
		return transferTokensApiEndpointUrl;
	}

	/**
	 * @param transferTokensApiEndpointUrl
	 *            the transferTokensApiEndpointUrl to set
	 */
	public void setTransferTokensApiEndpointUrl(String transferTokensApiEndpointUrl) {
		this.transferTokensApiEndpointUrl = transferTokensApiEndpointUrl;
	}

	/**
	 * @return the cashoutTokensApiEndpointUrl
	 */
	public String getCashoutTokensApiEndpointUrl() {
		return cashoutTokensApiEndpointUrl;
	}

	/**
	 * @param cashoutTokensApiEndpointUrl
	 *            the cashoutTokensApiEndpointUrl to set
	 */
	public void setCashoutTokensApiEndpointUrl(String cashoutTokensApiEndpointUrl) {
		this.cashoutTokensApiEndpointUrl = cashoutTokensApiEndpointUrl;
	}

	/**
	 * @return the transactionHistoryApiEndpointUrl
	 */
	public String getTransactionHistoryApiEndpointUrl() {
		return transactionHistoryApiEndpointUrl;
	}

	/**
	 * @param transactionHistoryApiEndpointUrl
	 *            the transactionHistoryApiEndpointUrl to set
	 */
	public void setTransactionHistoryApiEndpointUrl(String transactionHistoryApiEndpointUrl) {
		this.transactionHistoryApiEndpointUrl = transactionHistoryApiEndpointUrl;
	}

	public String getClientUsersApiEndpointUrl() {
		return clientUsersApiEndpointUrl;
	}

	public void setClientUsersApiEndpointUrl(String clientUsersApiEndpointUrl) {
		this.clientUsersApiEndpointUrl = clientUsersApiEndpointUrl;
	}

	public String getClientUserBalanceApiEndpointUrl() {
		return clientUserBalanceApiEndpointUrl;
	}

	public void setClientUserBalanceApiEndpointUrl(String clientUserBalanceApiEndpointUrl) {
		this.clientUserBalanceApiEndpointUrl = clientUserBalanceApiEndpointUrl;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public String getAgentUpdationApiEndpointUrl() {
		return agentUpdationApiEndpointUrl;
	}

	public void setAgentUpdationApiEndpointUrl(String agentUpdationApiEndpointUrl) {
		this.agentUpdationApiEndpointUrl = agentUpdationApiEndpointUrl;
	}

	public String getAgentListApiEndpointUrl() {
		return agentListApiEndpointUrl;
	}

	public void setAgentListApiEndpointUrl(String agentListApiEndpointUrl) {
		this.agentListApiEndpointUrl = agentListApiEndpointUrl;
	}

	public String getClientByCorrelationidApiEndpointUrl() {
		return clientByCorrelationidApiEndpointUrl;
	}

	public void setClientByCorrelationidApiEndpointUrl(String clientByCorrelationidApiEndpointUrl) {
		this.clientByCorrelationidApiEndpointUrl = clientByCorrelationidApiEndpointUrl;
	}

	public String getClientByResetTokenApiEndpointUrl() {
		return clientByResetTokenApiEndpointUrl;
	}

	public void setClientByResetTokenApiEndpointUrl(String clientByResetTokenApiEndpointUrl) {
		this.clientByResetTokenApiEndpointUrl = clientByResetTokenApiEndpointUrl;
	}

	public String getClientByEmailApiEndpointUrl() {
		return clientByEmailApiEndpointUrl;
	}

	public void setClientByEmailApiEndpointUrl(String clientByEmailApiEndpointUrl) {
		this.clientByEmailApiEndpointUrl = clientByEmailApiEndpointUrl;
	}

	public String getClientByUsernameApiEndpointUrl() {
		return clientByUsernameApiEndpointUrl;
	}

	public void setClientByUsernameApiEndpointUrl(String clientByUsernameApiEndpointUrl) {
		this.clientByUsernameApiEndpointUrl = clientByUsernameApiEndpointUrl;
	}

	public String getClientDetailUpdationApiEndpointUrl() {
		return clientDetailUpdationApiEndpointUrl;
	}

	public void setClientDetailUpdationApiEndpointUrl(String clientDetailUpdationApiEndpointUrl) {
		this.clientDetailUpdationApiEndpointUrl = clientDetailUpdationApiEndpointUrl;
	}

	public String getAllClientDataApiEndpointUrl() {
		return allClientDataApiEndpointUrl;
	}

	public void setAllClientDataApiEndpointUrl(String allClientDataApiEndpointUrl) {
		this.allClientDataApiEndpointUrl = allClientDataApiEndpointUrl;
	}

	public String getAllClientCustomApiEndpointUrl() {
		return allClientCustomApiEndpointUrl;
	}

	public void setAllClientCustomApiEndpointUrl(String allClientCustomApiEndpointUrl) {
		this.allClientCustomApiEndpointUrl = allClientCustomApiEndpointUrl;
	}

	public String getClientLedgerDetailsPagesApiEndpointUrl() {
		return clientLedgerDetailsPagesApiEndpointUrl;
	}

	public void setClientLedgerDetailsPagesApiEndpointUrl(String clientLedgerDetailsPagesApiEndpointUrl) {
		this.clientLedgerDetailsPagesApiEndpointUrl = clientLedgerDetailsPagesApiEndpointUrl;
	}

	public String getCountryListApiEndpointUrl() {
		return countryListApiEndpointUrl;
	}

	public void setCountryListApiEndpointUrl(String countryListApiEndpointUrl) {
		this.countryListApiEndpointUrl = countryListApiEndpointUrl;
	}

	public String getDownloadExcelAndCsvApiEndpointUrl() {
		return downloadExcelAndCsvApiEndpointUrl;
	}

	public void setDownloadExcelAndCsvApiEndpointUrl(String downloadExcelAndCsvApiEndpointUrl) {
		this.downloadExcelAndCsvApiEndpointUrl = downloadExcelAndCsvApiEndpointUrl;
	}

	public String getAllTransactionPurposeApiEndpointUrl() {
		return allTransactionPurposeApiEndpointUrl;
	}

	public void setAllTransactionPurposeApiEndpointUrl(String allTransactionPurposeApiEndpointUrl) {
		this.allTransactionPurposeApiEndpointUrl = allTransactionPurposeApiEndpointUrl;
	}

	public String getSaveUpdateTransactionPurposeApiEndpointUrl() {
		return saveUpdateTransactionPurposeApiEndpointUrl;
	}

	public void setSaveUpdateTransactionPurposeApiEndpointUrl(String saveUpdateTransactionPurposeApiEndpointUrl) {
		this.saveUpdateTransactionPurposeApiEndpointUrl = saveUpdateTransactionPurposeApiEndpointUrl;
	}

	public String getDeleteTransactionPurposeApiEndpointUrl() {
		return deleteTransactionPurposeApiEndpointUrl;
	}

	public void setDeleteTransactionPurposeApiEndpointUrl(String deleteTransactionPurposeApiEndpointUrl) {
		this.deleteTransactionPurposeApiEndpointUrl = deleteTransactionPurposeApiEndpointUrl;
	}

	public String getOnlineOfflineLedgerApiEndpointUrl() {
		return onlineOfflineLedgerApiEndpointUrl;
	}

	public void setOnlineOfflineLedgerApiEndpointUrl(String onlineOfflineLedgerApiEndpointUrl) {
		this.onlineOfflineLedgerApiEndpointUrl = onlineOfflineLedgerApiEndpointUrl;
	}

	public String getAdminByUsernameApiEndpointUrl() {
		return adminByUsernameApiEndpointUrl;
	}

	public void setAdminByUsernameApiEndpointUrl(String adminByUsernameApiEndpointUrl) {
		this.adminByUsernameApiEndpointUrl = adminByUsernameApiEndpointUrl;
	}

	public String getAdminByEmailApiEndpointUrl() {
		return adminByEmailApiEndpointUrl;
	}

	public void setAdminByEmailApiEndpointUrl(String adminByEmailApiEndpointUrl) {
		this.adminByEmailApiEndpointUrl = adminByEmailApiEndpointUrl;
	}

	public String getUpdateAdminDetailsApiEndpointUrl() {
		return updateAdminDetailsApiEndpointUrl;
	}

	public void setUpdateAdminDetailsApiEndpointUrl(String updateAdminDetailsApiEndpointUrl) {
		this.updateAdminDetailsApiEndpointUrl = updateAdminDetailsApiEndpointUrl;
	}

	public String getAdminByResetTokenApiEndpointUrl() {
		return adminByResetTokenApiEndpointUrl;
	}

	public void setAdminByResetTokenApiEndpointUrl(String adminByResetTokenApiEndpointUrl) {
		this.adminByResetTokenApiEndpointUrl = adminByResetTokenApiEndpointUrl;
	}

	public String getAddUpdateSubscriptionPlanApiEndpointUrl() {
		return addUpdateSubscriptionPlanApiEndpointUrl;
	}

	public void setAddUpdateSubscriptionPlanApiEndpointUrl(String addUpdateSubscriptionPlanApiEndpointUrl) {
		this.addUpdateSubscriptionPlanApiEndpointUrl = addUpdateSubscriptionPlanApiEndpointUrl;
	}

	public String getDeleteSubscriptionPlanApiEndpointUrl() {
		return deleteSubscriptionPlanApiEndpointUrl;
	}

	public void setDeleteSubscriptionPlanApiEndpointUrl(String deleteSubscriptionPlanApiEndpointUrl) {
		this.deleteSubscriptionPlanApiEndpointUrl = deleteSubscriptionPlanApiEndpointUrl;
	}

	public String getSubscriptionPlanListApiEndpointUrl() {
		return subscriptionPlanListApiEndpointUrl;
	}

	public void setSubscriptionPlanListApiEndpointUrl(String subscriptionPlanListApiEndpointUrl) {
		this.subscriptionPlanListApiEndpointUrl = subscriptionPlanListApiEndpointUrl;
	}

	public String getSubscriptionPlanListForAdminApiEndpointUrl() {
		return subscriptionPlanListForAdminApiEndpointUrl;
	}

	public void setSubscriptionPlanListForAdminApiEndpointUrl(String subscriptionPlanListForAdminApiEndpointUrl) {
		this.subscriptionPlanListForAdminApiEndpointUrl = subscriptionPlanListForAdminApiEndpointUrl;
	}

	public String getExcelLoadDataApiEndpointUrl() {
		return excelLoadDataApiEndpointUrl;
	}

	public void setExcelLoadDataApiEndpointUrl(String excelLoadDataApiEndpointUrl) {
		this.excelLoadDataApiEndpointUrl = excelLoadDataApiEndpointUrl;
	}

}
