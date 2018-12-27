package com.ireslab.emc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveProductResponse extends GenericResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String success;
	String message;
	String code;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReqResCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
