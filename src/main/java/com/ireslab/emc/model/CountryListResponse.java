package com.ireslab.emc.model;

import java.util.List;

public class CountryListResponse extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<CountryDetails> countryDetails;
	
    private Integer subscriptionPlan;
	
	private Integer subscribedUser;
	
	private Integer remainingInvitation;

	/**
	 * @return the countryDetails
	 */
	public List<CountryDetails> getCountryDetails() {
		return countryDetails;
	}

	/**
	 * @param countryDetails the countryDetails to set
	 */
	public void setCountryDetails(List<CountryDetails> countryDetails) {
		this.countryDetails = countryDetails;
	}

	/**
	 * @return the subscriptionPlan
	 */
	public Integer getSubscriptionPlan() {
		return subscriptionPlan;
	}

	/**
	 * @param subscriptionPlan the subscriptionPlan to set
	 */
	public void setSubscriptionPlan(Integer subscriptionPlan) {
		this.subscriptionPlan = subscriptionPlan;
	}

	/**
	 * @return the subscribedUser
	 */
	public Integer getSubscribedUser() {
		return subscribedUser;
	}

	/**
	 * @param subscribedUser the subscribedUser to set
	 */
	public void setSubscribedUser(Integer subscribedUser) {
		this.subscribedUser = subscribedUser;
	}

	/**
	 * @return the remainingInvitation
	 */
	public Integer getRemainingInvitation() {
		return remainingInvitation;
	}

	/**
	 * @param remainingInvitation the remainingInvitation to set
	 */
	public void setRemainingInvitation(Integer remainingInvitation) {
		this.remainingInvitation = remainingInvitation;
	}

	

	
}
