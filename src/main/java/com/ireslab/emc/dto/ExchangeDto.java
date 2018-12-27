package com.ireslab.emc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ireslab.emc.model.GenericRequest;
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeDto extends GenericRequest{
	private Integer exchangeId;
    private String exchangeToken;
	private String nativeCurrency;
	private String exchangeRate;
	private String transferFee;
	private boolean isCryptocurrency;
	
	private String widthrawlFee;
	
	private String clientCorrelationId;
	
	
	
	
	
	
	public ExchangeDto() {
		
	}
	/*public ExchangeDto(String exchangeToken, String nativeCurrency, String exchangeRate, String transferFee) {
		super();
		this.exchangeToken = exchangeToken;
		this.nativeCurrency = nativeCurrency;
		this.exchangeRate = exchangeRate;
		this.transferFee = transferFee;
	}*/
	public int getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(Integer exchangeId) {
		this.exchangeId = exchangeId;
	}
	public String getClientCorrelationId() {
		return clientCorrelationId;
	}
	public void setClientCorrelationId(String clientCorrelationId) {
		this.clientCorrelationId = clientCorrelationId;
	}
	public boolean isCryptocurrency() {
		return isCryptocurrency;
	}
	public void setCryptocurrency(boolean isCryptocurrency) {
		this.isCryptocurrency = isCryptocurrency;
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
	@Override
	public String toString() {
		return "ExchangeDto [exchangeId=" + exchangeId + ", exchangeToken=" + exchangeToken + ", nativeCurrency="
				+ nativeCurrency + ", exchangeRate=" + exchangeRate + ", transferFee=" + transferFee
				+ ", isCryptocurrency=" + isCryptocurrency + ", clientCorrelationId=" + clientCorrelationId + "]";
	}
	public String getWidthrawlFee() {
		return widthrawlFee;
	}
	public void setWidthrawlFee(String widthrawlFee) {
		this.widthrawlFee = widthrawlFee;
	}
	

}
