package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ireslab.emc.dto.ClientAgentInvitationDto;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClentAgentInvitationRequest extends GenericRequest{
	
	private String invocationType;
	private String send;
	private String subscriptionDurations;
	
	private List<ClientAgentInvitationDto> clientAgentInvitationList;
	
	
	public String getSubscriptionDurations() {
		return subscriptionDurations;
	}

	public void setSubscriptionDurations(String subscriptionDurations) {
		this.subscriptionDurations = subscriptionDurations;
	}

	public List<ClientAgentInvitationDto> getClientAgentInvitationList() {
		return clientAgentInvitationList;
	}

	public void setClientAgentInvitationList(List<ClientAgentInvitationDto> clientAgentInvitationList) {
		this.clientAgentInvitationList = clientAgentInvitationList;
	}

	public String getInvocationType() {
		return invocationType;
	}

	public void setInvocationType(String invocationType) {
		this.invocationType = invocationType;
	}
	
	/**
	 * @return the send
	 */
	public String getSend() {
		return send;
	}

	/**
	 * @param send the send to set
	 */
	public void setSend(String send) {
		this.send = send;
	}
	
	

}
