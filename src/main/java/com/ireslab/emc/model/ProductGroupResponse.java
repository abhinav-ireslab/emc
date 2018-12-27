package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ireslab.emc.dto.ProductGroupDto;

@JsonInclude(value = Include.NON_NULL) 
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductGroupResponse extends GenericResponse{
	private static final long serialVersionUID = 1L;
	
	private List<ProductGroupDto> productGroups;
 	
	public List<ProductGroupDto> getProductGroups() {
		return productGroups;
	}
	public void setProductGroups(List<ProductGroupDto> productGroups) { 
		this.productGroups = productGroups;
	}
}
