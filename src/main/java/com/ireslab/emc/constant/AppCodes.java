package com.ireslab.emc.constant;

/**
 * @author Nitin
 *
 */
public interface AppCodes {

	
	public static final Integer WRONG_CREDENTIALS =3000;
	public static final Integer LOGIN_VALID_WITH_2FA_ENABLE =3001;
	public static final Integer LOGIN_VALID_WITHOUT_2FA_ENABLE =3002;
	public static final Integer SUCCESS =2000 ;
	public static final Integer LOGIN_FAIL = 2001;
	public static final Integer USER_2FA_ENABLED_CODE = 2002;
	public static final Integer USER_2FA_DISABLED_CODE = 2003;
	public static final Integer IS_PASSWORD_RESET = 2006;
	public static final Integer BAD_REQUEST =4001 ;              // send security code from ui service 
	public static final Integer LOGIN_SUCCESS_WITH_2FA = 2004;   //LOGIN_SUCCESS_WITH_2FA
	public static final Integer LOGIN_FAIL_WITH_2FA = 2005;      //LOGIN_FAIL_WITH_2FA
	public static final Integer WRONG_EMAIL = 5001; // WRONG_EMAIL
	public static final int PASSWORD_RESET_CODE = 5002; //password resET SUCCESS
	public static final Integer INVALID_RESET_TOKEN = 5003;
	public static final Integer ACCOUNT_SUSPENDED = 5004;//INVALID_RESET_TOKEN
	public static final Integer INVITATION_ERROR = 6000;//INVITATION_ERROR
	public static final Integer INVITATION_SUCCESS = 6001; //INVITATION_SUCCESS
	public static final Integer SCRIPTION_STATUS = 6001; //INVITATION_SUCCESS
	public static final Integer AUTHORIZED_CODE = 1100;
	public static final Integer UNAUTHORIZED_CODE = 1101;
	
	
	
	
}
