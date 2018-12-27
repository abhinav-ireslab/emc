/**
 * 
 */
package com.ireslab.emc.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;



/**
 * @author Akhilesh
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer accountId;
	private String company_name;
	private String email;
	private String username;
	private String contactNumber;
	private String password;
	private Integer createdBy;
	private Date createdDate;
	private Integer modifiedBy;
	private Date modifiedDate;
	private String account_type;
	private String clientCorrelationId;
	private String description;
	private String status;
	private boolean is2faEnabled;
	private String clientApiKey;
	private String clientApiSecret;
	
	private String companyFax;
	private String companyContact;
	private String companyPinCode;
	private String companyCity;
	
	private String companyState;
	private String companyCountry;
	private String companyAddress;
	
	private String issuingAccountPublicKey;
	private String baseTxnAccountPublicKey;
	
	private String contactNumber1;
	private boolean isClientCredentialActivated;
	
	
	
	
	private boolean isPasswordReset;
	private String resetToken;
	
	private String gstNumber;
	private String currencySymbol;
	
	
	
	public String getGstNumber() {
		return gstNumber;
	}


	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}


	public String getIssuingAccountPublicKey() {
		return issuingAccountPublicKey;
	}


	public void setIssuingAccountPublicKey(String issuingAccountPublicKey) {
		this.issuingAccountPublicKey = issuingAccountPublicKey;
	}


	public String getBaseTxnAccountPublicKey() {
		return baseTxnAccountPublicKey;
	}


	public void setBaseTxnAccountPublicKey(String baseTxnAccountPublicKey) {
		this.baseTxnAccountPublicKey = baseTxnAccountPublicKey;
	}


	public String getCompanyFax() {
		return companyFax;
	}


	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}


	public String getCompanyContact() {
		return companyContact;
	}


	public void setCompanyContact(String companyContact) {
		this.companyContact = companyContact;
	}


	public String getCompanyPinCode() {
		return companyPinCode;
	}


	public void setCompanyPinCode(String companyPinCode) {
		this.companyPinCode = companyPinCode;
	}


	public String getCompanyCity() {
		return companyCity;
	}


	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}


	public String getCompanyState() {
		return companyState;
	}


	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}


	public String getCompanyCountry() {
		return companyCountry;
	}


	public void setCompanyCountry(String companyCountry) {
		this.companyCountry = companyCountry;
	}


	public String getCompanyAddress() {
		return companyAddress;
	}


	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
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


	public boolean isIs2faEnabled() {
		return is2faEnabled;
	}


	public void setIs2faEnabled(boolean is2faEnabled) {
		this.is2faEnabled = is2faEnabled;
	}


	public AccountDetails() {
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getClientCorrelationId() {
		return clientCorrelationId;
	}

	public void setClientCorrelationId(String clientCorrelationId) {
		this.clientCorrelationId = clientCorrelationId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "AccountDetails [accountId=" + accountId + ", company_name=" + company_name + ", email=" + email
				+ ", username=" + username + ", contactNumber=" + contactNumber + ", password=" + password
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", account_type=" + account_type + ", clientCorrelationId="
				+ clientCorrelationId + ", description=" + description + ", status=" + status + ", is2faEnabled="
				+ is2faEnabled + ", clientApiKey=" + clientApiKey + ", clientApiSecret=" + clientApiSecret
				+ ", companyFax=" + companyFax + ", companyContact=" + companyContact + ", companyPinCode="
				+ companyPinCode + ", companyCity=" + companyCity + ", companyState=" + companyState
				+ ", companyCountry=" + companyCountry + ", companyAddress=" + companyAddress
				+ ", issuingAccountPublicKey=" + issuingAccountPublicKey + ", baseTxnAccountPublicKey="
				+ baseTxnAccountPublicKey + "]";
	}


	public boolean isPasswordReset() {
		return isPasswordReset;
	}


	public void setPasswordReset(boolean isPasswordReset) {
		this.isPasswordReset = isPasswordReset;
	}


	public String getResetToken() {
		return resetToken;
	}


	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}


	public String getContactNumber1() {
		return contactNumber1;
	}


	public void setContactNumber1(String contactNumber1) {
		this.contactNumber1 = contactNumber1;
	}


	public boolean isClientCredentialActivated() {
		return isClientCredentialActivated;
	}


	public void setClientCredentialActivated(boolean isClientCredentialActivated) {
		this.isClientCredentialActivated = isClientCredentialActivated;
	}


	public String getCurrencySymbol() {
		return currencySymbol;
	}


	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

}
