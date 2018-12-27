/**
 * 
 */
package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAgentRegistrationRequest extends GenericRequest {

	private static final long serialVersionUID = 4422116507795747624L;

	private List<UserAgentRequest> agents;

	/**
	 * @return the agents
	 */
	public List<UserAgentRequest> getAgents() {
		return agents;
	}

	/**
	 * @param agents
	 *            the agents to set
	 */
	public void setAgents(List<UserAgentRequest> agents) {
		this.agents = agents;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserAgentRegistrationRequest [agents=" + agents + "]";
	}
}
