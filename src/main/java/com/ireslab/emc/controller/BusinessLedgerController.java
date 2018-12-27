package com.ireslab.emc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.emc.model.LedgerResponse;
import com.ireslab.emc.model.PaymentLedger;
import com.ireslab.emc.model.PaymentLedgerBalance;
import com.ireslab.emc.service.BusinessLedgerService;
import com.ireslab.emc.service.UserLedgerService;
import com.ireslab.emc.serviceImpl.LedgerServiceImpl;

/**
 * @author Santosh
 *
 */
@RestController
@CrossOrigin(origins = { "*" })
public class BusinessLedgerController {
	
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BusinessLedgerController.class);

	@Autowired
	private ObjectWriter objectWriter;

	@Autowired
	private LedgerServiceImpl ledgerServiceImpl;

	@Autowired
	private BusinessLedgerService businessLedgerService;
	
	@RequestMapping(value = "/requestBusinessLedger/{accountId}/{next}/{limit}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<LedgerResponse> requestUserLedgerJson(@PathVariable(value = "accountId") String accountId,
			@PathVariable(value = "next") String next, @PathVariable(value = "limit") String limit)
            throws JsonProcessingException {

		log.info("Business ledger request  recevied for accountId : " + objectWriter.writeValueAsString(accountId));

		LedgerResponse ledgerResponseWithAssetsAndPayment = businessLedgerService.requestPaymentJson(accountId,
				new LedgerResponse(), next, limit);


		return new ResponseEntity<>(ledgerResponseWithAssetsAndPayment, HttpStatus.OK);
	}

	

}
