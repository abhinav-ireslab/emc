package com.ireslab.emc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.emc.dto.ExchangeDto;

import com.ireslab.emc.model.AccountDetailsResponse;
import com.ireslab.emc.model.ClientAssetTokenResponse;
import com.ireslab.emc.model.ClientUsersInfoResponse;
import com.ireslab.emc.model.ExchangeResponse;
import com.ireslab.emc.model.ProductGroupResponse;
import com.ireslab.emc.model.ProductRequest;
import com.ireslab.emc.model.ProductResponse;
import com.ireslab.emc.model.SaveProductRequest;
import com.ireslab.emc.model.SaveProductResponse;
import com.ireslab.emc.model.TokenLifecycleManagementRequest;
import com.ireslab.emc.model.TokenLifecycleManagementResponse;
import com.ireslab.emc.model.TransactionPurposeRequest;
import com.ireslab.emc.model.TransactionPurposeResponse;
import com.ireslab.emc.service.ClientUserInfoService;

@RestController
@CrossOrigin(origins = { "*" })
public class ClientUserInfoController {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(ConsoleUiController.class);

	@Autowired
	private ClientUserInfoService clientUserService;
	
	@Autowired
	private ObjectWriter objectWriter;

	@RequestMapping(value = "/users/{clientCorrelationId}", method = RequestMethod.GET)
	public ResponseEntity<ClientUsersInfoResponse> usersAccountInfo(
			@PathVariable(value = "clientCorrelationId") String clientCorrelationId) throws JsonProcessingException {

		LOG.debug("Request received for getting users list based on clientCorrelationId - " + clientCorrelationId);
		ClientUsersInfoResponse clientUsersInfoResponse = clientUserService.getClientUsersList(clientCorrelationId);
		LOG.debug("Response for user list - " + objectWriter.writeValueAsString(clientUsersInfoResponse));

		return new ResponseEntity<>(clientUsersInfoResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "users/{userCorrelationId}/tokens", method = RequestMethod.POST)
	public ResponseEntity<TokenLifecycleManagementResponse> loadTokens(
			@PathVariable("userCorrelationId") String userCorrelationId,
			@RequestBody TokenLifecycleManagementRequest tokenManagementRequest) throws JsonProcessingException {

		return null;
	}

	@RequestMapping(value = "/getAllClientAssetTokenPages/{clientCorrelationId}", method = RequestMethod.GET)
	public ResponseEntity<ClientAssetTokenResponse> getAllClientAssetToken(
			@PathVariable(value = "clientCorrelationId") String clientCorrelationId,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "size", required = false) String size) throws JsonProcessingException {
		// log.info("Get all client asset token request received for clientCorrelationId
		// : " + objectWriter.writeValueAsString(clientCorrelationId));
		ClientAssetTokenResponse assetTokenResponse = clientUserService.allClientAssetToken(clientCorrelationId, page,
				size);
		return new ResponseEntity<>(assetTokenResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAccountDetailsByPublicKey/{PublicKey}", method = RequestMethod.GET)
	public ResponseEntity<AccountDetailsResponse> getAccountDetailsByPublicKey(
			@PathVariable(value = "") String PublicKey) throws JsonProcessingException {
		// log.info("Account details request received for Account PublicKey : " +
		// objectWriter.writeValueAsString(PublicKey));
		AccountDetailsResponse accountDetailsResponse = clientUserService.getAccountDetailsByPublicKey(PublicKey);
		return new ResponseEntity<>(accountDetailsResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/getExchangeDetails", method = RequestMethod.GET)
	public ResponseEntity<ExchangeResponse> getExchangeDetails() throws JsonProcessingException {
		LOG.info("Exchange details  request received  : ");
		ExchangeResponse exchangeDetailsResponse = clientUserService.getAllExchangeDetails();
		return new ResponseEntity<>(exchangeDetailsResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/configureExchange/{clientCorrelationId}", method = RequestMethod.POST)
	public ResponseEntity<ExchangeResponse> updateExchangeDetails(
			@PathVariable(value = "clientCorrelationId") String clientCorrelationId,
			@RequestBody ExchangeDto clientExchangeRequest) throws JsonProcessingException {
		clientExchangeRequest.setClientCorrelationId(clientCorrelationId);
		LOG.info("Configure Exchange details  request received  : ");
		ExchangeResponse exchangeDetailsResponse = clientUserService.updateExchangeDetails(clientExchangeRequest);
		return new ResponseEntity<>(exchangeDetailsResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/getProductGroups/{clientCorrelationId}", method = RequestMethod.GET)
	public ResponseEntity<ProductGroupResponse> getProductGroupList(
			@PathVariable(value = "clientCorrelationId") String clientCorrelationId) throws JsonProcessingException {
		LOG.info("Get ProductGroup Request received : ");
		ProductGroupResponse productGroupResponse = clientUserService.getProductGroupList(clientCorrelationId);
		return new ResponseEntity<>(productGroupResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public ResponseEntity<SaveProductResponse> saveProduct(@RequestBody SaveProductRequest saveProductRequest)
			throws JsonProcessingException {
		SaveProductResponse saveProductResponse = clientUserService.saveProduct(saveProductRequest);
		return new ResponseEntity<>(saveProductResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "editProduct", method = RequestMethod.POST)
	public ResponseEntity<SaveProductResponse> editProduct(@RequestBody SaveProductRequest saveProductRequest)
			throws JsonProcessingException {
		
		LOG.debug("Request received for edit product - " + objectWriter.writeValueAsString(saveProductRequest));
		SaveProductResponse saveProductResponse = clientUserService.editProduct(saveProductRequest);
		return new ResponseEntity<>(saveProductResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "deleteProduct/{productCode}", method = RequestMethod.GET)
	public ResponseEntity<SaveProductResponse> deleteProduct(@PathVariable("productCode") String productCode)
			throws JsonProcessingException {
		SaveProductResponse saveProductResponse = clientUserService.deleteProduct(productCode);
		return new ResponseEntity<>(saveProductResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "products/{clientCorrelationId}", method = RequestMethod.POST)
	public ResponseEntity<ProductResponse> getProducetList(@RequestBody ProductRequest productRequest,
			@PathVariable(value = "clientCorrelationId") String clientCorrelationId) throws JsonProcessingException {
		ProductResponse productResponse = clientUserService.getProducts(productRequest, clientCorrelationId);
		return new ResponseEntity<>(productResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllTransactionPurposeList/{clientCorrelationId}", method = RequestMethod.GET)
	public ResponseEntity<TransactionPurposeResponse> getAllTransactionPurposeList(
			@PathVariable(value = "clientCorrelationId") String clientCorrelationId) throws JsonProcessingException {
		LOG.info("Request received for purpose of transaction list with correlation id : "+clientCorrelationId);
		TransactionPurposeResponse transactionPurposeResponse = clientUserService.getAllTransactionPurposeList(clientCorrelationId);
		return new ResponseEntity<>(transactionPurposeResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addAndUpdateTransactionPurpose/{clientCorrelationId}", method = RequestMethod.POST)
	public ResponseEntity<TransactionPurposeResponse> addAndUpdateTransactionPurpose(@RequestBody TransactionPurposeRequest transactionPurposeRequest,
			@PathVariable(value = "clientCorrelationId") String clientCorrelationId) throws JsonProcessingException {
		LOG.info("Request received for save or update purpose of transaction with correlation id : "+clientCorrelationId);
		TransactionPurposeResponse transactionPurposeResponse = clientUserService.addAndUpdateTransactionPurpose((TransactionPurposeRequest) transactionPurposeRequest.setClientId(clientCorrelationId));
		return new ResponseEntity<>(transactionPurposeResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteTransactionPurpose/{clientCorrelationId}", method = RequestMethod.POST)
	public ResponseEntity<TransactionPurposeResponse> deleteTransactionPurpose(@RequestBody TransactionPurposeRequest transactionPurposeRequest,
			@PathVariable(value = "clientCorrelationId") String clientCorrelationId) throws JsonProcessingException {
		LOG.info("Request received for delete purpose of transaction with correlation id : "+clientCorrelationId);
		TransactionPurposeResponse transactionPurposeResponse = clientUserService.deleteTransactionPurpose((TransactionPurposeRequest) transactionPurposeRequest.setClientId(clientCorrelationId));
		return new ResponseEntity<>(transactionPurposeResponse, HttpStatus.OK);
	}
}
