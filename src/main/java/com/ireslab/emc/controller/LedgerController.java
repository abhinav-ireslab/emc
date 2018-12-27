/**
 * 
 */
package com.ireslab.emc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.emc.model.LedgerRequest;
import com.ireslab.emc.model.LedgerResponse;
import com.ireslab.emc.model.PaymentLedger;
import com.ireslab.emc.model.PaymentLedgerBalance;
import com.ireslab.emc.service.UserLedgerService;
import com.ireslab.emc.serviceImpl.LedgerServiceImpl;

/**
 * @author Akhilesh
 *
 */
@RestController
@CrossOrigin(origins = { "*" })
public class LedgerController {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ConsoleUiController.class);

	@Autowired
	private ObjectWriter objectWriter;

	@Autowired
	private LedgerServiceImpl ledgerServiceImpl;

	@Autowired
	private UserLedgerService userLedgerService;

	// @RequestMapping(value = "/requestledger/{accountid}", method =
	// RequestMethod.GET)
	// public Page<LedgerResponse> requestLedger(@PathVariable(value = "accountid")
	// String userCorrelationId)
	// throws JsonProcessingException {
	// Page<LedgerResponse> output = null;
	// try {
	// output = (Page<LedgerResponse>)
	// ledgerServiceImpl.requestLedger(userCorrelationId);
	// } catch (URISyntaxException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// // System.out.println("MOR : "+output);
	// return output;
	// }

	/*@RequestMapping(value = "/requestledger/{accountid}", method = RequestMethod.GET, produces = "application/json")
	public PaymentLedger requestLedgerJson(@PathVariable(value = "accountid") String accountID)
			throws JsonProcessingException {
		PaymentLedger paymentLedger = null;
		paymentLedger = ledgerServiceImpl.requestLedgerJson(accountID);
		PaymentLedgerBalance ledgerBalance = ledgerServiceImpl.requestPaymentBalence(accountID);
		paymentLedger.setLedgerBalance(ledgerBalance);
		return paymentLedger;
	}*/

	@RequestMapping(method = RequestMethod.POST, value = "requestledger", produces = "application/json")
	public PaymentLedger requestLedgerJson(HttpServletRequest request, @RequestBody LedgerRequest ledgerRequest)
			throws JsonProcessingException {
		PaymentLedger paymentLedger = null;
		
		
		/*paymentLedger = ledgerServiceImpl.requestLedgerJson(ledgerRequest);
		PaymentLedgerBalance ledgerBalance = ledgerServiceImpl.requestPaymentBalence(ledgerRequest.getPublicKey());
		paymentLedger.setLedgerBalance(ledgerBalance);*/
		
		
		String limit = ledgerRequest.getLimit();
		if (limit != null && limit.length() > 0) {
			paymentLedger = ledgerServiceImpl.requestLedgerJson(ledgerRequest);
			PaymentLedgerBalance ledgerBalance = ledgerServiceImpl.requestPaymentBalence(ledgerRequest.getPublicKey());
			paymentLedger.setLedgerBalance(ledgerBalance);
		} else {
			paymentLedger = ledgerServiceImpl.requestLedgerJson(ledgerRequest);
		}
		
		//log.info("TESTING BUSINESS LEDGER JSON : "+objectWriter.writeValueAsString(paymentLedger));
		return paymentLedger;
	}

	@RequestMapping(value = "/requestUserledger/{accountId}/{next}/{limit}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<LedgerResponse> requestUserLedgerJson(@PathVariable(value = "accountId") String accountId,
			@PathVariable(value = "next") String next, @PathVariable(value = "limit") String limit)
            throws JsonProcessingException {

		//log.info("User ledger request  recevied for accountId : " + objectWriter.writeValueAsString(accountId));

		LedgerResponse ledgerResponseAssets = userLedgerService.requestAssetsJson(accountId);
		LedgerResponse ledgerResponseWithAssetsAndPayment = userLedgerService.requestPaymentJson(accountId,
				ledgerResponseAssets, next, limit);
		// log.info("User ledger response send for accountId : " +
		// objectWriter.writeValueAsString(ledgerResponseWithAssetsAndPayment));

		/*
		 * PaymentLedger paymentLedger = null; paymentLedger =
		 * ledgerServiceImpl.requestLedgerJson(accountID); PaymentLedgerBalance
		 * ledgerBalance = ledgerServiceImpl.requestPaymentBalence(accountID);
		 * paymentLedger.setLedgerBalance(ledgerBalance);
		 */

		return new ResponseEntity<>(ledgerResponseWithAssetsAndPayment, HttpStatus.OK);
	}

}
