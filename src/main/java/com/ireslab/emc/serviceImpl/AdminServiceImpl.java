package com.ireslab.emc.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.emc.constant.AppCodes;
import com.ireslab.emc.constant.PropConstants;
import com.ireslab.emc.entity.AdminAccount;
import com.ireslab.emc.exception.ApiException;
import com.ireslab.emc.model.AccountDetails;
import com.ireslab.emc.model.ClientInfoResponse;
import com.ireslab.emc.model.ClientProfile;
import com.ireslab.emc.model.ClientRegistrationRequest;
import com.ireslab.emc.model.ClientRegistrationResponse;
import com.ireslab.emc.model.Error;
import com.ireslab.emc.model.Login;
import com.ireslab.emc.model.LoginResponse;
import com.ireslab.emc.model.MailData;
import com.ireslab.emc.model.PasswordResetRequest;
import com.ireslab.emc.model.PasswordResetResponse;
import com.ireslab.emc.model.UserLoginRequest;
import com.ireslab.emc.model.UserLoginResponse;
import com.ireslab.emc.model.UserProfile;
import com.ireslab.emc.properties.EmcProperties;
import com.ireslab.emc.repository.AdminAccountRepository;
import com.ireslab.emc.service.AdminService;
import com.ireslab.emc.service.ClientUserInfoService;
import com.ireslab.emc.service.EmailService;
import com.ireslab.emc.service.EmcOtpService;
import com.ireslab.emc.util.AppStatusCodes;

@Service
public class AdminServiceImpl extends EmailServiceImpl implements AdminService {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(AdminServiceImpl.class);
	

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AdminAccountRepository adminRepo;
	
	@Autowired
	private ClientUserInfoService clientUserInfoService;
	
	@Autowired
	private ObjectWriter objectWriter;
	
	@Autowired
	public EmcProperties emcProperties;
	
	@Autowired
	private EmcOtpService emcOtpService;
	
	
	
	@Override
	public LoginResponse loginValidate(Login login) {
		LoginResponse loginResponse = new LoginResponse();		
		List<Error> errors = new ArrayList<>();
		
		//ClientProfile clientProfile = clientUserInfoService.getAdminByUserName(login.getUserName());
		
		AdminAccount adminAccount = adminRepo.findByAdminName(login.getUserName());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if(adminAccount == null) {
			LOG.info("admin does not exist in database..!");

			errors.add(new Error("wrongCredentials", PropConstants.WRONG_CREDENTIALS, AppCodes.WRONG_CREDENTIALS));
			loginResponse.setErrors(errors);
			loginResponse.setCode(AppCodes.LOGIN_FAIL);
			loginResponse.setMessage(PropConstants.ADMIN_WRONG_CREDENTIALS);
		}
		else {
			if(!login.getUserName().equalsIgnoreCase(adminAccount.getAdminName()) && encoder.matches(login.getPassword(), adminAccount.getPassword())) {
				
				LOG.info("Entered wrong user name !");
				errors.add(new Error("wrongCredentials", PropConstants.WRONG_CREDENTIALS, AppCodes.WRONG_CREDENTIALS));
				loginResponse.setErrors(errors);
				loginResponse.setCode(AppCodes.LOGIN_FAIL);
				loginResponse.setMessage(PropConstants.ADMIN_WRONG_CREDENTIALS);
				
			}else if(login.getUserName().equalsIgnoreCase(adminAccount.getAdminName()) && !encoder.matches(login.getPassword(), adminAccount.getPassword())) {
				
				LOG.info("Entered wrong password !");
				errors.add(new Error("wrongCredentials", PropConstants.WRONG_CREDENTIALS, AppCodes.WRONG_CREDENTIALS));
				loginResponse.setErrors(errors);
				loginResponse.setCode(AppCodes.LOGIN_FAIL);
				loginResponse.setMessage(PropConstants.ADMIN_WRONG_CREDENTIALS);
				
			}else if(!login.getUserName().equalsIgnoreCase(adminAccount.getAdminName()) && !encoder.matches(login.getPassword(), adminAccount.getPassword())) {
				
				LOG.info("Entered wrong user name and password !");
				errors.add(new Error("wrongCredentials", PropConstants.WRONG_CREDENTIALS, AppCodes.WRONG_CREDENTIALS));
				loginResponse.setErrors(errors);
				loginResponse.setCode(AppCodes.LOGIN_FAIL);
				loginResponse.setMessage(PropConstants.ADMIN_WRONG_CREDENTIALS);
			}
			else{
				/*(login.getUserName().equalsIgnoreCase(clientProfile.getUserName()) && encoder.matches(login.getPassword(), clientProfile.getPassword())) 
				if (clientProfile.isIs2faEnabled()) { 
					LOG.info("user is 2FA Enabled");

					loginResponse.setErrors(errors);
					loginResponse.setCode(AppCodes.USER_2FA_ENABLED_CODE);
					loginResponse.setMessage(PropConstants.USER_2FA_ENABLED);

				}
				else {*/
					LOG.info("user is 2FA Disabled");
					loginResponse.setErrors(errors);
					loginResponse.setCode(AppCodes.SUCCESS);
					loginResponse.setMessage(PropConstants.LOGIN_SUCCESS);
					
				//}
				
			}
			
		}
		
		return loginResponse;
	}
	
