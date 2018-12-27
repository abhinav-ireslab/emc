/**
 * 
 */
package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ireslab.sendx.electra.dto.GSTChapterDto;
import com.ireslab.sendx.electra.model.GenericResponse;

/**
 * @author Sachin
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GstHsnCodeLoadResponse extends GenericResponse{

	private static final long serialVersionUID = 1L;
	
	private List<GstHsnCode> gstHsnCode;

	public List<GstHsnCode> getGstHsnCode() {
		return gstHsnCode;
	}

	public void setGstHsnCode(List<GstHsnCode> gstHsnCode) {
		this.gstHsnCode = gstHsnCode;
	}

}
