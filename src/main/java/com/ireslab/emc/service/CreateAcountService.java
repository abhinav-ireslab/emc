/**
 * 
 */
package com.ireslab.emc.service;

import java.util.List;
import java.util.Map;

import com.ireslab.emc.model.AccountDetails;
import com.ireslab.emc.model.ClientRegistrationResponse;

/**
 * @author Akhilesh
 *
 */
public interface CreateAcountService {
	Map<String, String> addAccount(AccountDetails accountDetails);

	List<AccountDetails> getAllAccountDetails();
	
	ClientRegistrationResponse updateAccount(AccountDetails accountDetails);

	/**
	 * @param client_correlation_id
	 * @return
	 */
	AccountDetails getAccount(String client_correlation_id); 
	
}
