package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ireslab.emc.model.GenericRequest;


@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientRegistrationRequest extends GenericRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ClientProfile> clientProfile;

	public List<ClientProfile> getClientProfile() {
		return clientProfile;
	}

	public void setClientProfile(List<ClientProfile> clientProfile) {
		this.clientProfile = clientProfile;
	}
	
	

}
