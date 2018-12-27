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
public class UserRegistrationResponse extends GenericResponse{

	private static final long serialVersionUID = 1L;
	private List<UserProfile> userProfile;
	/**
	 * @return the userProfile
	 */
	public List<UserProfile> getUserProfile() {
		return userProfile;
	}
	/**
	 * @param userProfile the userProfile to set
	 */
	public void setUserProfile(List<UserProfile> userProfile) {
		this.userProfile = userProfile;
	}
	
	
	
}
