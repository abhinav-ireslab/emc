package com.ireslab.emc.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;



@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientProfile extends GenericResponse implements Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;


	private String clientCorrelationId;

	
	private String clientName;
	
	private String description;
	
	private String clientStatus;
	
	private String contactNumber1;

	private String emailAddress;

	private boolean isTestnetAccount;
	
	
    private boolean isRegistered;
	
    private String clientApiKey;
	
	private String clientApiSecret;
	
	private String userName;
	
	private String password;
	
	private String resetToken;
	
	private String companyCountry;
	
	private String companyState;
	
	private String companyCity;
	
	private String companyPinCode;
	
	private String companyContact;
	
	private String companyFax;
	
	private String companyAddress;
	
	private boolean is2faEnabled;
	
	private String account_type;
	
	private String securityCode;
	
	private String company_name;
	
	private Integer availableUsers;
	
	private String companyCode;
	
    private boolean subscriptionStatus;
    
    private boolean isSubscriptionExpired;
	
    private String countryDialCode;
    
    private String gstNumber;
    
    private String currencySymbol;
    
	

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public boolean getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(boolean subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}
	
	private boolean isPasswordReset;
	private boolean isClientCredentialActivated;
	
	

	public boolean isClientCredentialActivated() {
		return isClientCredentialActivated;
	}

	public void setClientCredentialActivated(boolean isClientCredentialActivated) {
		this.isClientCredentialActivated = isClientCredentialActivated;
	}

	

	

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getClientCorrelationId() {
		return clientCorrelationId;
	}

	public void setClientCorrelationId(String clientCorrelationId) {
		this.clientCorrelationId = clientCorrelationId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getContactNumber1() {
		return contactNumber1;
	}

	public void setContactNumber1(String contactNumber1) {
		this.contactNumber1 = contactNumber1;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isTestnetAccount() {
		return isTestnetAccount;
	}

	public void setTestnetAccount(boolean isTestnetAccount) {
		this.isTestnetAccount = isTestnetAccount;
	}

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public String getClientApiKey() {
		return clientApiKey;
	}

	public void setClientApiKey(String clientApiKey) {
		this.clientApiKey = clientApiKey;
	}

	public String getClientApiSecret() {
		return clientApiSecret;
	}

	public void setClientApiSecret(String clientApiSecret) {
		this.clientApiSecret = clientApiSecret;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public String getCompanyCountry() {
		return companyCountry;
	}

	public void setCompanyCountry(String companyCountry) {
		this.companyCountry = companyCountry;
	}

	public String getCompanyState() {
		return companyState;
	}

	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}

	public String getCompanyCity() {
		return companyCity;
	}

	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}

	public String getCompanyPinCode() {
		return companyPinCode;
	}

	public void setCompanyPinCode(String companyPinCode) {
		this.companyPinCode = companyPinCode;
	}

	public String getCompanyContact() {
		return companyContact;
	}

	public void setCompanyContact(String companyContact) {
		this.companyContact = companyContact;
	}

	public String getCompanyFax() {
		return companyFax;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public boolean isIs2faEnabled() {
		return is2faEnabled;
	}

	public void setIs2faEnabled(boolean is2faEnabled) {
		this.is2faEnabled = is2faEnabled;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getClientStatus() {
		return clientStatus;
	}

	public void setClientStatus(String clientStatus) {
		this.clientStatus = clientStatus;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	
	public Integer getAvailableUsers() {
		return availableUsers;
	}

	public void setAvailableUsers(Integer availableUsers) {
		this.availableUsers = availableUsers;
	}

	public boolean isPasswordReset() {
		return isPasswordReset;
	}

	public void setPasswordReset(boolean isPasswordReset) {
		this.isPasswordReset = isPasswordReset;
	}

	public boolean getIsSubscriptionExpired() {
		return isSubscriptionExpired;
	}

	public void setIsSubscriptionExpired(boolean isSubscriptionExpired) {
		this.isSubscriptionExpired = isSubscriptionExpired;
	}

	public String getCountryDialCode() {
		return countryDialCode;
	}

	public void setCountryDialCode(String countryDialCode) {
		this.countryDialCode = countryDialCode;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	
	

	


}
