package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ireslab.emc.dto.TransactionLimitDto;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionLimitResponse extends GenericResponse{
	
	private List<TransactionLimitDto> transactionLimit;

	public List<TransactionLimitDto> getTransactionLimit() {
		return transactionLimit;
	}

	public void setTransactionLimit(List<TransactionLimitDto> transactionLimit) {
		this.transactionLimit = transactionLimit;
	}
	

}
