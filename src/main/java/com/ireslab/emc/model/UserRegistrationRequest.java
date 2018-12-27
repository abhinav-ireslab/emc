/**
 * 
 */
package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author User
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegistrationRequest extends GenericRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<UserProfile> users;

	/**
	 * @return the users
	 */
	public List<UserProfile> getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(List<UserProfile> users) {
		this.users = users;
	}
}
