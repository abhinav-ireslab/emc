package com.ireslab.emc.exception;



import java.util.List;

import org.springframework.http.HttpStatus;




/**
 * @author Nitin
 *
 */
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String refId;
	private HttpStatus httpStatus;

	private Integer code;
	private String msg;

	private List<Error> errors;

	public ApiException() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public ApiException(String msg){
		super();
		this.msg = msg;
	}
	
	
	
	/**
	 * @param message
	 * @param cause
	 */
	public ApiException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param httpStatus
	 * @param code
	 * @param message
	 * @param errors
	 */
	public ApiException(HttpStatus httpStatus, Integer code, String message, List<Error> errors) {
		super();
		this.httpStatus = httpStatus;
		this.code = code;
		this.msg = message;
		this.errors = errors;
	}

	/**
	 * @param httpStatus
	 * @param code
	 * @param errors
	 */
	public ApiException(HttpStatus httpStatus, Integer code, List<Error> errors) {
		super();
		this.httpStatus = httpStatus;
		this.code = code;
		this.errors = errors;
	}
	/**
	 * 
	 * @param httpStatus
	 * @param errors
	 */
	public ApiException(HttpStatus httpStatus, List<Error> errors) {
		super();
		this.httpStatus = httpStatus;
		this.errors = errors;
	}

	/**
	 * @param refId
	 * @param httpStatus
	 * @param code
	 * @param message
	 * @param errors
	 */
	public ApiException(String refId, HttpStatus httpStatus, Integer code, String message, List<Error> errors) {
		super();
		this.refId = refId;
		this.httpStatus = httpStatus;
		this.code = code;
		this.msg = message;
		this.errors = errors;
	}

	/**
	 * @return the refId
	 */
	public String getRefId() {
		return refId;
	}

	/**
	 * @param refId
	 *            the refId to set
	 */
	public void setRefId(String refId) {
		this.refId = refId;
	}

	/**
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * @param httpStatus
	 *            the httpStatus to set
	 */
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
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
	
	/**
	 * @return the errors
	 */
	public List<Error> getErrors() {
		return errors;
	}

	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	/**
	 * @param errors
	 *            the errors to set
	 */
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
}
