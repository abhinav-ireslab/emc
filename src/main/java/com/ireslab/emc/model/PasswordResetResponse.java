package com.ireslab.emc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordResetResponse extends GenericResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int passwordResetcode;
	private String passwordResetmessage;
	
	private String passwordResetInfo;

	public int getPasswordResetcode() {
		return passwordResetcode;
	}

	public void setPasswordResetcode(int passwordResetcode) {
		this.passwordResetcode = passwordResetcode;
	}

	public String getPasswordResetmessage() {
		return passwordResetmessage;
	}

	public void setPasswordResetmessage(String passwordResetmessage) {
		this.passwordResetmessage = passwordResetmessage;
	}

	public String getPasswordResetInfo() {
		return passwordResetInfo;
	}

	public void setPasswordResetInfo(String passwordResetInfo) {
		this.passwordResetInfo = passwordResetInfo;
	}

	
}
