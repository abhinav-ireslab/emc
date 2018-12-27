package com.ireslab.emc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * @author Nitin
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericResponse implements Serializable {

	private static final long serialVersionUID = 4519741788165974557L;

	private Integer status;
	private Integer code;
	private String message;
	private String developerMessage;
	private String moreInfo;
	private List<Error> errors =new ArrayList<>();
	
	private String clientId;

	 /**
	  * @return the clientId
	  */
	 public String getClientId() {
	  return clientId;
	 }

	 /**
	  * @param clientId
	  *            the clientId to set
	  */
	 public GenericResponse setClientId(String clientId) {
	  this.clientId = clientId;
	  return this;
	 }
	
	/* Error list */
	

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public GenericResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenericResponse(Integer status, Integer code, String message) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the developerMessage
	 */
	public String getDeveloperMessage() {
		return developerMessage;
	}

	/**
	 * @param developerMessage
	 *            the developerMessage to set
	 */
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	/**
	 * @return the moreInfo
	 */
	public String getMoreInfo() {
		return moreInfo;
	}

	/**
	 * @param moreInfo
	 *            the moreInfo to set
	 */
	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

}
