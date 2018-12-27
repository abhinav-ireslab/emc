package com.ireslab.emc.service;

public interface EmcOtpService {
	
	
   public String getRandomSecretKey();
	
	public String getGoogleAuthenticatorBarCode(String secretKey, String account, String issuer);
	
	public String getTOTPCode(String secretKey);

}
