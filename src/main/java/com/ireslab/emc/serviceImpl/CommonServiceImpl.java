package com.ireslab.emc.serviceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.emc.ElectraApiConfig;
import com.ireslab.emc.constant.AppCodes;
import com.ireslab.emc.constant.PropConstants;
import com.ireslab.emc.dto.ClientAgentInvitationDto;
import com.ireslab.emc.model.ClentAgentInvitationRequest;
import com.ireslab.emc.model.ClientProfile;
import com.ireslab.emc.model.ClientRegistrationRequest;
import com.ireslab.emc.model.ClientRegistrationResponse;
import com.ireslab.emc.model.ClientValidateDto;
import com.ireslab.emc.model.CountryListResponse;
import com.ireslab.emc.model.Error;

import com.ireslab.emc.model.MailData;
import com.ireslab.emc.model.PasswordResetRequest;
import com.ireslab.emc.model.PasswordResetResponse;
import com.ireslab.emc.model.UserReferralRequest;
import com.ireslab.emc.model.UserReferralResponse;
import com.ireslab.emc.properties.EmcProperties;
import com.ireslab.emc.service.ClientUserInfoService;
import com.ireslab.emc.service.CommonService;
import com.ireslab.emc.service.ProductConfiguration;
import com.ireslab.emc.util.InvocationType;
import com.ireslab.emc.util.SpringSecurityUtil;
import com.ireslab.emc.util.Status;

import com.ireslab.sendx.electra.model.GstHsnCodeModel;
import com.ireslab.sendx.electra.model.GstHsnSacLoadRequest;
import com.ireslab.sendx.electra.model.ProductConfigurationResponse;

@Service
public class CommonServiceImpl extends EmailServiceImpl implements CommonService {
	private static final Logger log = LoggerFactory.getLogger(CommonServiceImpl.class);

	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	public EmcProperties emcProperties;

	@Autowired
	public ClientUserInfoService clientInfoService;
	
	@Autowired
	public ProductConfiguration productConfiguration;
	
	private static final String BLANK = "";

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String ACCEPT_HEADER = "Accept";
	private static final String CONTENT_TYPE_HEADER = "Content-Type";
	private static final Integer ACCESS_TOKEN_RETRY_LIMIT = 3;

	private static String accessToken = null;
	private static boolean isAccessTokenExpired = false;
	private static int accessTokenRetryCount = 0;
	private static HttpHeaders httpHeaders = null;

	@Autowired
	private ElectraApiConfig electraApiConfig;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectWriter objectWriter;

