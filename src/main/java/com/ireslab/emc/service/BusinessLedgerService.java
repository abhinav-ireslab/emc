package com.ireslab.emc.service;
import com.ireslab.emc.model.LedgerResponse;
import com.ireslab.emc.model.PaymentLedgerBalance;

/**
 * @author Santosh
 *
 */
public interface BusinessLedgerService {
	
		
		PaymentLedgerBalance requestPaymentBalence(String accountID);
		LedgerResponse requestPaymentJson(String accountID, LedgerResponse ledgerResponse, String next, String limit);
	
}
