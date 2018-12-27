/**
 * 
 */
package com.ireslab.emc.service;

import com.ireslab.emc.model.LedgerRequest;
import com.ireslab.emc.model.PaymentLedger;
import com.ireslab.emc.model.PaymentLedgerBalance;

/**
 * @author Akhilesh
 *
 */
public interface LedgerService {
	// Page<LedgerResponse> requestLedger(String url) throws URISyntaxException,
	// IOException;
	PaymentLedger requestLedgerJson(LedgerRequest ledgerRequest);
	//PaymentLedger requestLedgerJson(String accountId);
	PaymentLedgerBalance requestPaymentBalence(String accountID);
}
