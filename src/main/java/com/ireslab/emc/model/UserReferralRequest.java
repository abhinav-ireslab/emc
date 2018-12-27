package com.ireslab.emc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserReferralRequest extends GenericRequest{
	
	private static final long serialVersionUID = 1L;
	private String clientCorrelationId;
	private List<String> emailList;
	private List<String> mobileList;
	private String subscriptionDurations;
	
	
	public String getSubscriptionDurations() {
		return subscriptionDurations;
	}
	public void setSubscriptionDurations(String subscriptionDurations) {
		this.subscriptionDurations = subscriptionDurations;
	}
	public List<String> getMobileList() {
		return mobileList;
	}
	public void setMobileList(List<String> mobileList) {
		this.mobileList = mobileList;
	}
	public String getClientCorrelationId() {
		return clientCorrelationId;
	}
	public void setClientCorrelationId(String clientCorrelationId) {
		this.clientCorrelationId = clientCorrelationId;
	}
	public List<String> getEmailList() {
		return emailList;
	}
	public void setEmailList(List<String> emailList) {
		this.emailList = emailList;
	}
}
