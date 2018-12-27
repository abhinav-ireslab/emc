package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientRegistrationResponse extends GenericResponse{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ClientProfile> clients;
	
	private List<AccountDetails> accountDetails;



	public List<AccountDetails> getAccountDetails() {
		return accountDetails;
	}



	public void setAccountDetails(List<AccountDetails> accountDetails) {
		this.accountDetails = accountDetails;
	}



	public List<ClientProfile> getClients() {
		return clients;
	}



	public void setClients(List<ClientProfile> clients) {
		this.clients = clients;
	}
	
	
	

}
