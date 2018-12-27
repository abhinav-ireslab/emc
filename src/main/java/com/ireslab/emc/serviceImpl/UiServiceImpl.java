package com.ireslab.emc.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ireslab.emc.constant.AppCodes;
import com.ireslab.emc.constant.PropConstants;
import com.ireslab.emc.exception.ApiException;
import com.ireslab.emc.model.ClientProfile;
import com.ireslab.emc.model.ClientRegistrationRequest;
import com.ireslab.emc.model.ClientRegistrationResponse;
import com.ireslab.emc.model.Login;
import com.ireslab.emc.model.LoginResponse;
import com.ireslab.emc.model.UserLoginRequest;
import com.ireslab.emc.model.UserLoginResponse;
import com.ireslab.emc.model.UserProfile;
import com.ireslab.emc.properties.EmcProperties;
import com.ireslab.emc.service.ClientUserInfoService;
import com.ireslab.emc.service.EmcOtpService;
import com.ireslab.emc.service.UiService;
import com.ireslab.emc.util.AppStatusCodes;
import com.ireslab.emc.util.Status;
import com.ireslab.emc.model.Error;


@Service
public class UiServiceImpl implements UiService {
	private static final Logger log = LoggerFactory.getLogger(UiServiceImpl.class);

	@Autowired
	private EmcProperties emcProperties;

	@Autowired
	private EmcOtpService emcOtpService;
	
	@Autowired
	public ClientUserInfoService clientInfoService;

	

	/* User validateAllUsercredentials Service */
	@Override
	public UserLoginResponse validateAllUsercredentials(UserLoginRequest loginRequest, HttpServletRequest request) {

		UserProfile profile = loginRequest.getUsers();
		UserLoginResponse userLoinResponse = new UserLoginResponse();

		List<Error> errors = new ArrayList<>();
		List<UserProfile> userList = new ArrayList<>();

		if (profile == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, AppStatusCodes.ACCOUNT_NOT_EXISTS, null);
		}

		// TODO : check user valid
		log.info("finding user in  electra database");
		//AccountDetail account = accountRepo.findByUsername(profile.getUserName());
		ClientProfile account = clientInfoService.getClientByUserName(profile.getUserName());

		if (account.getUserName() == null) {

			log.info("user does not exist in database..!");

			errors.add(new Error("wrongCredentials", PropConstants.WRONG_CREDENTIALS, AppCodes.WRONG_CREDENTIALS));
			userLoinResponse.setErrors(errors);
			userLoinResponse.setCode(AppCodes.LOGIN_FAIL);// 2001
			userLoinResponse.setMessage(PropConstants.WRONG_CREDENTIALS);

		} else {

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			// Cases
			// Case 1 - Terminate
			// Case 2 - Suspend
			// Case 3 - Credentials wrong

			if (account.getClientStatus().equalsIgnoreCase(Status.SUSPENDED.name())) {
				log.info("client suspanded! ");
				errors.add(new Error("wrongCredentials", PropConstants.WRONG_CREDENTIALS, AppCodes.WRONG_CREDENTIALS));
				userLoinResponse.setErrors(errors);
				userLoinResponse.setCode(AppCodes.LOGIN_FAIL);// 2001
				userLoinResponse.setMessage(PropConstants.ACCOUNT_SUSPENDED);

			} else if (account.getClientStatus().equalsIgnoreCase(Status.TERMINATED.name())) {
				log.info("client terminated! ");
				errors.add(new Error("wrongCredentials", PropConstants.WRONG_CREDENTIALS, AppCodes.WRONG_CREDENTIALS));
				userLoinResponse.setErrors(errors);
				userLoinResponse.setCode(AppCodes.LOGIN_FAIL);// 2001
				userLoinResponse.setMessage(PropConstants.ACCOUNT_TERMINATED);

			}
			else if (account.getIsSubscriptionExpired()) {
				log.info("client subscription expired! ");
				errors.add(new Error("wrongCredentials", PropConstants.SUBSCRIPTION_STATUS, AppCodes.WRONG_CREDENTIALS));
				userLoinResponse.setErrors(errors);
				userLoinResponse.setCode(AppCodes.LOGIN_FAIL);// 2001
				userLoinResponse.setMessage(PropConstants.SUBSCRIPTION_STATUS);

			}else if (profile.getUserName().equalsIgnoreCase(account.getUserName())
					&& encoder.matches(profile.getPassword(), account.getPassword())
					&& account.getClientStatus().equalsIgnoreCase(Status.ACTIVE.name())) {
				// open for 2FA configuration

				if (account.isIs2faEnabled()) { // TODO : Check if 2 FA
					log.info("user is 2FA Enabled");

					userLoinResponse.setErrors(errors);
					profile.setPassword(null);
					profile.setClientCorrelationId(account.getClientCorrelationId());
					profile.setClientCredentialActivated(account.isClientCredentialActivated());
					userList.add(profile);
					userLoinResponse.setUsers(userList);
					userLoinResponse.setCode(AppCodes.USER_2FA_ENABLED_CODE);
					userLoinResponse.setMessage(PropConstants.USER_2FA_ENABLED);

					return userLoinResponse;

				}
				else if(account.isPasswordReset()){
					
					userLoinResponse.setErrors(errors);
					profile.setPassword(null);
					profile.setClientCorrelationId(account.getClientCorrelationId());
					profile.setClientCredentialActivated(account.isClientCredentialActivated());
					userList.add(profile);
					userLoinResponse.setUsers(userList);
					userLoinResponse.setCode(AppCodes.IS_PASSWORD_RESET);
					userLoinResponse.setMessage(PropConstants.IS_PASSWORD_RESET);

					return userLoinResponse;
					
				}else {
					log.info("user is 2FA Disabled");
					userLoinResponse.setErrors(errors);
					profile.setPassword(null);
					profile.setClientCorrelationId(account.getClientCorrelationId());
					profile.setClientCredentialActivated(account.isClientCredentialActivated());
					userList.add(profile);
					userLoinResponse.setUsers(userList);
					userLoinResponse.setCode(AppCodes.USER_2FA_DISABLED_CODE);
					userLoinResponse.setMessage(PropConstants.USER_2FA_DISABLED);
					return userLoinResponse;
				}

			} else {
				log.info("Wrong credentials! ");
				errors.add(new Error("wrongCredentials", PropConstants.WRONG_CREDENTIALS, AppCodes.WRONG_CREDENTIALS));

				userLoinResponse.setErrors(errors);
				profile.setPassword(null);
				userList.add(profile);
				userLoinResponse.setUsers(userList);
				userLoinResponse.setCode(AppCodes.LOGIN_FAIL);// 2001
				userLoinResponse.setMessage(PropConstants.WRONG_CREDENTIALS);

			}
		}

