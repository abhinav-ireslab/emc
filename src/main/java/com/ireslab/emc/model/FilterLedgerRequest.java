package com.ireslab.emc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterLedgerRequest extends GenericRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String clientCorrelationId;
	private String filterBy;
	private String filterData;
	private String sourceCorrelationId;
	private String destinationCorrelationId;
	private String sourceAddress;
	private String destinationAddress;
	private String fromDate;
	private String toDate;
	private String dataFormat;
	private boolean offline;
	
	
	
	public String getFilterData() {
		return filterData;
	}
	public void setFilterData(String filterData) {
		this.filterData = filterData;
	}
	public String getClientCorrelationId() {
		return clientCorrelationId;
	}
	public void setClientCorrelationId(String clientCorrelationId) {
		this.clientCorrelationId = clientCorrelationId;
	}
	public String getFilterBy() {
		return filterBy;
	}
	public void setFilterBy(String filterBy) {
		this.filterBy = filterBy;
	}
	public String getSourceCorrelationId() {
		return sourceCorrelationId;
	}
	public void setSourceCorrelationId(String sourceCorrelationId) {
		this.sourceCorrelationId = sourceCorrelationId;
	}
	public String getDestinationCorrelationId() {
		return destinationCorrelationId;
	}
	public void setDestinationCorrelationId(String destinationCorrelationId) {
		this.destinationCorrelationId = destinationCorrelationId;
	}
	public String getSourceAddress() {
		return sourceAddress;
	}
	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}
	public String getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getDataFormat() {
		return dataFormat;
	}
	public void setDataFormat(String dataFormat) {
		this.dataFormat= dataFormat;
	}
	public boolean getOffline() {
		return offline;
	}
	public void setOffline(boolean offline) {
		this.offline = offline;
	}
	
	

}
