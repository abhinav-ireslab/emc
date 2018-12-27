package com.ireslab.emc.model;

import java.io.Serializable;
import java.util.Date;

import com.ireslab.emc.util.TokenActivity;
import com.ireslab.emc.util.TokenOperation;

/**
 * The persistent class for the transaction_details database table.
 * 
 */

public class TransactionDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer transactionDetailId;
	private String transactionSequenceNo;
	private TokenOperation operation; // payment,change_trust,create account -stellar
	private TokenActivity type; // LOAD TOKEN, ISSUE TOKEN, CREATE ACCOUNT
	private String stellarRequest;
	private String stellarResponse;
	private String sourceAccountName;
	private boolean isSourceAccountUser;
	private String sourceCorrelationId; // (User correlation id, Client Correllation id)
	private String destinationAccountName;
	private boolean isDestinationAccountUser;
	private String destinationCorrelationId; // (User correlation id, Client Correllation id
	private short status; // 0-pending,1-success,2-failed
	private String tnxData;
	private String assetCode;
	private String tnxHash;
	private String transactionXdr;
	private Date transactionDate;
	private Date modifiedDate;

	public TransactionDetail() {
	}

	public Integer getTransactionDetailId() {
		return transactionDetailId;
	}

	public void setTransactionDetailId(Integer transactionDetailId) {
		this.transactionDetailId = transactionDetailId;
	}

	public String getTransactionSequenceNo() {
		return transactionSequenceNo;
	}

	public void setTransactionSequenceNo(String transactionSequenceNo) {
		this.transactionSequenceNo = transactionSequenceNo;
	}

	public TokenOperation getOperation() {
		return operation;
	}

	public void setOperation(TokenOperation operation) {
		this.operation = operation;
	}

	public TokenActivity getType() {
		return type;
	}

	public void setType(TokenActivity type) {
		this.type = type;
	}

	public String getStellarRequest() {
		return stellarRequest;
	}

	public void setStellarRequest(String stellarRequest) {
		this.stellarRequest = stellarRequest;
	}

	public String getStellarResponse() {
		return stellarResponse;
	}

	public void setStellarResponse(String stellarResponse) {
		this.stellarResponse = stellarResponse;
	}

	public String getSourceAccountName() {
		return sourceAccountName;
	}

	public void setSourceAccountName(String sourceAccountName) {
		this.sourceAccountName = sourceAccountName;
	}

	public boolean isSourceAccountUser() {
		return isSourceAccountUser;
	}

	public void setSourceAccountUser(boolean isSourceAccountUser) {
		this.isSourceAccountUser = isSourceAccountUser;
	}

	public String getSourceCorrelationId() {
		return sourceCorrelationId;
	}

	public void setSourceCorrelationId(String sourceCorrelationId) {
		this.sourceCorrelationId = sourceCorrelationId;
	}

	public String getDestinationAccountName() {
		return destinationAccountName;
	}

	public void setDestinationAccountName(String destinationAccountName) {
		this.destinationAccountName = destinationAccountName;
	}

	public boolean isDestinationAccountUser() {
		return isDestinationAccountUser;
	}

	public void setDestinationAccountUser(boolean isDestinationAccountUser) {
		this.isDestinationAccountUser = isDestinationAccountUser;
	}

	public String getDestinationCorrelationId() {
		return destinationCorrelationId;
	}

	public void setDestinationCorrelationId(String destinationCorrelationId) {
		this.destinationCorrelationId = destinationCorrelationId;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getTnxData() {
		return tnxData;
	}

	public void setTnxData(String tnxData) {
		this.tnxData = tnxData;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getTnxHash() {
		return tnxHash;
	}

	public void setTnxHash(String tnxHash) {
		this.tnxHash = tnxHash;
	}

	public String getTransactionXdr() {
		return transactionXdr;
	}

	public void setTransactionXdr(String transactionXdr) {
		this.transactionXdr = transactionXdr;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}