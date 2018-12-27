package com.ireslab.emc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ireslab.emc.util.Status;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenLifecycleManagementRequest extends GenericRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore(value = true)
	private String userCorrelationId;

	private String beneficiaryCorrelationId;

	private String tokenCorrelationId;

	private String noOfTokens;

	private Status status;

	private Boolean accountStatus;

	/**
	 * @return the userCorrelationId
	 */
	public String getUserCorrelationId() {
		return userCorrelationId;
	}

	/**
	 * @param userCorrelationId
	 *            the userCorrelationId to set
	 */
	public void setUserCorrelationId(String userCorrelationId) {
		this.userCorrelationId = userCorrelationId;
	}

	/**
	 * @return the beneficiaryCorrelationId
	 */
	public String getBeneficiaryCorrelationId() {
		return beneficiaryCorrelationId;
	}

	/**
	 * @param beneficiaryCorrelationId
	 *            the beneficiaryCorrelationId to set
	 */
	public void setBeneficiaryCorrelationId(String beneficiaryCorrelationId) {
		this.beneficiaryCorrelationId = beneficiaryCorrelationId;
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
	 * @return the noOfTokens
	 */
	public String getNoOfTokens() {
		return noOfTokens;
	}

	/**
	 * @param noOfTokens
	 *            the noOfTokens to set
	 */
	public void setNoOfTokens(String noOfTokens) {
		this.noOfTokens = noOfTokens;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Boolean getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Boolean accountStatus) {
		this.accountStatus = accountStatus;
	}

}
