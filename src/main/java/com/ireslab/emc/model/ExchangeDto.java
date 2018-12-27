package com.ireslab.emc.model;

public class ExchangeDto {
	private int exchangeId;
    private String exchangeToken;
	private String nativeCurrency;
	private String exchangeRate;
	private String transferFee;
	
	private String widthrawlFee;
	
	
	
	public int getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(int exchangeId) {
		this.exchangeId = exchangeId;
	}
	public String getExchangeToken() {
		return exchangeToken;
	}
	public String getNativeCurrency() {
		return nativeCurrency;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public String getTransferFee() {
		return transferFee;
	}
	public void setExchangeToken(String exchangeToken) {
		this.exchangeToken = exchangeToken;
	}
	public void setNativeCurrency(String nativeCurrency) {
		this.nativeCurrency = nativeCurrency;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public void setTransferFee(String transferFee) {
		this.transferFee = transferFee;
	}
	public String getWidthrawlFee() {
		return widthrawlFee;
	}
	public void setWidthrawlFee(String widthrawlFee) {
		this.widthrawlFee = widthrawlFee;
	}
	

}
