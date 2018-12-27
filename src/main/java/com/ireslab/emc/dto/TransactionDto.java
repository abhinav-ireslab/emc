package com.ireslab.emc.dto;

import java.text.DecimalFormat;

import com.ireslab.emc.util.TokenActivity;
import com.ireslab.emc.util.TokenOperation;

public class TransactionDto {
	
	
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
	private String destinationCorrelationId; // (User correlation id, Client Correllation id)
	private short status; // 0-pending,1-success,2-failed
	private String tnxData;
	private String assetCode;
	private String tnxHash;
	private String transactionXdr;
	private String transactionDate;
	private String currencySymbol;
	
	
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
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	/*@Override 
	public String toString() {
		return "" + transactionSequenceNo + ";" + operation
				+ ";" +transactionDate + ";" +sourceAccountName 
				+ ";" +destinationAccountName  + ";" + String.format("%.2f", new Double(tnxData)) + ";" + tnxHash + "";
	}*/
	
	@Override 
	public String toString() {
		return "" + transactionSequenceNo + ";" + operation
				+ ";" +transactionDate + ";" +sourceAccountName 
				+ ";" +destinationAccountName  + ";" + String.format("%.2f", new Double(tnxData == null?"0":tnxData)) + "";
	}
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	
	

}
