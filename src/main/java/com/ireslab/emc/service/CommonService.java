package com.ireslab.emc.service;

import javax.servlet.http.HttpServletRequest;

import com.ireslab.emc.model.ClientValidateDto;
import com.ireslab.emc.model.CountryListResponse;
import com.ireslab.emc.model.PasswordResetRequest;
import com.ireslab.emc.model.PasswordResetResponse;
import com.ireslab.emc.model.UserReferralRequest;
import com.ireslab.emc.model.UserReferralResponse;
import com.ireslab.sendx.electra.model.ProductConfigurationResponse;
 

public interface CommonService extends EmailService {

	public PasswordResetResponse resetPassword(String email, HttpServletRequest req);

	public PasswordResetResponse resetPasswordWithNewPassword(PasswordResetRequest passwordResetRequest,
			HttpServletRequest req);

	public CountryListResponse getCountryList(String clientCorrelationId);

	public UserReferralResponse inviteAgent(UserReferralRequest referralRequest);

	public ProductConfigurationResponse ExcelDataLoad();

	public ClientValidateDto clientValidate(String correlationId, HttpServletRequest request);

	

}
