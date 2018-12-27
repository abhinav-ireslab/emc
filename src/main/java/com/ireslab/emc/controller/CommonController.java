package com.ireslab.emc.controller;

import java.io.IOException;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.emc.model.ClientValidateDto;
import com.ireslab.emc.model.CountryListResponse;
import com.ireslab.emc.model.PasswordResetRequest;
import com.ireslab.emc.model.PasswordResetResponse;
import com.ireslab.emc.model.UserReferralRequest;
import com.ireslab.emc.model.UserReferralResponse;
import com.ireslab.emc.service.CommonService;
import com.ireslab.sendx.electra.model.ProductConfigurationResponse;



@RestController
@CrossOrigin(origins = { "*" })
public class CommonController {
	
	private static final Logger log = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private ObjectWriter objectWriter;
	
	
	// a request from ui with email to reset password and send email with reset password link which will redirect user to ui with token.
		@RequestMapping(value = "/forgot", method = RequestMethod.GET)
		public ResponseEntity<PasswordResetResponse> processForgotPasswordForm(@RequestParam(name = "email") String email, HttpServletRequest request) {
			
			log.info("Password reset request received for email : " + email);
			PasswordResetResponse resetPassword = commonService.resetPassword(email,request);
			return new ResponseEntity<>(resetPassword, HttpStatus.OK);
			
		}
		
		
		// a request from ui with token will receive whenever user click resetpassword link.
		@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
		public ResponseEntity<PasswordResetResponse> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest,HttpServletRequest request) throws IOException {
			log.info("resetting Password");
			
			log.info("Reset Password request received " + objectWriter.writeValueAsString(passwordResetRequest));
	        
			PasswordResetResponse passwordResetResponse = commonService.resetPasswordWithNewPassword(passwordResetRequest, request);
			return new ResponseEntity<>(passwordResetResponse, HttpStatus.OK);
	    }
		
		
		@RequestMapping(value = "/getCountryList/{clientCorrelationId}", method = RequestMethod.GET)
		public CountryListResponse getCountryList(HttpServletRequest request,
				@PathVariable(value = "clientCorrelationId") String clientCorrelationId) throws JsonProcessingException {
			
			log.info("get country list request received ");

			CountryListResponse countryListResponse = commonService.getCountryList(clientCorrelationId);
			return countryListResponse;
		}
		
		@RequestMapping(value = "/inviteAgent", method = RequestMethod.POST)
		public ResponseEntity<UserReferralResponse> referUsers(HttpServletRequest request,
			@RequestBody UserReferralRequest referralRequest) throws JsonProcessingException{
			log.info("Agent invation request received " + objectWriter.writeValueAsString(referralRequest));
            UserReferralResponse userReferralResponse = commonService.inviteAgent(referralRequest);
            return new ResponseEntity<>(userReferralResponse, HttpStatus.OK);
		}
		
		@RequestMapping(value = "/ExcelDataLoad", method = RequestMethod.GET)
		public ResponseEntity<ProductConfigurationResponse> ExcelDataLoad() throws JsonProcessingException{
			log.info("Request received for read and load excel sheet data ");
			ProductConfigurationResponse productConfigurationResponse = commonService.ExcelDataLoad();
            return new ResponseEntity<>(productConfigurationResponse, HttpStatus.OK);
		}
		
		@RequestMapping(value = "/clientValidate", method = RequestMethod.GET)
		public ResponseEntity<ClientValidateDto> clientValidate(@RequestParam(name = "correlationId") String correlationId,HttpServletRequest request) {
			
			log.info("Validating logged-in client : "+correlationId);
			ClientValidateDto clientValidateDto = commonService.clientValidate(correlationId, request);
			return new ResponseEntity<>(clientValidateDto, HttpStatus.OK);
			
		}	

}
