/**
 * 
 */
package com.ireslab.emc.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAgentRequest implements Serializable {

	private static final long serialVersionUID = 1920741613515047771L;
	private Long agentMobNo;
	private String locateAgent;

	private String idProofImageValue;
	private String businessId;
	private String fiatCurrency;
	private String cryptoCurrency;
	private String businessAdd;
	private String businessLong;
	private String businessLat;
	private String countryDialCode;
	private String correlationId;
	private String status;

	public UserAgentRequest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the agentMobNo
	 */
	public Long getAgentMobNo() {
		return agentMobNo;
	}

	/**
	 * @return the locateAgent
	 */
	public String getLocateAgent() {
		return locateAgent;
	}

	/**
	 * @return the idCardImage
	 */
	public String getIdProofImageValue() {
		return idProofImageValue;
	}

	/**
	 * @return the businessId
	 */
	public String getBusinessId() {
		return businessId;
	}

	/**
	 * @return the fiatCurrency
	 */
	public String getFiatCurrency() {
		return fiatCurrency;
	}

	/**
	 * @return the cryptoCurrency
	 */
	public String getCryptoCurrency() {
		return cryptoCurrency;
	}

	/**
	 * @return the businessAdd
	 */
	public String getBusinessAdd() {
		return businessAdd;
	}

	/**
	 * @return the businessLong
	 */
	public String getBusinessLong() {
		return businessLong;
	}

	/**
	 * @return the businessLat
	 */
	public String getBusinessLat() {
		return businessLat;
	}

	/**
	 * @return the countryDialCode
	 */
	public String getCountryDialCode() {
		return countryDialCode;
	}

	/**
	 * @return the correlationId
	 */
	public String getCorrelationId() {
		return correlationId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param agentMobNo
	 *            the agentMobNo to set
	 */
	public void setAgentMobNo(Long agentMobNo) {
		this.agentMobNo = agentMobNo;
	}

	/**
	 * @param locateAgent
	 *            the locateAgent to set
	 */
	public void setLocateAgent(String locateAgent) {
		this.locateAgent = locateAgent;
	}

	/**
	 * @param idProofImageValue
	 *            the idCardImage to set
	 */
	public void setIdProofImageValue(String idProofImageValue) {
		this.idProofImageValue = idProofImageValue;
	}

	/**
	 * @param businessId
	 *            the businessId to set
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	/**
	 * @param fiatCurrency
	 *            the fiatCurrency to set
	 */
	public void setFiatCurrency(String fiatCurrency) {
		this.fiatCurrency = fiatCurrency;
	}

	/**
	 * @param cryptoCurrency
	 *            the cryptoCurrency to set
	 */
	public void setCryptoCurrency(String cryptoCurrency) {
		this.cryptoCurrency = cryptoCurrency;
	}

	/**
	 * @param businessAdd
	 *            the businessAdd to set
	 */
	public void setBusinessAdd(String businessAdd) {
		this.businessAdd = businessAdd;
	}

	/**
	 * @param businessLong
	 *            the businessLong to set
	 */
	public void setBusinessLong(String businessLong) {
		this.businessLong = businessLong;
	}

	/**
	 * @param businessLat
	 *            the businessLat to set
	 */
	public void setBusinessLat(String businessLat) {
		this.businessLat = businessLat;
	}

	/**
	 * @param countryDialCode
	 *            the countryDialCode to set
	 */
	public void setCountryDialCode(String countryDialCode) {
		this.countryDialCode = countryDialCode;
	}

	/**
	 * @param correlationId
	 *            the correlationId to set
	 */
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserAgentRequest [agentMobNo=" + agentMobNo + ", locateAgent=" + locateAgent + ", businessId="
				+ businessId + ", fiatCurrency=" + fiatCurrency + ", cryptoCurrency=" + cryptoCurrency
				+ ", businessAdd=" + businessAdd + ", businessLong=" + businessLong + ", businessLat=" + businessLat
				+ ", countryDialCode=" + countryDialCode + ", correlationId=" + correlationId + ", status=" + status
				+ "]";
	}
}
