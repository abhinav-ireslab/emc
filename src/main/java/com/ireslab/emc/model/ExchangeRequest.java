package com.ireslab.emc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRequest extends GenericRequest{
	 private String exchangeToken;
		private String nativeCurrency;
		private String exchangeRate;
		private String transferFee;
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
		
	

}
