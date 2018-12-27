/**
 * 
 */
package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
//import com.ireslab.sendx.electra.model.GenericResponse;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAgentRegistrationResponse extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3356784083429945190L;

	private List<AgentResponse> agents;
	

	
	public List<AgentResponse> getAgents() {
		return agents;
	}



	public void setAgents(List<AgentResponse> agents) {
		this.agents = agents;
	}



	public UserAgentRegistrationResponse() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	/*public UserAgentRegistrationResponse(Integer status, Integer code, String message, List<Error> errors) {
		super(status, code, message, errors);
		// TODO Auto-generated constructor stub
	}

	public UserAgentRegistrationResponse(String refId, Integer status, Integer code, String message, List<Error> errors) {
		super(refId, status, code, message, errors);
		// TODO Auto-generated constructor stub
	}

	public UserAgentRegistrationResponse(String refId, List<Error> errors) {
		super(refId, errors);
		// TODO Auto-generated constructor stub
	}*/

}
