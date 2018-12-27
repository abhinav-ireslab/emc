package com.ireslab.emc.service;

import javax.servlet.http.HttpServletRequest;

import com.ireslab.emc.model.Login;
import com.ireslab.emc.model.LoginResponse;
import com.ireslab.emc.model.UserLoginRequest;
import com.ireslab.emc.model.UserLoginResponse;

public interface UiService {
	
	
	/*public boolean loginValidate(Login login);*/
	//public LoginResponse loginValidate(Login login);

	UserLoginResponse validateAllUsercredentials(UserLoginRequest loginRequest, HttpServletRequest request);

	UserLoginResponse enableDisable2faSetting(UserLoginRequest loginRequest);
}
