package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientInfoResponse extends GenericResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<AccountDetails> clients;

	public List<AccountDetails> getClients() {
		return clients;
	}

	public void setClients(List<AccountDetails> clients) {
		this.clients = clients;
	}
	
	

}
