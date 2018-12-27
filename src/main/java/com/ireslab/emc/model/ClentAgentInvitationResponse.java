package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ireslab.emc.dto.ClientAgentInvitationDto;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClentAgentInvitationResponse extends GenericResponse{
	
	private List<ClientAgentInvitationDto> clientAgentInvitationList;

	public List<ClientAgentInvitationDto> getClientAgentInvitationList() {
		return clientAgentInvitationList;
	}

	public void setClientAgentInvitationList(List<ClientAgentInvitationDto> clientAgentInvitationList) {
		this.clientAgentInvitationList = clientAgentInvitationList;
	}
	
	

}