		return userLoinResponse;

	}

	@Override
	public UserLoginResponse enableDisable2faSetting(UserLoginRequest loginRequest) {

		UserProfile profile = loginRequest.getUsers();
		UserLoginResponse userLoinResponse = new UserLoginResponse();

		List<Error> errorsList = userLoinResponse.getErrors();
		List<UserProfile> userList = new ArrayList<>();

		if (profile == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, AppStatusCodes.ACCOUNT_NOT_EXISTS, null);
		}

		//AccountDetail account = accountRepo.findByUsername(profile.getUserName());
		
		ClientProfile account = clientInfoService.getClientByUserName(profile.getUserName());

		if (profile.getEnable2fa().equals("ENABLE")) {

			log.info("Enable 2fa request received.");

			if (profile.getSecurityCode() == null) {

				errorsList.add(
						new Error("SecurityCodeVerificationMessage", "security code not saved", AppCodes.BAD_REQUEST));
			} else {

				account.setSecurityCode(profile.getSecurityCode());
				account.setIs2faEnabled(true);
				log.info("uPDATING 2FA.");
				//accountRepo.save(account);
				
				ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest();
				List<ClientProfile> profileList = new ArrayList<>();
				profileList.add(account);
				clientRegistrationRequest.setClientProfile(profileList);
				
				ClientRegistrationResponse registrationResponse = clientInfoService
						.updateClientDetail((ClientRegistrationRequest) clientRegistrationRequest.setClientId(account.getClientCorrelationId()));
				
				log.info("uPDATING 2FA.");
			}

		} else if (profile.getEnable2fa().equals("DISABLE")) {

			log.info("Disable 2fa request received.");
			account.setIs2faEnabled(false);
			//accountRepo.save(account);
			
			ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest();
			List<ClientProfile> profileList = new ArrayList<>();
			profileList.add(account);
			clientRegistrationRequest.setClientProfile(profileList);
			
			ClientRegistrationResponse registrationResponse = clientInfoService
					.updateClientDetail((ClientRegistrationRequest) clientRegistrationRequest.setClientId(account.getClientCorrelationId()));
			
		} else if (profile.getEnable2fa().equals("ACTIVATE")) {

			log.info("2FA Activation request received.");
			String securityCode = emcOtpService.getRandomSecretKey();
			System.out.println("USERNAME :" + profile.getUserName());
			profile.setGoogleAuthenticatorBarCode(emcOtpService.getGoogleAuthenticatorBarCode(securityCode,
					profile.getUserName(), "ElectraMgmtConsole"));
			System.out.println("GOOGLEAUTHENTICATORBARCODE :" + emcOtpService
					.getGoogleAuthenticatorBarCode(securityCode, profile.getUserName(), "ElectraMgmtConsole"));

			profile.setSecurityCode(securityCode);
		}

		userLoinResponse.setErrors(errorsList);

		userList.add(profile);
		userLoinResponse.setUsers(userList);

		return userLoinResponse;
	}

	public UserLoginResponse validateOTP(UserLoginRequest loginRequest) {
		UserProfile profile = loginRequest.getUsers();
		List<UserProfile> userList = new ArrayList<>();
		//AccountDetail client = accountRepo.findByUsername(profile.getUserName());
		ClientProfile client = clientInfoService.getClientByUserName(profile.getUserName());
		
		String totpCode = emcOtpService.getTOTPCode(client.getSecurityCode());
		log.info("totp Code of user is :" + totpCode + " user typed totp Code :" + profile.getOtpCode());

		UserLoginResponse loginResponse = new UserLoginResponse();
		if (totpCode.equals(profile.getOtpCode())) {
			loginResponse.setCode(AppCodes.LOGIN_SUCCESS_WITH_2FA);
			loginResponse.setMessage("show dashboard");
			profile.setClientCorrelationId(client.getClientCorrelationId());
			userList.add(profile);
			loginResponse.setUsers(userList);

		} else {

			loginResponse.setCode(AppCodes.LOGIN_FAIL_WITH_2FA);
			loginResponse.setMessage("otp entered in wrong.");
		}

		return loginResponse;
	}

}
