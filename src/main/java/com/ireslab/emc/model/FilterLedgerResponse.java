package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ireslab.emc.dto.TransactionDto;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterLedgerResponse extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<TransactionDto> transactionList;
	
	

	private String NumberOfElements;

	private String TotalPages;

	private String TotalElements;
	
	private String currencySymbol;
	

	

	public String getNumberOfElements() {
		return NumberOfElements;
	}

	public void setNumberOfElements(String numberOfElements) {
		NumberOfElements = numberOfElements;
	}

	public String getTotalPages() {
		return TotalPages;
	}

	public void setTotalPages(String totalPages) {
		TotalPages = totalPages;
	}

	public String getTotalElements() {
		return TotalElements;
	}

	public void setTotalElements(String totalElements) {
		TotalElements = totalElements;
	}

	public List<TransactionDto> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<TransactionDto> transactionList) {
		this.transactionList = transactionList;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	

	

	/*
	 * Page<TransactionDetail> transactionList;
	 * 
	 * public Page<TransactionDetail> getTransactionList() { return transactionList;
	 * }
	 * 
	 * public void setTransactionList(Page<TransactionDetail> transactionList) {
	 * this.transactionList = transactionList; }
	 */

}
