package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ireslab.emc.dto.ProductDto;
 
@JsonInclude(value = Include.NON_NULL) 
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse extends GenericResponse{
	private static final long serialVersionUID = 1L;
	
	private List<ProductDto> productDetails;
	 
	
	public List<ProductDto> getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(List<ProductDto> productDetails) {
		this.productDetails = productDetails;
	}
	 
	
	
	
	

}
