/**
 * 
 */
package com.ireslab.emc.model;

import java.util.List;

/**
 * @author ireslab
 *
 */
public class TransactionPurposeResponse extends GenericResponse{

	
	private static final long serialVersionUID = 1L;
	
	List<TransactionPurposeDto> purposeList;

	public List<TransactionPurposeDto> getPurposeList() {
		return purposeList;
	}

	public void setPurposeList(List<TransactionPurposeDto> purposeList) {
		this.purposeList = purposeList;
	}

}
