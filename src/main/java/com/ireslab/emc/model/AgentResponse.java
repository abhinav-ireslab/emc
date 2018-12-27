/**
 * 
 */
package com.ireslab.emc.model;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentResponse implements Serializable {

	private static final long serialVersionUID = 4192032820850153348L;

	private String agentName;

	private BigInteger mobileNumber;

	private String idProofImageUrl;

	private String agentAddress;

	private String businessId;

	private String fiatCurrencies;

	private String cryptoCurrencies;

	private String businessLongitude;

	private String businessLatitude;

	private String ekyc;

	private String status;

	private String countryCode;

	private String userCorrelationId;

	public AgentResponse() {
		super();
	}

	public String getAgentName() {
		return agentName;
	}

	public BigInteger getMobileNumber() {
		return mobileNumber;
	}

	public String getIdProofImageUrl() {
		return idProofImageUrl;
	}

	public String getAgentAddress() {
		return agentAddress;
	}

	public String getBusinessId() {
		return businessId;
	}

	public String getFiatCurrencies() {
		return fiatCurrencies;
	}

	public String getCryptoCurrencies() {
		return cryptoCurrencies;
	}

	public String getBusinessLongitude() {
		return businessLongitude;
	}

	public String getBusinessLatitude() {
		return businessLatitude;
	}

	public String getEkyc() {
		return ekyc;
	}

	public String getStatus() {
		return status;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getUserCorrelationId() {
		return userCorrelationId;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public void setMobileNumber(BigInteger mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setIdProofImageUrl(String idProofImageUrl) {
		this.idProofImageUrl = idProofImageUrl;
	}

	public void setAgentAddress(String agentAddress) {
		this.agentAddress = agentAddress;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public void setFiatCurrencies(String fiatCurrencies) {
		this.fiatCurrencies = fiatCurrencies;
	}

	public void setCryptoCurrencies(String cryptoCurrencies) {
		this.cryptoCurrencies = cryptoCurrencies;
	}

	public void setBusinessLongitude(String businessLongitude) {
		this.businessLongitude = businessLongitude;
	}

	public void setBusinessLatitude(String businessLatitude) {
		this.businessLatitude = businessLatitude;
	}

	public void setEkyc(String ekyc) {
		this.ekyc = ekyc;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setUserCorrelationId(String userCorrelationId) {
		this.userCorrelationId = userCorrelationId;
	}

}
