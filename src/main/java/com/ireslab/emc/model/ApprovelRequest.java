package com.ireslab.emc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApprovelRequest extends GenericRequest{
	
	private String corellationId;
	private boolean isClient;
	private boolean approvelStatus;
	public String getCorellationId() {
		return corellationId;
	}
	public void setCorellationId(String corellationId) {
		this.corellationId = corellationId;
	}
	public boolean isClient() {
		return isClient;
	}
	public void setClient(boolean isClient) {
		this.isClient = isClient;
	}
	public boolean isApprovelStatus() {
		return approvelStatus;
	}
	public void setApprovelStatus(boolean approvelStatus) {
		this.approvelStatus = approvelStatus;
	}
	
	
	

}
