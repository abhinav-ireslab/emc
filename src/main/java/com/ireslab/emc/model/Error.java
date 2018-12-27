package com.ireslab.emc.model;

/**
 * @author Nitin
 *
 */
public class Error {

	/* Application Status Codes */
	private Integer code;
    private String message;
	private String description;
	private String moreInfo;

	public Error() {
		 }
	
	public Error(String issue, String description, Integer code) {
		super();
		this.message = issue;
		this.description = description;
		this.code=code;
	}

	/**
	 * @param message
	 * @param description
	 */
	public Error(String message, String description) {
		super();
		this.message = message;
		this.description = description;
	}
	
	public Error(Integer code, String message, String description, String moreInfo) {
		  super();
		  this.code = code;
		  this.message = message;
		  this.description = description;
		  this.moreInfo = moreInfo;
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
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the developerMessage to set
	 */
	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "Error [code=" + code + ", message=" + message + ", description=" + description + ", moreInfo="
				+ moreInfo + "]";
	}	
	

}
