package com.ireslab.emc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ireslab.sendx.electra.model.AssetDetails;
import com.ireslab.sendx.electra.model.TokenDetails;
import com.ireslab.sendx.electra.utils.Status;

/**
 * @author Nitin
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1920741613515047771L;

	private String userName;
	
	private String password;
	
	private String email;
	
	private String enable2fa;
	
	private String securityCode;
	
	private String googleAuthenticatorBarCode;
	private String otpCode;
	private String clientCorrelationId;
	
	private boolean isClientCredentialActivated;
	
	private String firstName;
	private String lastName;
	private String userCorrelationId;
	private String createdDate;
	private String accountPublicKey;
	private String mobileNumber;
	private String emailAddress;
	private boolean isRegistered;
	private Status status;
	private String companyName;
	private String companyCode;
	private List<TokenDetails> tokenDetails;
	private List<AssetDetails> assetDetails;
	private boolean isEkycEkybApproved;
	private String countryDialCode;
	private String residentialAddress;
	private String dob;
	private String gender;
	private String scanDocumentType;
	private String scanDocumentId;
	private String scanDocumentFrontPage;
	private String scanDocumentBackPage;
	private String postalCode;
	private String profileImageValue;
	private String businessId;
	private String businessLong;
	private String businessLat;
	private boolean isKycConfigure;
	private String idproofImage;
	private Date subscriptionExpireOn;
	private String uniqueCode;
	private String countryName;
	private boolean isClient;
	private String iso4217CurrencyAlphabeticCode;
	private String firebaseServiceKey;
	private String gcmRegisterKey;
	private String accountBalance;
	@JsonIgnore(value = true)
	private Status accountStatus;
	private Boolean ismPINSetup;
	private String mPIN;
	private String verificationCode;
	private String contactAddress;
	private String nricPassportNumber;
	private String iso4217CurrencyName;
	private String profileImageUrl;
	private String electraAppUrl;
	private String countryCurrrency;
	private String ekyc;
	private String notificationCount;
	
	
	
	
	
	
	
	
	

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public List<TokenDetails> getTokenDetails() {
		return tokenDetails;
	}

	public void setTokenDetails(List<TokenDetails> tokenDetails) {
		this.tokenDetails = tokenDetails;
	}

	public List<AssetDetails> getAssetDetails() {
		return assetDetails;
	}

	public void setAssetDetails(List<AssetDetails> assetDetails) {
		this.assetDetails = assetDetails;
	}

	public boolean isEkycEkybApproved() {
		return isEkycEkybApproved;
	}

	public void setEkycEkybApproved(boolean isEkycEkybApproved) {
		this.isEkycEkybApproved = isEkycEkybApproved;
	}

	public String getCountryDialCode() {
		return countryDialCode;
	}

	public void setCountryDialCode(String countryDialCode) {
		this.countryDialCode = countryDialCode;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getScanDocumentType() {
		return scanDocumentType;
	}

	public void setScanDocumentType(String scanDocumentType) {
		this.scanDocumentType = scanDocumentType;
	}

	public String getScanDocumentId() {
		return scanDocumentId;
	}

	public void setScanDocumentId(String scanDocumentId) {
		this.scanDocumentId = scanDocumentId;
	}

	public String getScanDocumentFrontPage() {
		return scanDocumentFrontPage;
	}

	public void setScanDocumentFrontPage(String scanDocumentFrontPage) {
		this.scanDocumentFrontPage = scanDocumentFrontPage;
	}

	public String getScanDocumentBackPage() {
		return scanDocumentBackPage;
	}

	public void setScanDocumentBackPage(String scanDocumentBackPage) {
		this.scanDocumentBackPage = scanDocumentBackPage;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getProfileImageValue() {
		return profileImageValue;
	}

	public void setProfileImageValue(String profileImageValue) {
		this.profileImageValue = profileImageValue;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBusinessLong() {
		return businessLong;
	}

	public void setBusinessLong(String businessLong) {
		this.businessLong = businessLong;
	}

	public String getBusinessLat() {
		return businessLat;
	}

	public void setBusinessLat(String businessLat) {
		this.businessLat = businessLat;
	}

	public boolean isKycConfigure() {
		return isKycConfigure;
	}

	public void setKycConfigure(boolean isKycConfigure) {
		this.isKycConfigure = isKycConfigure;
	}

	public String getIdproofImage() {
		return idproofImage;
	}

	public void setIdproofImage(String idproofImage) {
		this.idproofImage = idproofImage;
	}

	public Date getSubscriptionExpireOn() {
		return subscriptionExpireOn;
	}

	public void setSubscriptionExpireOn(Date subscriptionExpireOn) {
		this.subscriptionExpireOn = subscriptionExpireOn;
	}

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public boolean isClient() {
		return isClient;
	}

	public void setClient(boolean isClient) {
		this.isClient = isClient;
	}

	public String getIso4217CurrencyAlphabeticCode() {
		return iso4217CurrencyAlphabeticCode;
	}

	public void setIso4217CurrencyAlphabeticCode(String iso4217CurrencyAlphabeticCode) {
		this.iso4217CurrencyAlphabeticCode = iso4217CurrencyAlphabeticCode;
	}

	public String getFirebaseServiceKey() {
		return firebaseServiceKey;
	}

	public void setFirebaseServiceKey(String firebaseServiceKey) {
		this.firebaseServiceKey = firebaseServiceKey;
	}

	public String getGcmRegisterKey() {
		return gcmRegisterKey;
	}

	public void setGcmRegisterKey(String gcmRegisterKey) {
		this.gcmRegisterKey = gcmRegisterKey;
	}

	public String getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Status getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Status accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Boolean getIsmPINSetup() {
		return ismPINSetup;
	}

	public void setIsmPINSetup(Boolean ismPINSetup) {
		this.ismPINSetup = ismPINSetup;
	}

	public String getmPIN() {
		return mPIN;
	}

	public void setmPIN(String mPIN) {
		this.mPIN = mPIN;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getNricPassportNumber() {
		return nricPassportNumber;
	}

	public void setNricPassportNumber(String nricPassportNumber) {
		this.nricPassportNumber = nricPassportNumber;
	}

	public String getIso4217CurrencyName() {
		return iso4217CurrencyName;
	}

	public void setIso4217CurrencyName(String iso4217CurrencyName) {
		this.iso4217CurrencyName = iso4217CurrencyName;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getElectraAppUrl() {
		return electraAppUrl;
	}

	public void setElectraAppUrl(String electraAppUrl) {
		this.electraAppUrl = electraAppUrl;
	}

	public String getCountryCurrrency() {
		return countryCurrrency;
	}

	public void setCountryCurrrency(String countryCurrrency) {
		this.countryCurrrency = countryCurrrency;
	}

	public String getEkyc() {
		return ekyc;
	}

	public void setEkyc(String ekyc) {
		this.ekyc = ekyc;
	}

	public String getNotificationCount() {
		return notificationCount;
	}

	public void setNotificationCount(String notificationCount) {
		this.notificationCount = notificationCount;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserCorrelationId() {
		return userCorrelationId;
	}

	public void setUserCorrelationId(String userCorrelationId) {
		this.userCorrelationId = userCorrelationId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getAccountPublicKey() {
		return accountPublicKey;
	}

	public void setAccountPublicKey(String accountPublicKey) {
		this.accountPublicKey = accountPublicKey;
	}

	public boolean isClientCredentialActivated() {
		return isClientCredentialActivated;
	}

	public void setClientCredentialActivated(boolean isClientCredentialActivated) {
		this.isClientCredentialActivated = isClientCredentialActivated;
	}
	
	public String getClientCorrelationId() {
		return clientCorrelationId;
	}

	public void setClientCorrelationId(String clientCorrelationId) {
		this.clientCorrelationId = clientCorrelationId;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	
	
	public String getOtpCode() {
		return otpCode;
	}

	public void setOtpCode(String otpCode) {
		this.otpCode = otpCode;
	}

	
	
	
	

	public String getGoogleAuthenticatorBarCode() {
		return googleAuthenticatorBarCode;
	}

	public void setGoogleAuthenticatorBarCode(String googleAuthenticatorBarCode) {
		this.googleAuthenticatorBarCode = googleAuthenticatorBarCode;
	}

	public String profile() {
		return securityCode;
	}

	
	public String getEnable2fa() {
		return enable2fa;
	}

	public void setEnable2fa(String enable2fa) {
		this.enable2fa = enable2fa;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}