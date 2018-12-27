package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientAssetTokenResponse extends GenericResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	List<ClientAssetTokenRequest> assetTokenRequestsList;
	
    private String NumberOfElements;
    
    private String TotalPages;
	
	private String TotalElements;
	
	public String getNumberOfElements() {
		return NumberOfElements;
	}

	public void setNumberOfElements(String numberOfElements) {
		NumberOfElements = numberOfElements;
	}

	public String getTotalPages() {
		return TotalPages;
	}

	public void setTotalPages(String totalPages) {
		TotalPages = totalPages;
	}

	public String getTotalElements() {
		return TotalElements;
	}

	public void setTotalElements(String totalElements) {
		TotalElements = totalElements;
	}

	

	public List<ClientAssetTokenRequest> getAssetTokenRequestsList() {
		return assetTokenRequestsList;
	}

	public void setAssetTokenRequestsList(List<ClientAssetTokenRequest> assetTokenRequestsList) {
		this.assetTokenRequestsList = assetTokenRequestsList;
	}
	
	

}
