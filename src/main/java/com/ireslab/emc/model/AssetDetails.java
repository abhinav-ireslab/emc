package com.ireslab.emc.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tokenCorrelationId;

	private String tokenDesc;

	private String assetCode;

	private String assetQuantity;

	private String limit;

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
	public AssetDetails setTokenCorrelationId(String tokenCorrelationId) {
		this.tokenCorrelationId = tokenCorrelationId;
		return this;
	}

	/**
	 * @return the tokenDesc
	 */
	public String getTokenDesc() {
		return tokenDesc;
	}

	/**
	 * @param tokenDesc
	 *            the tokenDesc to set
	 */
	public AssetDetails setTokenDesc(String tokenDesc) {
		this.tokenDesc = tokenDesc;
		return this;
	}

	/**
	 * @return the assetCode
	 */
	public String getAssetCode() {
		return assetCode;
	}

	/**
	 * @param assetCode
	 *            the assetCode to set
	 */
	public AssetDetails setAssetCode(String assetCode) {
		this.assetCode = assetCode;
		return this;
	}

	/**
	 * @return the accountBalance
	 */
	public String getAssetQuantity() {
		return assetQuantity;
	}

	/**
	 * @param assetQuantity
	 *            the accountBalance to set
	 */
	public AssetDetails setAssetQuantity(String assetQuantity) {
		this.assetQuantity = assetQuantity;
		return this;
	}

	/**
	 * @return the limit
	 */
	public String getLimit() {
		return limit;
	}

	/**
	 * @param limit
	 *            the limit to set
	 */
	public AssetDetails setLimit(String limit) {
		this.limit = limit;
		return this;
	}
}
