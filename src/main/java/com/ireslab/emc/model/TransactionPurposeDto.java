/**
 * 
 */
package com.ireslab.emc.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author ireslab
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionPurposeDto implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
    private Integer purposeId;
	
	private String purposeTitle;

	/**
	 * @return the purposeId
	 */
	public Integer getPurposeId() {
		return purposeId;
	}

	/**
	 * @param purposeId the purposeId to set
	 */
	public void setPurposeId(Integer purposeId) {
		this.purposeId = purposeId;
	}

	/**
	 * @return the purposeTitle
	 */
	public String getPurposeTitle() {
		return purposeTitle;
	}

	/**
	 * @param purposeTitle the purposeTitle to set
	 */
	public void setPurposeTitle(String purposeTitle) {
		this.purposeTitle = purposeTitle;
	}

	
	

}
