package com.ireslab.emc.service;

import com.ireslab.emc.model.LedgerResponse;

public interface UserLedgerService {
	
	LedgerResponse requestAssetsJson(String accountID);
	LedgerResponse requestPaymentJson(String accountID,LedgerResponse ledgerResponse,String next,String limit);
	
	
	
	
	

}
