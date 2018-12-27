package com.ireslab.emc.test;

public class ExchangeDto {
	
	private String currency;
	private String currentRate;
	private String exchangeRate;
	
	
	
	
	public ExchangeDto(String currency, String currentRate, String exchangeRate) {
		super();
		this.currency = currency;
		this.currentRate = currentRate;
		this.exchangeRate = exchangeRate;
	}
	public ExchangeDto() {
		// TODO Auto-generated constructor stub
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrentRate() {
		return currentRate;
	}
	public void setCurrentRate(String currentRate) {
		this.currentRate = currentRate;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	@Override
	public String toString() {
		return "" + currency + "," + currentRate + "," + exchangeRate
				+ "";
	}
	
	

}
