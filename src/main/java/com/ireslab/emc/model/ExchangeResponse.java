package com.ireslab.emc.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeResponse extends GenericResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ExchangeDto> exchangeList;
	
    private Set<String> currencyList;
	
	
	
	

	public Set<String> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(Set<String> currencyList) {
		this.currencyList = currencyList;
	}

	public List<ExchangeDto> getExchangeList() {
		return exchangeList;
	}

	public void setExchangeList(List<ExchangeDto> exchangeList) {
		this.exchangeList = exchangeList;
	}
	

}
