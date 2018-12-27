/**
 * 
 */
package com.ireslab.emc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author ireslab
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionPurposeRequest extends GenericRequest{

	private static final long serialVersionUID = 1L;

    private Integer purposeId;
	
	private String purposeTitle;

	public Integer getPurposeId() {
		return purposeId;
	}

	public void setPurposeId(Integer purposeId) {
		this.purposeId = purposeId;
	}

	public String getPurposeTitle() {
		return purposeTitle;
	}

	public void setPurposeTitle(String purposeTitle) {
		this.purposeTitle = purposeTitle;
	}
	
	
}
