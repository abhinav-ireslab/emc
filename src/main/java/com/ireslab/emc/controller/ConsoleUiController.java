package com.ireslab.emc.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ireslab.emc.model.ClientAssetTokenRequest;
import com.ireslab.emc.model.ClientAssetTokenResponse;
import com.ireslab.emc.model.ClientRegistrationRequest;
import com.ireslab.emc.model.ClientRegistrationResponse;
import com.ireslab.emc.model.Login;
import com.ireslab.emc.model.LoginResponse;
import com.ireslab.emc.model.UserAgentRegistrationRequest;
import com.ireslab.emc.model.UserAgentRegistrationResponse;
import com.ireslab.emc.model.UserAgentResponse;
import com.ireslab.emc.model.UserLoginRequest;
import com.ireslab.emc.model.UserLoginResponse;
import com.ireslab.emc.model.UserRegistrationRequest;
import com.ireslab.emc.model.UserRegistrationResponse;
import com.ireslab.emc.service.ClientUserInfoService;
import com.ireslab.emc.serviceImpl.UiServiceImpl;

@RestController
@CrossOrigin(origins = { "*" })
/*
 * @RequestMapping(value = "/v1/*", produces = MediaType.APPLICATION_JSON_VALUE)
 */
public class ConsoleUiController {

	private static final Logger log = LoggerFactory.getLogger(ConsoleUiController.class);

	@Autowired
	private ObjectWriter objectWriter;

	@Autowired
	private ClientUserInfoService clientUserInfoService;

	@Autowired
	private UiServiceImpl uiService;

	

