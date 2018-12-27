package com.ireslab.emc.dto;

import java.util.Date;

public class TransactionLimitDto {

	
	
	private Integer transactionLimitId;
	
	
	private String dailyLimit;
	
	
	private String transactionsPerDay;
	
	
	private String monthlyLimit;
	
	
	private Date modifiedDate;

	public Integer getTransactionLimitId() {
		return transactionLimitId;
	}

	public void setTransactionLimitId(Integer transactionLimitId) {
		this.transactionLimitId = transactionLimitId;
	}

	public String getDailyLimit() {
		return dailyLimit;
	}

	public void setDailyLimit(String dailyLimit) {
		this.dailyLimit = dailyLimit;
	}

	public String getTransactionsPerDay() {
		return transactionsPerDay;
	}

	public void setTransactionsPerDay(String transactionsPerDay) {
		this.transactionsPerDay = transactionsPerDay;
	}

	public String getMonthlyLimit() {
		return monthlyLimit;
	}

	public void setMonthlyLimit(String monthlyLimit) {
		this.monthlyLimit = monthlyLimit;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "TransactionLimit [transactionLimitId=" + transactionLimitId + ", dailyLimit=" + dailyLimit
				+ ", transactionsPerDay=" + transactionsPerDay + ", monthlyLimit=" + monthlyLimit + ", modifiedDate="
				+ modifiedDate + "]";
	}
}
