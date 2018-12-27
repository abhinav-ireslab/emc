package com.ireslab.emc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ireslab.emc.model.ClientInfoResponse;
import com.ireslab.emc.model.ClientProfile;
import com.ireslab.emc.model.Login;
import com.ireslab.emc.model.LoginResponse;
import com.ireslab.emc.model.PasswordResetRequest;
import com.ireslab.emc.model.PasswordResetResponse;
import com.ireslab.emc.model.UserLoginRequest;
import com.ireslab.emc.model.UserLoginResponse;

public interface AdminService {
	
	/*List<AccountDetails>*/ public ClientInfoResponse getAllAccountDetails();
	
	Page<ClientProfile> listAllByPage(Pageable pageable, String clientCorrelationId) throws JsonProcessingException;

	public LoginResponse loginValidate(Login login);

	public PasswordResetResponse adminResetPassword(String email);

	public PasswordResetResponse adminResetPasswordWithNewPassword(PasswordResetRequest passwordResetRequest);

	

}
