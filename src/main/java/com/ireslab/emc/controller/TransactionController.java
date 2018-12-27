package com.ireslab.emc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.emc.model.LoadTokensRequest;
import com.ireslab.emc.model.LoadTokensResponse;
import com.ireslab.emc.service.TransactionService;




@RestController
@CrossOrigin(origins = { "*" })
public class TransactionController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TransactionController.class);

	
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private ObjectWriter objectWriter;
	
	/**
	 * @param cashOutRequest
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/loadTokens/{clientCorrelationId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public LoadTokensResponse transactionHistory(@RequestBody LoadTokensRequest loadTokensRequest,@PathVariable(value = "clientCorrelationId") String clientCorrelationId)
			throws JsonProcessingException {
		loadTokensRequest.setClientCorrelationId(clientCorrelationId);
		LoadTokensResponse loadTokensResponse = null;
		LOG.debug("Load Tokens request received - " + objectWriter.writeValueAsString(loadTokensRequest));

		loadTokensResponse = transactionService.handleLoadTokens(loadTokensRequest);
		LOG.debug("Load Tokens response sent - " + objectWriter.writeValueAsString(loadTokensResponse));

		//System.out.println("--------------======-------- : "+loadTokensResponse);
		
		return loadTokensResponse;
	}
	

}