	@Override
	public PasswordResetResponse resetPassword(String email, HttpServletRequest req) {
		log.info("Resetting password of user with email : " + email);
		PasswordResetResponse passwordResetResponse = new PasswordResetResponse();
		List<Error> errors = new ArrayList<>();

		// Lookup email in database

		// AccountDetail account = clientRepo.findByEmail(email);
		ClientProfile clientProfile = clientInfoService.getClientByEmailId(email);

		if (clientProfile.getClientCorrelationId() == null) {

			log.info("invalid client email entered.");

			errors.add(new Error("wrongCredentials", PropConstants.WRONG_EMAIL, AppCodes.WRONG_EMAIL));
		}else if (!clientProfile.getSubscriptionStatus()) {
			log.info("client is not subscribed..");

			errors.add(new Error("notSubscribed", PropConstants.SUBSCRIPTION_MESSAGE, AppCodes.SCRIPTION_STATUS));
		} 
		
		else if (clientProfile.getClientStatus().equalsIgnoreCase(Status.SUSPENDED.name())) {

			log.info(" client account suspended.");

			errors.add(new Error("accountSuspended", PropConstants.ACCOUNT_SUSPENDED, AppCodes.ACCOUNT_SUSPENDED));
		} else {

			// Generate random 36-character string token for reset password
			log.info("Generate random 36-character string token for reset password");
			String vToken = UUID.randomUUID().toString();
			clientProfile.setResetToken(vToken);

			// Save token to database
			log.info("Saving token to database");
			// clientRepo.save(account);

			ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest();
			List<ClientProfile> profileList = new ArrayList<>();
			profileList.add(clientProfile);
			clientRegistrationRequest.setClientProfile(profileList);

			ClientRegistrationResponse registrationResponse = clientInfoService
					.updateClientDetail((ClientRegistrationRequest) clientRegistrationRequest
							.setClientId(clientProfile.getClientCorrelationId()));

			try {
				log.info("Response after updating client password : - "+objectWriter.writeValueAsString(registrationResponse));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
							
			// Email message

			MailData data = new MailData();

			data.setSendTo(clientProfile.getEmailAddress());
			data.setSubject("Password Reset Request");
			data.setBody(String.format(emcProperties.passwordResetLink, clientProfile.getClientName(),
					emcProperties.baseUrl + String.format(emcProperties.passwordResetPage, vToken)));
			log.info("Sending password Reset Link");
			sendMailTo(data);
			passwordResetResponse.setPasswordResetcode(AppCodes.PASSWORD_RESET_CODE);
			passwordResetResponse.setPasswordResetmessage("successMessage");
			passwordResetResponse
					.setPasswordResetInfo("A password reset link has been sent to " + clientProfile.getEmailAddress());
		}

		passwordResetResponse.setErrors(errors);

		return passwordResetResponse;
	}

	@Override
	public PasswordResetResponse resetPasswordWithNewPassword(PasswordResetRequest passwordResetRequest,
			HttpServletRequest req) {

		PasswordResetResponse passwordResetResponse = new PasswordResetResponse();
		List<Error> errors = new ArrayList<>();
		System.out.println("Finding account by passwordResetRequest.getToken() :" + passwordResetRequest.getToken());
		// AccountDetail resetUser
		// =clientRepo.findUserByResetToken(passwordResetRequest.getToken());
		ClientProfile clientProfile = clientInfoService.getClientByResetToken(passwordResetRequest.getToken());
		if (clientProfile.getClientCorrelationId() == null) {
			log.info("invalid client email entered.");

			errors.add(new Error("invalidRestToken", PropConstants.INVALID_RESET_TOKEN, AppCodes.INVALID_RESET_TOKEN));
		} else {

			// Set new password
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			clientProfile.setPassword(bCryptPasswordEncoder.encode(passwordResetRequest.getPassword()));

			// Set the reset token to null so it cannot be used again
			clientProfile.setResetToken(null);
			clientProfile.setPasswordReset(false);
			// Save user
			log.info("successfully reset your password.");
			// clientRepo.save(resetUser);

			ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest();
			List<ClientProfile> profileList = new ArrayList<>();
			profileList.add(clientProfile);
			clientRegistrationRequest.setClientProfile(profileList);

			ClientRegistrationResponse registrationResponse = clientInfoService
					.updateClientDetail((ClientRegistrationRequest) clientRegistrationRequest
							.setClientId(clientProfile.getClientCorrelationId()));

			// Send Password reset success mail.
			MailData data = new MailData();
			data.setSendTo(clientProfile.getEmailAddress());
			data.setSubject("Password successfully updated !");
			data.setBody(String.format(emcProperties.passwordResetSuccessText, clientProfile.getUserName(),
					emcProperties.baseUrl + emcProperties.loginPage));
			sendMailTo(data);
			log.info("successfully reset  password mail send to user.");
			passwordResetResponse.setPasswordResetcode(AppCodes.PASSWORD_RESET_CODE);
			passwordResetResponse.setPasswordResetmessage("successMessage");
			passwordResetResponse
					.setPasswordResetmessage("You have successfully reset your password.  You may now login.");
		}
		passwordResetResponse.setErrors(errors);

		return passwordResetResponse;
	}

	@Override

	public CountryListResponse getCountryList(String clientCorrelationId) {
		log.info("get country list request received in service ");

		CountryListResponse countryListResponse = clientInfoService.getCountryLists(clientCorrelationId);
		return countryListResponse;
	}

	public UserReferralResponse inviteAgent(UserReferralRequest referralRequest) {
		UserReferralResponse userReferralResponse = new UserReferralResponse();
		List<com.ireslab.emc.model.Error> errors = new ArrayList<>();

		// TODO write a logic to find inivitation limit
		int invationLimit = 10;

		// Getting Email list
		List<String> mailList = referralRequest.getEmailList();

		/*
		 * if(!(mailList.size()>0) || mailList.isEmpty()) {
		 * 
		 * errors.add(new com.ireslab.emc.model.Error(AppCodes.INVITATION_ERROR,
		 * "EmptyEmailList", null, null)); }
		 */

		/*
		 * ClentAgentInvitationRequest clentAgentInvitationRequest = new
		 * ClentAgentInvitationRequest();
		 * clentAgentInvitationRequest.setClientId(referralRequest.
		 * getClientCorrelationId());
		 * 
		 * ClientAgentInvitationDto agentInvitationDto = new ClientAgentInvitationDto();
		 * agentInvitationDto.setClientId(1);
		 * agentInvitationDto.setEmailAddress("abc@yopmail.com");
		 * agentInvitationDto.setRegister(false);
		 * 
		 * List<ClientAgentInvitationDto> list =new ArrayList<>();
		 * list.add(agentInvitationDto);
		 * clentAgentInvitationRequest.setClientAgentInvitationList(list);
		 * clentAgentInvitationRequest.setInvocationType(InvocationType.save.toString())
		 * ;
		 * 
		 * clientInfoService.saveClientAgentInvitationDetails(
		 * clentAgentInvitationRequest);
		 */

		// Get client detail by client correlation Id
		ClientProfile client = clientInfoService.getClientByCorrelationId(referralRequest.getClientCorrelationId());

		if (mailList.size() > 0 && !mailList.isEmpty()) {
			mailList.forEach((mail) -> {
				MailData data = new MailData();
				data.setSendTo(mail);
				data.setSubject(PropConstants.AGENT_INVITATION_SUBJECT);
				data.setBody(String.format(emcProperties.agentInvitationText, emcProperties.getElectraAppDownloadLink(),
						client.getCompanyCode()));
				sendMailTo(data); // Mail send to email in list
				ClentAgentInvitationRequest clentAgentInvitationRequest = new ClentAgentInvitationRequest();
				clentAgentInvitationRequest.setClientId(referralRequest.getClientCorrelationId());
				ClientAgentInvitationDto agentInvitationDto = new ClientAgentInvitationDto();
				agentInvitationDto.setEmailAddress(mail);
				agentInvitationDto.setRegister(false);

				List<ClientAgentInvitationDto> list = new ArrayList<>();
				list.add(agentInvitationDto);

				clentAgentInvitationRequest.setClientAgentInvitationList(list);
				clentAgentInvitationRequest.setInvocationType(InvocationType.save.toString());
				clentAgentInvitationRequest.setSend("MAIL");
				clentAgentInvitationRequest.setSubscriptionDurations(referralRequest.getSubscriptionDurations());
				//clienta
				clientInfoService.saveClientAgentInvitationDetails(clentAgentInvitationRequest);

				// TODO get invitation status
			});
		}

		// Getting Email list
		List<String> mobileList = referralRequest.getMobileList();

		/*
		 * if(!(mobileList.size()>0) || mobileList.isEmpty()) {
		 * 
		 * errors.add(new com.ireslab.emc.model.Error(AppCodes.INVITATION_ERROR,
		 * "EmptyEmailList", null, null)); }
		 */

		if (mobileList.size() > 0 && !mobileList.isEmpty()) {

			mobileList.forEach((mobile) -> {

				ClentAgentInvitationRequest clentAgentInvitationRequest = new ClentAgentInvitationRequest();
				clentAgentInvitationRequest.setClientId(referralRequest.getClientCorrelationId());
				ClientAgentInvitationDto agentInvitationDto = new ClientAgentInvitationDto();
				agentInvitationDto.setMobileNumber(mobile);
				agentInvitationDto.setRegister(false);

				List<ClientAgentInvitationDto> list = new ArrayList<>();
				list.add(agentInvitationDto);

				clentAgentInvitationRequest.setClientAgentInvitationList(list);
				clentAgentInvitationRequest.setInvocationType(InvocationType.save.toString());
				clentAgentInvitationRequest.setSend("SMS");
				clentAgentInvitationRequest.setSubscriptionDurations(referralRequest.getSubscriptionDurations());
				clientInfoService.saveClientAgentInvitationDetails(clentAgentInvitationRequest);

			});
		}

		userReferralResponse.setErrors(errors);
		userReferralResponse.setCode(AppCodes.SUCCESS);
		userReferralResponse.setMessage(PropConstants.INVITATION_SUCCESS_MESSAGE);
		log.info("successfully invitation send to agents email id.");
		return userReferralResponse;
	}

	@Override
	public ProductConfigurationResponse ExcelDataLoad() {
		// clientInfoService
		String filename = "C:\\Users\\Sachin\\Desktop\\gstService.xlsx";
		GstHsnSacLoadRequest gstHsnSacLoadRequest = new GstHsnSacLoadRequest();
		List<GstHsnCodeModel> hsnCodeList = new ArrayList<>();
		FileInputStream fis = null;
		try {

			fis = new FileInputStream(filename);

			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {
				XSSFRow row = (XSSFRow) rows.next();
				
				GstHsnCodeModel gstHsnCodeDto = new GstHsnCodeModel();
				if(String.valueOf( row.getCell(1).getStringCellValue()) != null && String.valueOf( row.getCell(1).getStringCellValue()) != "") {
				if (row.getCell(0).getCellType() == row.getCell(0).CELL_TYPE_NUMERIC) {

					gstHsnCodeDto.setHsnCode(BigDecimal.valueOf(row.getCell(0).getNumericCellValue()).intValue()+"");
				} else if (row.getCell(0).getCellType() == row.getCell(0).CELL_TYPE_STRING) {
					
					gstHsnCodeDto.setHsnCode(String.valueOf(row.getCell(0).getStringCellValue()));
				}
				
				
				if (row.getCell(1).getCellType() == row.getCell(1).CELL_TYPE_NUMERIC) {

					gstHsnCodeDto.setDescription(String.valueOf( row.getCell(1).getNumericCellValue()));
				} else if (row.getCell(1).getCellType() == row.getCell(1).CELL_TYPE_STRING) {
					gstHsnCodeDto.setDescription(String.valueOf( row.getCell(1).getStringCellValue()));

				}
				
				
				if (row.getCell(2).getCellType() == row.getCell(2).CELL_TYPE_NUMERIC) {

					gstHsnCodeDto.setCgst(row.getCell(2).getNumericCellValue()+"");
				} else if (row.getCell(2).getCellType() == row.getCell(2).CELL_TYPE_STRING) {

					gstHsnCodeDto.setCgst(row.getCell(2).getStringCellValue());
				} else {

					gstHsnCodeDto.setCgst(row.getCell(2).getRawValue());
				}
				
				
				if (row.getCell(3).getCellType() == row.getCell(3).CELL_TYPE_NUMERIC) {
					gstHsnCodeDto.setSgstUtgst(row.getCell(3).getNumericCellValue()+"");
				} else if (row.getCell(3).getCellType() == row.getCell(3).CELL_TYPE_STRING) {
					gstHsnCodeDto.setSgstUtgst(row.getCell(3).getStringCellValue());
				} else {
					gstHsnCodeDto.setSgstUtgst(row.getCell(3).getRawValue());
				}
				
				
				if (row.getCell(4).getCellType() == row.getCell(4).CELL_TYPE_NUMERIC) {
					
					gstHsnCodeDto.setIgst(row.getCell(4).getNumericCellValue()+"");
				} else if (row.getCell(4).getCellType() == row.getCell(4).CELL_TYPE_STRING) {
					
					gstHsnCodeDto.setIgst(row.getCell(4).getStringCellValue());
				}
				
				
				if (row.getCell(5).getCellType() == row.getCell(5).CELL_TYPE_NUMERIC) {
					
					//gstHsnCodeDto.setRelatedExportImportHsnCode(new BigInteger(row.getCell(5).getNumericCellValue()+"")+"");
					gstHsnCodeDto.setRelatedExportImportHsnCode(BigDecimal.valueOf(row.getCell(5).getNumericCellValue()).intValue()+"");
				} else if (row.getCell(5).getCellType() == row.getCell(5).CELL_TYPE_STRING) {
					
					gstHsnCodeDto.setRelatedExportImportHsnCode(String.valueOf( row.getCell(5).getStringCellValue()));
				}
				hsnCodeList.add(gstHsnCodeDto);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		gstHsnSacLoadRequest.setGstHsnCode(hsnCodeList);
		
		ProductConfigurationResponse productConfigurationResponse = productConfiguration.ExcelDataLoad(gstHsnSacLoadRequest);
		return productConfigurationResponse;
	}

	@Override
	public ClientValidateDto clientValidate(String correlationId, HttpServletRequest request) {
		ClientValidateDto clientValidateDto = new ClientValidateDto();
		ClientProfile client = clientInfoService.getClientByCorrelationId(correlationId);
		if(client.getCode() == AppCodes.UNAUTHORIZED_CODE) {
			clientValidateDto.setCode(AppCodes.UNAUTHORIZED_CODE);	
		}
		String userName = SpringSecurityUtil.usernameFromSecurityContext();
		
		
		
		if(!userName.equalsIgnoreCase(client.getUserName())) {
			clientValidateDto.setCode(AppCodes.UNAUTHORIZED_CODE);
		}else {
			
			if(client.getClientStatus().equalsIgnoreCase(Status.TERMINATED.name()) || client.getClientStatus().equalsIgnoreCase(Status.SUSPENDED.name())) {
				clientValidateDto.setCode(AppCodes.UNAUTHORIZED_CODE);
			}else {
				clientValidateDto.setCode(AppCodes.AUTHORIZED_CODE);
			}
			
			
		}
		
		return clientValidateDto;
	}

}
