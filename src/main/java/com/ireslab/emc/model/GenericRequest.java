package com.ireslab.emc.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Nitin
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6297134432532472057L;
	
	
	@JsonIgnore
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
	 public GenericRequest setClientId(String clientId) {
	  this.clientId = clientId;
	  return this;
	 }

}
