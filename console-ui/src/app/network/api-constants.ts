export class ApiConstants {

  // public static HOME_URL= "http://localhost:8086/emc/v1/"; 
  // public static HOME_URL= "http://192.168.1.75:8087/emc/";
  // public static HOME_URL = "http://192.168.1.96:8087/emc/";
  // public static HOME_URL = "http://54.252.181.154:8087/emc/";
  //public static HOME_URL = "http://35.176.71.195:8087/emc/"; //Demo
	public static HOME_URL = "http://13.250.226.42:8087/emc/"; //Development server URl
  //  public static HOME_URL = "http://192.168.1.32:8087/emc/"; //local server URl/
  public static CO_ID: string;
  public static cKey: string;
  public static sKey: string;
  public static ledgerAccount: string;

  public static get CREATE_ACCOUNT_URL(): string { return this.HOME_URL + "v1/addAccount"; };
  public static get CLIENT_LOGIN_URL(): string { return this.HOME_URL + "validateCredential"; };
  public static get LOGIN_OTP_URL(): string { return this.HOME_URL + "validateCredentialWithOtp"; };
  public static get GET_ACCOUNT_URL(): string { return this.HOME_URL + "v1/getaccount?clientCorrelationId=" + this.getCO_ID() };
  public static get UPDATE_ACCOUNT_URL(): string { return this.HOME_URL + "v1/clientupdate" };
  public static get GET_USERS_URL(): string { return this.HOME_URL + "users/" + this.getCO_ID() };
  public static get LOAD_TOKENS_URL(): string { return this.HOME_URL + "loadTokens/" + this.getCO_ID(); };
  public static get REQUEST2FA_URL(): string { return this.HOME_URL + "enable2fa"; };
  public static get FORGOT_URL(): string { return this.HOME_URL + "forgot?email="; };
  public static get RESET_PASSWORD_URL(): string { return this.HOME_URL + "resetPassword"; };
  public static get CONFIG_CLIENT_TOKEN_URL(): string { return this.HOME_URL + "configureClientAssetToken"; };
  public static get REQUEST_BUSINESS_LEDGER_URL(): string { return this.HOME_URL + "getFilterLedger/"+ this.getCO_ID(); };
  public static get DOWNLOAD_BUSINESS_LEDGER_URL(): string { return this.HOME_URL + "downloadExcelAndCsv/"+ this.getCO_ID(); };
  public static get REQUEST_USER_LEDGER_URL(): string { return this.HOME_URL + "requestUserledger/" };
  public static get GET_ALL_CLIENTS(): string { return this.HOME_URL + "getClientPageData" };
  public static get GET_ALL_TOKENS(): string { return this.HOME_URL + "getAllClientAssetTokenPages/" + this.getCO_ID(); };
  public static get GET_EXCHANGE_RATE(): string { return this.HOME_URL + "getExchangeDetails" };
  public static get GET_COUNTRY_LIST(): string { return this.HOME_URL + "getCountryList/"+ this.getCO_ID();};
  public static get SEND_INVITE_LIST(): string { return this.HOME_URL + "inviteAgent"};
  public static get UPDATE_FEE(): string { return this.HOME_URL + "configureExchange/"+this.getCO_ID();};
  public static get GET_KYC_STATUS(): string { return this.HOME_URL + "ekycEkybApprovelList"};
  public static get GET_APPROVED_KYC(): string { return this.HOME_URL + "ekycEkybApprovedList"};
  public static get UPDATE_KYC_STATUS(): string { return this.HOME_URL + "approveEkycEkyb"};

  public static get SuspendUser(): string { return this.HOME_URL + "updateClient" };
  public static get UpdateUser(): string { return this.HOME_URL +  this.getCO_ID() + "/" +"updateUser" };
 
  // http://192.168.1.113:8087/emc/client/updateUser/usercid
  public static getCO_ID(): string {
    this.CO_ID = window.localStorage.getItem("cID")
    return this.CO_ID;
  };
  public static getcKey(): string {
    this.cKey = window.localStorage.getItem("cKey")
    return this.cKey;
  };
  public static getsKey(): string {
    this.sKey = window.localStorage.getItem("sKey")
    return this.sKey;
  };
  public static getLedgerAccount(): string {
    this.ledgerAccount = window.localStorage.getItem("baseTXN");
    return this.ledgerAccount;
  };

}