	@RequestMapping(value = "/validateCredential", method = RequestMethod.POST)
	public ResponseEntity<UserLoginResponse> validateCredential(HttpServletRequest request,
			@RequestBody UserLoginRequest loginRequest) throws JsonProcessingException {

		// TODO: Request and response logging
		// log.info("User login request received " +
		// objectWriter.writeValueAsString(loginRequest));
		log.info("User login request received ");

		UserLoginResponse loginResponse = uiService.validateAllUsercredentials(loginRequest, request);
		return new ResponseEntity<>(loginResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/validateCredentialWithOtp", method = RequestMethod.POST)
	public ResponseEntity<UserLoginResponse> validateCredentialWithOtp(HttpServletRequest request,
			@RequestBody UserLoginRequest loginRequest) throws JsonProcessingException {

		// TODO: Request and response logging
		// log.info("User login request received " +
		// objectWriter.writeValueAsString(loginRequest));
		log.info("User login request received ");

		UserLoginResponse loginResponse = uiService.validateOTP(loginRequest);
		return new ResponseEntity<>(loginResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/enable2fa", method = RequestMethod.POST)
	public ResponseEntity<UserLoginResponse> enableDisable2fa(@RequestBody UserLoginRequest loginRequest)
			throws JsonProcessingException {

		// TODO: Request received to enalbe disable 2 fa
		log.info("User enableDisable2fa request received " + objectWriter.writeValueAsString(loginRequest));

		// TODO: generate and save security code to database

		UserLoginResponse loginResponse = uiService.enableDisable2faSetting(loginRequest);

		return new ResponseEntity<>(loginResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/createClient", method = RequestMethod.POST)
	public ResponseEntity<ClientRegistrationResponse> createClient(HttpServletRequest request,
			@RequestBody ClientRegistrationRequest clientRegistrationRequest) throws JsonProcessingException {

		log.info("Client registration request received " + objectWriter.writeValueAsString(clientRegistrationRequest));

		ClientRegistrationResponse registrationResponse = clientUserInfoService.createClient(clientRegistrationRequest);
		return new ResponseEntity<>(registrationResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateClient/{clientCorrelationId}", method = RequestMethod.PUT)
	public ResponseEntity<ClientRegistrationResponse> updateClient(HttpServletRequest request,
			@PathVariable(value = "clientCorrelationId") String clientCorrelationId,
			@RequestBody ClientRegistrationRequest clientUpdationRequest) throws JsonProcessingException {

		// TODO: Request and response logging
		log.info("Client registration request received " + objectWriter.writeValueAsString(clientUpdationRequest));

		ClientRegistrationResponse registrationResponse = clientUserInfoService
				.updateClient((ClientRegistrationRequest) clientUpdationRequest.setClientId(clientCorrelationId));
		return new ResponseEntity<>(registrationResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "{clientCorrelationId}/updateUser/{userCorrelationId}", method = RequestMethod.PUT)
	public ResponseEntity<UserRegistrationResponse> updateUser(HttpServletRequest request,
			@PathVariable(value = "userCorrelationId") String userCorrelationId,
			@PathVariable(value = "clientCorrelationId") String clientCorrelationId,
			@RequestBody UserRegistrationRequest userRegistrationRequest) throws JsonProcessingException {

		// TODO: Request and response logging
		log.info("Request received for user status updation "
				+ objectWriter.writeValueAsString(userRegistrationRequest));

		UserRegistrationResponse userRegistrationResponse = clientUserInfoService
				.updateUser((UserRegistrationRequest) userRegistrationRequest.setClientId(clientCorrelationId),userCorrelationId);

		return new ResponseEntity<>(userRegistrationResponse, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/updateAgent", method = RequestMethod.PUT)
	public ResponseEntity<UserAgentResponse> updateUser(HttpServletRequest request,
			@RequestBody UserAgentRegistrationRequest userAgentRegistrationRequest) throws JsonProcessingException {

		// TODO: Request and response logging
		log.info("Request received for agent status updation "
				+ objectWriter.writeValueAsString(userAgentRegistrationRequest));

		UserAgentResponse userAgentResponse = clientUserInfoService
				.updateAgent(userAgentRegistrationRequest);

		return new ResponseEntity<>(userAgentResponse, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getAgentList/{userCorrelationId}", method = RequestMethod.GET)
	public UserAgentRegistrationResponse getAgentList(HttpServletRequest request,
			@PathVariable(value = "userCorrelationId") String userCorrelationId) throws JsonProcessingException {

		// TODO: Request and response logging
		log.info("Request received for agent List "
				+ userCorrelationId);

		UserAgentRegistrationResponse userRegistrationResponse = clientUserInfoService
				.getAgentList(userCorrelationId);

		
		
		return userRegistrationResponse;
	}
	
	
	
	// Configure client Asset Token

	@RequestMapping(value = "/configureClientAssetToken", method = RequestMethod.POST)
	public ResponseEntity<ClientAssetTokenResponse> configureClientAssetToken(
			@RequestBody ClientAssetTokenRequest clientAssetTokenRequest) throws JsonProcessingException {

		log.info("Request received for Configuring client Asset Token - "
				+ objectWriter.writeValueAsString(clientAssetTokenRequest));

		ClientAssetTokenResponse clientAssetTokenResponse = clientUserInfoService
				.clientAssetTokenConfiguration(clientAssetTokenRequest);

		log.info("Response sent for Configuring client Asset Token - "
				+ objectWriter.writeValueAsString(clientAssetTokenResponse));

		return new ResponseEntity<>(clientAssetTokenResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/activateClientCredential/{clientCorrelationId}", method = RequestMethod.PUT)
	public ResponseEntity<ClientRegistrationResponse> activateClientCredential(HttpServletRequest request,
			@PathVariable(value = "clientCorrelationId") String clientCorrelationId,
			@RequestBody ClientRegistrationRequest clientUpdationRequest) throws JsonProcessingException {
		log.info("Request received for activating ClientCredential - "
				+ objectWriter.writeValueAsString(clientUpdationRequest));

		ClientRegistrationResponse registrationResponse = clientUserInfoService.activateClientCredential(
				(ClientRegistrationRequest) clientUpdationRequest.setClientId(clientCorrelationId));
		return new ResponseEntity<>(registrationResponse, HttpStatus.OK);
	}
	
	

}
