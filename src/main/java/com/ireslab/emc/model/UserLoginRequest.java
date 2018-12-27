package com.ireslab.emc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoginRequest extends GenericRequest{
	private static final long serialVersionUID = 4422116507795747624L;
	
	private UserProfile users;

	public UserProfile getUsers() {
		return users;
	}

	public void setUsers(UserProfile users) {
		this.users = users;
	}

}
