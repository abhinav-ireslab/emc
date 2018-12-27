package com.ireslab.emc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import com.ireslab.emc.model.ApprovelRequest;
import com.ireslab.emc.model.ApprovelResponse;
import com.ireslab.emc.model.ClientDataDto;
import com.ireslab.emc.model.ClientInfoResponse;
import com.ireslab.emc.model.ClientPageResponse;
import com.ireslab.emc.model.FilterLedgerRequest;
import com.ireslab.emc.model.FilterLedgerResponse;
import com.ireslab.emc.model.Login;
import com.ireslab.emc.model.LoginResponse;
import com.ireslab.emc.model.PasswordResetRequest;
import com.ireslab.emc.model.PasswordResetResponse;
import com.ireslab.emc.model.SubscriptionPlanRequest;
import com.ireslab.emc.model.SubscriptionPlanResponse;
import com.ireslab.emc.model.TransactionLimitResponse;
import com.ireslab.emc.service.AdminService;
import com.ireslab.emc.service.ClientUserInfoService;
import com.ireslab.emc.service.TransactionService;
import com.ireslab.sendx.electra.model.SendxElectraRequest;
import com.ireslab.sendx.electra.model.SendxElectraResponse;


@RestController
@CrossOrigin(origins = { "*" })
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ClientUserInfoService clientUserInfoService;

	@Autowired
	private ObjectWriter objectWriter;

	@Autowired
	private TransactionService transactionService;

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(AdminController.class);
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Login login) {

		LoginResponse loginResponse = adminService.loginValidate(login);
		return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/adminForgetPassword", method = RequestMethod.GET)
	public ResponseEntity<PasswordResetResponse> adminForgetPaswword(@RequestParam(name = "email") String email) {
		
		LOG.info("Password reset request received for admin by email : " + email);
		PasswordResetResponse resetPassword = adminService.adminResetPassword(email);
		return new ResponseEntity<>(resetPassword, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/adminResetNewPassword", method = RequestMethod.POST)
	public ResponseEntity<PasswordResetResponse> adminResetNewPassword(@RequestBody PasswordResetRequest passwordResetRequest) throws IOException {
		LOG.info("resetting Password");
		
		LOG.info("Reset Password request for admin received " );
        
		PasswordResetResponse passwordResetResponse = adminService.adminResetPasswordWithNewPassword(passwordResetRequest);
		return new ResponseEntity<>(passwordResetResponse, HttpStatus.OK);
    }
	
	/*@RequestMapping(value = "/enable2faForAdmin", method = RequestMethod.POST)
	public ResponseEntity<UserLoginResponse> enable2faForAdmin(@RequestBody UserLoginRequest loginRequest)
			throws JsonProcessingException {

		// TODO: Request received to enalbe disable 2 fa
		LOG.info("admin enableDisable2fa request received " + objectWriter.writeValueAsString(loginRequest));

		// TODO: generate and save security code to database

		UserLoginResponse loginResponse = adminService.enableDisable2faSettingForAdmin(loginRequest);

		return new ResponseEntity<>(loginResponse, HttpStatus.OK);
	}*/
	

	@RequestMapping(value = "/getAllClientList/{adminId}", method = RequestMethod.GET)
	public ResponseEntity<ClientInfoResponse> getAllClientAccountInfo(@PathVariable(value = "adminId") String adminId)
			throws JsonProcessingException {

		LOG.info("Request received for retrieving all Clients list for AdminId : " + adminId);
		ClientInfoResponse clientInfoResponse = adminService.getAllAccountDetails();

		return new ResponseEntity<>(clientInfoResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/getClientPageData/{clientCorrelationId}", method = RequestMethod.GET)
	ClientPageResponse ClientList(Pageable pageable,
			@PathVariable(value = "clientCorrelationId") String clientCorrelationId) throws JsonProcessingException {

		LOG.info("Request received for getting all Clients list paginated");

		ClientPageResponse clientResponse = clientUserInfoService.getAllClientCustom(pageable, clientCorrelationId);

		List<ClientDataDto> clientList = clientResponse.getPageList();

		return clientResponse;
	}

	@RequestMapping(value = "/getFilterLedger/{clientCorrelationId}/{page}/{size}", method = RequestMethod.POST)
	public ResponseEntity<FilterLedgerResponse> getFilterLedger(
			@PathVariable(value = "clientCorrelationId") String clientCorrelationId,
			@RequestBody FilterLedgerRequest filterLedgerRequest, Pageable pageable,
			@PathVariable(value = "page") String page, @PathVariable(value = "size") String size)
			throws JsonProcessingException {

		filterLedgerRequest.setClientCorrelationId(clientCorrelationId);

		LOG.info("Filter Ledger Request received for Client - "
				+ objectWriter.writeValueAsString(filterLedgerRequest));

	FilterLedgerResponse allFilterLedgerInfo = clientUserInfoService.getLedger(filterLedgerRequest, page, size);

		LOG.info("Response sent for Filter Ledger - " + objectWriter.writeValueAsString(allFilterLedgerInfo));

		/* return new ResponseEntity<>(allFilterLedgerInfo, HttpStatus.OK); */

	return new ResponseEntity<>(allFilterLedgerInfo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getOnlineOfflineLedger/{clientCorrelationId}/{page}/{size}", method = RequestMethod.POST)
	public ResponseEntity<FilterLedgerResponse> getOnlineOfflineLedger(
			@PathVariable(value = "clientCorrelationId") String clientCorrelationId,
			@RequestBody FilterLedgerRequest filterLedgerRequest, Pageable pageable,
			@PathVariable(value = "page") String page, @PathVariable(value = "size") String size)
			throws JsonProcessingException {

		filterLedgerRequest.setClientCorrelationId(clientCorrelationId);

		LOG.info("Filter Ledger Request received for Client - "
				+ objectWriter.writeValueAsString(filterLedgerRequest));

	FilterLedgerResponse allFilterLedgerInfo = clientUserInfoService.getOnlineOfflineLedger(filterLedgerRequest, page, size);

		LOG.info("Response sent for Filter Ledger - " + objectWriter.writeValueAsString(allFilterLedgerInfo));

		/* return new ResponseEntity<>(allFilterLedgerInfo, HttpStatus.OK); */

	return new ResponseEntity<>(allFilterLedgerInfo, HttpStatus.OK);
	}

	@RequestMapping(value = "getTransactionLimitData", method = RequestMethod.GET)
	public ResponseEntity<TransactionLimitResponse> transactionLimitData() throws JsonProcessingException {
		LOG.info("Transaction Limit Data Request received : ");
		TransactionLimitResponse transactionLimitResponse = transactionService.getTransactionLimitData();

		return new ResponseEntity<>(transactionLimitResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value="ekycEkybApprovelList",method=RequestMethod.GET)
	public ResponseEntity<ApprovelResponse> ekycEkybApprovelList() throws JsonProcessingException{
		ApprovelResponse approvelResponse =transactionService.ekycEkybApprovelList();
		LOG.info("Response sent for ekyc/ekyb approval list - " + objectWriter.writeValueAsString(approvelResponse));
		return new ResponseEntity<>(approvelResponse,HttpStatus.OK);
	}
	
	@RequestMapping(value="ekycEkybApprovedList",method=RequestMethod.GET)
	public ResponseEntity<ApprovelResponse> ekycEkybApprovedList() throws JsonProcessingException{
		ApprovelResponse approvelResponse =transactionService.ekycEkybApprovedList();
		return new ResponseEntity<>(approvelResponse,HttpStatus.OK);
	}
	
	@RequestMapping(value="approveEkycEkyb",method=RequestMethod.POST)
	public ResponseEntity<ApprovelResponse> approveEkycEkyb(@RequestBody ApprovelRequest approvelRequest) throws JsonProcessingException{
		LOG.info("Approvel json: "+objectWriter.writeValueAsString(approvelRequest));
		ApprovelResponse approvelResponse =transactionService.approveEkycEkyb(approvelRequest);
		return new ResponseEntity<>(approvelResponse,HttpStatus.OK);
	}


@RequestMapping(value = "/downloadExcelAndCsv/{clientCorrelationId}", method = RequestMethod.POST,produces = "text/csv")
public void downloadExcelAndCsv(@PathVariable(value = "clientCorrelationId") String clientCorrelationId,
		@RequestBody FilterLedgerRequest filterLedgerRequest, HttpServletResponse res) throws JsonProcessingException {

	filterLedgerRequest.setClientCorrelationId(clientCorrelationId);
	
	LOG.info("Request for download Excel and csv file - "
			+ objectWriter.writeValueAsString(filterLedgerRequest));

     clientUserInfoService.downloadExcelAndCsv(filterLedgerRequest, res);

	LOG.info("Excel and csv downloaded successfully ");

	
 }

@RequestMapping(value = "addUpdateSubscriptionPlan", method = RequestMethod.POST)
public ResponseEntity<SubscriptionPlanResponse> addUpdateSubscriptionPlan(@RequestBody SubscriptionPlanRequest subscriptionPlanRequest) throws JsonProcessingException {
	LOG.info("Request received for add or update subcription plan : "+objectWriter.writeValueAsString(subscriptionPlanRequest));
	SubscriptionPlanResponse subscriptionPlanResponse = clientUserInfoService.addUpdateSubscriptionPlan(subscriptionPlanRequest);
	LOG.info("Response for add or update subcription plan : "+objectWriter.writeValueAsString(subscriptionPlanResponse));

	return new ResponseEntity<>(subscriptionPlanResponse, HttpStatus.OK);
}

@RequestMapping(value = "deleteSubscriptionPlan", method = RequestMethod.POST)
public ResponseEntity<SubscriptionPlanResponse> deleteSubscriptionPlan(@RequestBody SubscriptionPlanRequest subscriptionPlanRequest) throws JsonProcessingException {
	LOG.info("Request received for delete subcription plan : "+objectWriter.writeValueAsString(subscriptionPlanRequest));
	SubscriptionPlanResponse subscriptionPlanResponse = clientUserInfoService.deleteSubscriptionPlan(subscriptionPlanRequest);
	LOG.info("Response for delete subcription plan : "+objectWriter.writeValueAsString(subscriptionPlanResponse));

		return new ResponseEntity<>(subscriptionPlanResponse, HttpStatus.OK);
	}

@RequestMapping(value = "getSubscriptionPlanListByCountry/{countryId}", method = RequestMethod.GET)
public ResponseEntity<SubscriptionPlanResponse> getSubscriptionPlanListByCountry(@PathVariable(value = "countryId") String countryId) throws JsonProcessingException {
	LOG.info("Request received for get subcription plan list ");
	SubscriptionPlanResponse subscriptionPlanResponse = clientUserInfoService.getSubscriptionPlanListByCountry(countryId);
	LOG.info("Response for get subcription plan list : "+objectWriter.writeValueAsString(subscriptionPlanResponse));

		return new ResponseEntity<>(subscriptionPlanResponse, HttpStatus.OK);
	}

@RequestMapping(value = "getSubscriptionPlanListForAdmin/{page}/{size}", method = RequestMethod.GET)
public ResponseEntity<SubscriptionPlanResponse> getSubscriptionPlanListForAdmin(@PathVariable(value = "page") String page, @PathVariable(value = "size") String size) throws JsonProcessingException {
	LOG.info("Request received for get subcription plan list for admin ");
	SubscriptionPlanResponse subscriptionPlanResponse = clientUserInfoService.getSubscriptionPlanListForAdmin(page, size);
	LOG.info("Response for get subcription plan list for admin : "+objectWriter.writeValueAsString(subscriptionPlanResponse));

		return new ResponseEntity<>(subscriptionPlanResponse, HttpStatus.OK);
	}



@RequestMapping(value="getAllSettlementReports",method=RequestMethod.GET)
public ResponseEntity<SendxElectraResponse> getAllSettlementReports(Pageable pageable,
		@RequestParam(value = "page", required = false) String page,
		@RequestParam(value = "size", required = false) String size) throws JsonProcessingException{
	LOG.info("Request received to get all settlement reports");
	SendxElectraResponse sendxElectraResponse = transactionService.getAllSettlementReports(page,size);
	return new ResponseEntity<>(sendxElectraResponse,HttpStatus.OK);
}

@RequestMapping(value="updateSettlementReport",method=RequestMethod.POST)
public ResponseEntity<SendxElectraResponse> updateSettlementReport(@RequestBody SendxElectraRequest sendxElectraRequest) throws JsonProcessingException{
	//LOG.info("Request received to delete Notification [notificationId:"+sendxElectraRequest.getNotificationId()+""+objectWriter.writeValueAsString(sendxElectraRequest));
	SendxElectraResponse sendxElectraResponse = transactionService.updateSettlementReport(sendxElectraRequest);
	return new ResponseEntity<>(sendxElectraResponse,HttpStatus.OK);
}




}