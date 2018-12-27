package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ireslab.emc.dto.EkycEkybApprovelDto;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApprovelResponse extends GenericResponse{
	
	List<EkycEkybApprovelDto> clientEkycEkybApprovelList;
	List<EkycEkybApprovelDto> userEkycEkybApprovelList;
	List<EkycEkybApprovelDto> ekycEkybApprovelList;
	
	
	public List<EkycEkybApprovelDto> getEkycEkybApprovelList() {
		return ekycEkybApprovelList;
	}
	public void setEkycEkybApprovelList(List<EkycEkybApprovelDto> ekycEkybApprovelList) {
		this.ekycEkybApprovelList = ekycEkybApprovelList;
	}
	public List<EkycEkybApprovelDto> getClientEkycEkybApprovelList() {
		return clientEkycEkybApprovelList;
	}
	public void setClientEkycEkybApprovelList(List<EkycEkybApprovelDto> clientEkycEkybApprovelList) {
		this.clientEkycEkybApprovelList = clientEkycEkybApprovelList;
	}
	public List<EkycEkybApprovelDto> getUserEkycEkybApprovelList() {
		return userEkycEkybApprovelList;
	}
	public void setUserEkycEkybApprovelList(List<EkycEkybApprovelDto> userEkycEkybApprovelList) {
		this.userEkycEkybApprovelList = userEkycEkybApprovelList;
	}

	
	
	
	
	

}
