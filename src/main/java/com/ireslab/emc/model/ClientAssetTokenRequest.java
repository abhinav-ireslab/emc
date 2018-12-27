package com.ireslab.emc.model;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientAssetTokenRequest extends GenericRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String clientCorrelationId;
    private String tokenCorrelationId;
    private String tokenName;
    private String tokenCode;
    private String tokenDescreption;
    private BigInteger batchQuantity;
    private BigInteger provisionedQuantity;
    private String issuingAccountPublicKey;
    private String issuingAccountSecretKey;
    private String baseTxnAccountPublicKey;
    private String baseTxnAccountSecretKey;
    private String status;
    private Integer createdBy;
    private String createdDate;
    private Integer modifiedBy;
    private Date modifiedDate;
	public String getClientCorrelationId() {
		return clientCorrelationId;
	}
	public void setClientCorrelationId(String clientCorrelationId) {
		this.clientCorrelationId = clientCorrelationId;
	}
	public String getTokenCorrelationId() {
		return tokenCorrelationId;
	}
	public void setTokenCorrelationId(String tokenCorrelationId) {
		this.tokenCorrelationId = tokenCorrelationId;
	}
	public String getTokenName() {
		return tokenName;
	}
	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}
	public String getTokenCode() {
		return tokenCode;
	}
	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}
	public String getTokenDescreption() {
		return tokenDescreption;
	}
	public void setTokenDescreption(String tokenDescreption) {
		this.tokenDescreption = tokenDescreption;
	}
	public BigInteger getBatchQuantity() {
		return batchQuantity;
	}
	public void setBatchQuantity(BigInteger batchQuantity) {
		this.batchQuantity = batchQuantity;
	}
	public BigInteger getProvisionedQuantity() {
		return provisionedQuantity;
	}
	public void setProvisionedQuantity(BigInteger provisionedQuantity) {
		this.provisionedQuantity = provisionedQuantity;
	}
	public String getIssuingAccountPublicKey() {
		return issuingAccountPublicKey;
	}
	public void setIssuingAccountPublicKey(String issuingAccountPublicKey) {
		this.issuingAccountPublicKey = issuingAccountPublicKey;
	}
	public String getIssuingAccountSecretKey() {
		return issuingAccountSecretKey;
	}
	public void setIssuingAccountSecretKey(String issuingAccountSecretKey) {
		this.issuingAccountSecretKey = issuingAccountSecretKey;
	}
	public String getBaseTxnAccountPublicKey() {
		return baseTxnAccountPublicKey;
	}
	public void setBaseTxnAccountPublicKey(String baseTxnAccountPublicKey) {
		this.baseTxnAccountPublicKey = baseTxnAccountPublicKey;
	}
	public String getBaseTxnAccountSecretKey() {
		return baseTxnAccountSecretKey;
	}
	public void setBaseTxnAccountSecretKey(String baseTxnAccountSecretKey) {
		this.baseTxnAccountSecretKey = baseTxnAccountSecretKey;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
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
    
    
    
	

}
