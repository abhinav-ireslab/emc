package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientUsersInfoResponse extends GenericResponse{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3356784083429945190L;

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

	@Override
	public String toString() {
		return "ClientUsersInfoResponse [users=" + users + ", getUsers()=" + getUsers() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

}