	@Override
	public PasswordResetResponse adminResetPassword(String email) {
		LOG.info("Resetting password of admin with email : " + email);
		PasswordResetResponse passwordResetResponse = new PasswordResetResponse();
		List<Error> errors = new ArrayList<>();

		// Lookup email in database

		// AccountDetail account = clientRepo.findByEmail(email);adminRepo
		//ClientProfile clientProfile = clientUserInfoService.getAdminByEmailId(email);
		AdminAccount adminAccount = adminRepo.findByEmailAddress(email);

		if (adminAccount == null) {

			LOG.info("invalid admin email entered.");

			errors.add(new Error("wrongCredentials", PropConstants.WRONG_EMAIL, AppCodes.WRONG_EMAIL));
		}else {

			// Generate random 36-character string token for reset password
			LOG.info("Generate random 36-character string token for reset password");
			String vToken = UUID.randomUUID().toString();
			adminAccount.setResetToken(vToken);

			// Save token to database
			LOG.info("Saving token to database for admin");
			 adminRepo.save(adminAccount);

			/*ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest();
			List<ClientProfile> profileList = new ArrayList<>();
			profileList.add(clientProfile);
			clientRegistrationRequest.setClientProfile(profileList);

			ClientRegistrationResponse registrationResponse = clientUserInfoService
					.updateAdminDetail(clientRegistrationRequest);*/
			
			
							
			// Email message

			MailData data = new MailData();

			data.setSendTo(adminAccount.getEmailAddress());
			data.setSubject("Password Reset Request");
			data.setBody(String.format(emcProperties.passwordResetLink, adminAccount.getAdminName(),
					emcProperties.baseUrl + String.format(emcProperties.adminPasswordResetPage, vToken)));
			LOG.info("Sending password Reset Link to mail id : "+email);
			sendMailTo(data);
			passwordResetResponse.setPasswordResetcode(AppCodes.PASSWORD_RESET_CODE);
			passwordResetResponse.setPasswordResetmessage("successMessage");
			passwordResetResponse
					.setPasswordResetInfo("A password reset link has been sent to " + adminAccount.getEmailAddress());
		}

		passwordResetResponse.setErrors(errors);

		return passwordResetResponse;
	}
	
	@Override
	public PasswordResetResponse adminResetPasswordWithNewPassword(PasswordResetRequest passwordResetRequest) {
		PasswordResetResponse passwordResetResponse = new PasswordResetResponse();
		List<Error> errors = new ArrayList<>();
		LOG.info("Finding account by reset token for admin : " + passwordResetRequest.getToken());
		
		//ClientProfile clientProfile = clientUserInfoService.getAdminByResetToken(passwordResetRequest.getToken());
		AdminAccount adminAccount = adminRepo.findByResetToken(passwordResetRequest.getToken());
		if (adminAccount == null) {
			LOG.info("invalid reset token entered.");

			errors.add(new Error("invalidRestToken", PropConstants.INVALID_RESET_TOKEN, AppCodes.INVALID_RESET_TOKEN));
		} else {

			// Set new password
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			adminAccount.setPassword(bCryptPasswordEncoder.encode(passwordResetRequest.getPassword()));

			// Set the reset token to null so it cannot be used again
			adminAccount.setResetToken(null);
			
			// Save user
			LOG.info("successfully reset your password.");
			adminRepo.save(adminAccount);

			/*ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest();
			List<ClientProfile> profileList = new ArrayList<>();
			profileList.add(clientProfile);
			clientRegistrationRequest.setClientProfile(profileList);

			ClientRegistrationResponse registrationResponse = clientUserInfoService.updateAdminDetail(clientRegistrationRequest);*/

			// Send Password reset success mail.
			MailData data = new MailData();
			data.setSendTo(adminAccount.getEmailAddress());
			data.setSubject("Password successfully updated !");
			data.setBody(String.format(emcProperties.passwordResetSuccessText, adminAccount.getAdminName(),
					emcProperties.baseUrl + emcProperties.adminLoginPage));
			sendMailTo(data);
			LOG.info("successfully reset  password mail send to admin.");
			passwordResetResponse.setPasswordResetcode(AppCodes.PASSWORD_RESET_CODE);
			passwordResetResponse.setPasswordResetmessage("successMessage");
			passwordResetResponse
					.setPasswordResetmessage("You have successfully reset your password.  You may now login.");
		}
		passwordResetResponse.setErrors(errors);

		return passwordResetResponse;
	}
	
	

	@Override
	public ClientInfoResponse getAllAccountDetails() {

		List<ClientProfile> accountsEntity = new ArrayList<>();
		//accountDetailsRepo.findAll().forEach(accountsEntity::add);
		
		ClientRegistrationResponse clientRegistrationResponse = clientUserInfoService.getAllClientData();
		clientRegistrationResponse.getClients().forEach(accountsEntity::add);

		java.lang.reflect.Type targetListType = new TypeToken<List<ClientProfile>>() {
		}.getType();
		List<AccountDetails> accountDetails = modelMapper.map(accountsEntity, targetListType);

		ClientInfoResponse clientInfoResponse = new ClientInfoResponse();
		clientInfoResponse.setClients(accountDetails);

		return clientInfoResponse;
	}
	
	

	@Override
	public Page<ClientProfile> listAllByPage(Pageable pageable, String clientCorrelationId)
			throws JsonProcessingException {

		LOG.debug("Retrieving account details list from database");
		//return accountDetailsRepo.findAllCustom(pageable);
			
		return null;
	}
	
}
