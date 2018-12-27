package com.ireslab.emc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ireslab.emc.model.ClientUsersInfoResponse;


@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoadTokensResponse extends GenericResponse {

	private String accountBalance;
	
	private ClientUsersInfoResponse clientUsersInfoResponse;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3519410235099264089L;

	public LoadTokensResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param status
	 * @param code
	 * @param message
	 */
	public LoadTokensResponse(Integer status, Integer code, String message) {
		super(status, code, message);
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @param status
	 * @param code
	 * @param message
	 * @param accountBalance
	 */
	public LoadTokensResponse(Integer status, Integer code, String message, String accountBalance) {
		super(status, code, message);
		this.accountBalance = accountBalance;
	}
	
	
	public LoadTokensResponse(Integer status, Integer code, String message, ClientUsersInfoResponse clientUsersInfoResponse) {
		super(status, code, message);
		this.setClientUsersInfoResponse(clientUsersInfoResponse);
	}

	/**
	 * @return the accountBalance
	 */
	public String getAccountBalance() {
		return accountBalance;
	}

	/**
	 * @param accountBalance
	 *            the accountBalance to set
	 */
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

	public ClientUsersInfoResponse getClientUsersInfoResponse() {
		return clientUsersInfoResponse;
	}

	public void setClientUsersInfoResponse(ClientUsersInfoResponse clientUsersInfoResponse) {
		this.clientUsersInfoResponse = clientUsersInfoResponse;
	}

	@Override
	public String toString() {
		return "LoadTokensResponse [accountBalance=" + accountBalance + ", clientUsersInfoResponse="
				+ clientUsersInfoResponse + "]";
	}
	
	
	
}
