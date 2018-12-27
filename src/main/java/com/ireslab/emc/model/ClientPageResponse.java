/**
 * 
 */
package com.ireslab.emc.model;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author ireslab
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientPageResponse extends GenericResponse{

	private static final long serialVersionUID = 1L;
	
	private List<ClientDataDto> pageList;
	
	private String totalPages;
	private String totalElements;
    private String numberOfElements;
    private String size;
    
    

	public String getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}

	public String getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(String totalElements) {
		this.totalElements = totalElements;
	}

	public String getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(String numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<ClientDataDto> getPageList() {
		return pageList;
	}

	public void setPageList(List<ClientDataDto> pageList) {
		this.pageList = pageList;
	}


	

	
}
