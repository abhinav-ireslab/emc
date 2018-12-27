import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators, PatternValidator } from '@angular/forms';
import { ApiConstants } from '../../network/api-constants';
import { ServiceCall } from '../../network/web-service-call';
import { Http, Response, Headers } from '@angular/http';
import { AccountLedgerService } from '../user-account-ledger/user-account-ledger.service';
import { AbstractControl } from '@angular/forms';
import { AlertComponent } from '../../alert/alert.component';
import { AlertNotification } from '../../alert-notification';

declare var $: any;
declare var require: any;
@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit {
  currentMessage: string;
  noRecord: boolean;
  loader: boolean = false;
  loaderMain: boolean = false;
  _indexToDel: number;
  tokenValueInvalid: boolean = false;
  userBalance: any = '00.00';
  serverError: boolean = false;
  // ShowLoading = true;
  tokenList: any;
  usersList: any = [];
  serverErrMsg: any = '';
  tempUserDetails: any;
  tokenValueInput: any;
  nextLedger: any;
  noMoreRecords: boolean = false;
  _userRecords: any = [];
  scrollFunctionComplete: boolean = false;
  _selectedUserObj: any;
  loaderForLedger: boolean = false;
  _noAssetDetails: boolean = false;
  _noPayDetails: boolean = false;
  AccountHolderName: String;
  _userAssets: any = [];
  tokenCount: number = null;
  itemsPerPageAllowed: any = 10;
  modalScrollDistance = 10;
  modalScrollThrottle = 5;
  p: any;


  subsPlan: any;
  subsUsers: any;
  subsRemain: any;
  countryList: any = [];
  emailEntered: string = "";
  mobileEntered: string = "";
  countryCode: any;
  numberValid: boolean = true;
  mobileArray: any = []
  emailArray: any = []
  coid;
  allowedSubscription;

  editUserForm: FormGroup;
  IssueTokenForm: FormGroup;
  inviteForm: FormGroup;
  constructor(private http: Http, private als: AccountLedgerService, private alert: AlertComponent) { }

  ngOnInit() {
    this.p = 1;
    this.currentMessage = "";
    this.noRecord = false;
    this.createEditUserForm(null, "");
    this.createIssueTokenForm();
    this.createInviteForm();
    this.getUsers();
    this.coid = ApiConstants.getCO_ID();
    this.countryCode = "+65"
  }

  createEditUserForm(_user, Flag) {
    let _firstName = "";
    let _lastName = "";
    let _Email = "";
    let _Contact = "";
    let _CorrelationID = "";
    let _UserStatus = "";

    if (Flag == "Edit") {
      _firstName = _user.firstName;
      _lastName = _user.lastName;
      _CorrelationID = _user.userCorrelationId;
      _UserStatus = _user.status;// == "ACTIVE" ? "0" : _user.status == "SUSPENDED" ? "1" : _user.status == "TERMINATED" ? "2" : "";
    }

    this.editUserForm = new FormGroup({
      firstName: new FormControl({ value: _firstName, disabled: true }),
      lastName: new FormControl({ value: _lastName, disabled: true }),
      CorrelationID: new FormControl({ value: _CorrelationID, disabled: true }),
      UserStatus: new FormControl(_UserStatus)
    });
  }

  createInviteForm() {
    this.inviteForm = new FormGroup({
      inviteEmail: new FormControl('', [Validators.pattern(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/i)]),
      inviteCode: new FormControl({ value: "+65", disabled: false }, []),
      inviteMobile: new FormControl('', [Validators.pattern("^[0-9]*$"), Validators.minLength(6)]),

    })
  }

  createIssueTokenForm() {
    this.tokenValueInvalid = false;
    this.userBalance = '00.00';
    this.IssueTokenForm = new FormGroup({
      selectToken: new FormControl('', [Validators.required]),
      tokenCount: new FormControl('', [Validators.required, Validators.pattern(/^\d+(\.\d+)?$/)])
    })
  }


  fillEditValues(_users, _index) {
    window.localStorage.setItem('userCorrelationId', _users.userCorrelationId);
    this._indexToDel = _index;
    this.createEditUserForm(_users, "Edit");
  }

  userAssetBalance() {
    this.userBalance = this.IssueTokenForm.controls.selectToken.value.assetQuantity;
  }

  getUserDetail(_data) {
    this.serverError = false;
    this.tempUserDetails = _data
    this.tokenList = _data.assetDetails
  }

  validateTokenValue(e) {
    this.tokenValueInvalid = false;
    if (e.target.value.charAt(0) == '.') {
      this.tokenValueInput = "0.";
    } else {
      this.tokenValueInput = e.target.value;
    }

    $('input').on('input', function () {
      this.value = this.value
        .replace(/(\..*)\./, '$1')
        .replace(/(\.[\d]{7})./, '$1')

    });
    if (e.target.value == 0) {
      for (let control in this.IssueTokenForm.controls) {
        this.tokenValueInvalid = true;
      }
    }
  }

  issueToken() {
    this.serverError = false;
    if (this.IssueTokenForm.invalid) {
      for (let control in this.IssueTokenForm.controls) {
        this.IssueTokenForm.get(control).markAsTouched();
        this.IssueTokenForm.get(control).invalid;
        this.IssueTokenForm.get(control).updateValueAndValidity();
        // console.error("form is not valid")
      }
      return
    } else if (this.tokenValueInvalid == true) {
      // console.error("Token value is not valid")
      return
    } else {
      this.loader = true;
      this.loaderMain = true;
      let _url = ApiConstants.LOAD_TOKENS_URL;
      let _reqJson = {
        "accountStatus": "true",
        "noOfTokens": this.IssueTokenForm.controls.tokenCount.value,
        "status": this.tempUserDetails.status,
        "tokenCorrelationId": this.IssueTokenForm.controls.selectToken.value,
        "userCorrelationId": this.tempUserDetails.userCorrelationId,
      }
      ServiceCall.httpPostCall(_reqJson, _url, this.http).subscribe(
        (data) => {
          // console.log(data);
          this.loader = false;
          this.loaderMain = false;
          if (data.status == 200) {
            if (data.code == 100) {
              this.tokenList = []
              this.createIssueTokenForm();
              $('#tokensIssue').modal('hide')
              this.usersList = data.clientUsersInfoResponse.users
              this.currentMessage = AlertNotification.tokenIssue;
              this.alert.showAlert();
            } else if (data.code == 805) {
              this.serverError = true;
              this.serverErrMsg = "Insufficient fund to transfer.";
            }
            else {
              this.currentMessage = AlertNotification.serverErr;
              this.alert.showAlert();
            }
          }
        },
        (error) => {
          this.loader = false;
          this.loaderMain = false;
          this.currentMessage = AlertNotification.serverErr;
          this.alert.showAlert();
          // this.serverErrMsg = 'Your request cannot be completed at the moment. Please contact administrator';
          // console.error(error);
        });
    }
  }

  getUsers() {
    this.loader = true;
    this.loaderMain = true;
    let _url = ApiConstants.GET_USERS_URL;
    let _reqJson = "";
    ServiceCall.httpGetCall(_url, _reqJson, this.http).subscribe(
      (data) => {
        // console.log(data)
        this.loader = false;
        this.loaderMain = false;
        if (data.users.length > 0) {
          this.noRecord = false;
          this.usersList = data.users;
        } else {
          this.noRecord = true;
        }
      },
      (error) => {
        this.loader = false;
        this.loaderMain = false;
        this.currentMessage = AlertNotification.serverErr;
        this.alert.showAlert();
      });
  }

  openUserViewReportModal(_user) {
    window.localStorage.setItem('selectedUserObj', JSON.stringify(_user))
    this.nextLedger = 0;
    this.userViewReport();
    this.noMoreRecords = false;
  }

  closeViewReportModal() {
    $('#Reporting').modal('hide')
    this._userRecords = [];
  }

  userViewReport() {
    if (this.scrollFunctionComplete == true || this.nextLedger == 1 && this.scrollFunctionComplete == false) {
      return;
    } else {
      this.scrollFunctionComplete = true;
      this._selectedUserObj = JSON.parse(window.localStorage.getItem('selectedUserObj'));
      this.loaderForLedger = true;
      this._noAssetDetails = false;
      this._noPayDetails = false;
      this.AccountHolderName = this._selectedUserObj.firstName + ' ' + this._selectedUserObj.lastName;
      let _userPubKey = this._selectedUserObj.accountPublicKey;
      let _url = ApiConstants.REQUEST_USER_LEDGER_URL + _userPubKey + '/' + this.nextLedger + '/' + this.itemsPerPageAllowed;
      ServiceCall.httpGetCall(_url, '', this.http).subscribe(
        (data) => {
          // console.log(data)
          if (data.next == 'undefined' || data.next == undefined || data.next == '') {
            this.noMoreRecords = true;
          } else {
            this.nextLedger = data.next;
          }
          if (data.assets.length == 0) {
            this._noAssetDetails = true;
          }
          if (data.assets.length > 0) {
            this._userAssets = data.assets;
          }
          if (data.payment.length == 0 && this._userRecords.length == 0) {
            this._noPayDetails = true;
          }
          if (data.payment.length > 0) {
            for (let payment of data.payment) {
              this._userRecords.push(payment);
            }
            if (data.payment.length < 10) {
              this.noMoreRecords = true;
              this.nextLedger = 1;
            }
          }
          this.loaderForLedger = false;
          this.scrollFunctionComplete = false;
        },
        (error: any) => {
          // this.loaderForLedger = false;
          this.scrollFunctionComplete = false;
          this.currentMessage = AlertNotification.serverErr;
          this.alert.showAlert();
          // console.log(error)
        }
      );
    }
  }

  SaveUserId(_userDetail, _index) {
    window.localStorage.setItem('userCorrelationId', _userDetail.userCorrelationId);
    this._indexToDel = _index;
  }

  SuspendUser(flag) {
    if (flag == 'Y') {
      $('#ConfirmationModal1').modal('hide');
      this.loader = true;
      this.loaderMain = true;
      let url = ApiConstants.UpdateUser + "/" + window.localStorage.getItem("userCorrelationId");
      let sType = "TERMINATED";

      let reqJson = {
        "users": [
          {
            "status": sType
          }
        ]
      };

      ServiceCall.httpPutCall(url, reqJson, this.http).subscribe(
        (data) => {
          this.loader = false;
          this.loaderMain = false;

          if (data.code = "200") {
            this.currentMessage = AlertNotification.terminateSuc;
            this.alert.showAlert();
            this.usersList.splice(((this.p - 1) * 10) + this._indexToDel, 1);
          }
        },
        (error: any) => {
          this.loader = false;
          this.loaderMain = false;
          this.currentMessage = AlertNotification.serverErr;
          this.alert.showAlert();
        }
      );

    }
    else {
      $('#ConfirmationModal1').modal('hide');
    }
  }

  modalClose() {
    $('#editUserModal').modal('hide');
  }

  SaveUser() {
    this.loader = true;
    this.loaderMain = true;
    let sType = this.editUserForm.controls['UserStatus'].value;
    let CorrelationID = this.editUserForm.controls['CorrelationID'].value;
    let url = ApiConstants.UpdateUser + "/" + CorrelationID;
    let reqJson = {
      "users": [
        {
          "status": sType
        }
      ]
    };

    ServiceCall.httpPutCall(url, reqJson, this.http).subscribe(
      (data) => {
        this.loader = false;
        this.loaderMain = false;
        this.usersList[((this.p - 1) * 10) + this._indexToDel].status = sType;

        if (data.code = "200") {
          $('#editUserModal').modal('hide');
        }
      },
      (error: any) => {
        this.loader = false;
        this.loaderMain = false;
        this.currentMessage = AlertNotification.serverErr;
        this.alert.showAlert();
      }
    );
  }

  sendInvite() {
    if (this.emailArray.length == 0 && this.mobileArray.length == 0) {
      this.currentMessage = AlertNotification.mobMailErr;
      this.alert.showAlert();
    } else {
      this.loader = true;
      this.loaderMain = true;
      let url = ApiConstants.SEND_INVITE_LIST;
      let _reqJson = {
        "clientCorrelationId": this.coid,
        "emailList": this.emailArray,
        "mobileList": this.mobileArray,
      }

      ServiceCall.httpPostCall(_reqJson, url, this.http).subscribe(
        (data) => {

          this.loader = false;
          this.loaderMain = false;
          // console.log(data);
          if (data.code == 2000) {
            $("#inviteModal").modal('hide');
            alert(data.message)
          }

        },
        (error: any) => {
          this.loader = false;
          this.loaderMain = false;
          this.currentMessage = AlertNotification.serverErr;
          this.alert.showAlert();
        }
      );
    }

  }



  addCode(e) {
    this.countryCode = e.target.value;
  }

  getAllowedUsers() {
    let allowedInvites = 10;
    this.allowedSubscription = this.emailArray.length + this.mobileArray.length;
    if (this.allowedSubscription == allowedInvites) {
      this.inviteForm.controls['inviteEmail'].disable();
      this.inviteForm.controls['inviteMobile'].disable();
      this.inviteForm.controls['inviteCode'].disable();
      this.currentMessage = AlertNotification.inviteLengthErr;
      this.alert.showAlert();
    }
  }

  addEmail() {
    let _email = this.inviteForm.controls['inviteEmail'].value;
    if (_email != null && _email != "") {
      if (this.inviteForm.controls['inviteEmail'].valid) {
        this.emailEntered = this.emailEntered + _email + ";";
        this.emailArray = [];
        this.emailArray = this.emailEntered.slice(0, -1).split(';');
        this.getAllowedUsers();
        this.inviteForm.controls['inviteEmail'].reset();
      }
    } else {
      this.inviteForm.controls['inviteEmail'].setErrors({ 'pattern': true });
    }
  }

  addMobile() {
    let _mobile = this.inviteForm.controls['inviteMobile'].value;
    if (_mobile != null && _mobile != "") {
      if (this.inviteForm.controls['inviteMobile'].valid) {
        this.mobileEntered = this.mobileEntered + this.countryCode + "-" + _mobile + ";";
        this.mobileArray = [];
        this.mobileArray = this.mobileEntered.slice(0, -1).split(';');
        this.getAllowedUsers();
        this.inviteForm.controls['inviteMobile'].reset();
      }
    } else {
      this.inviteForm.controls['inviteMobile'].setErrors({ 'pattern': true });
    }
  }



  formReset() {
    this.inviteForm.controls['inviteEmail'].enable();
    this.inviteForm.controls['inviteMobile'].enable();
    this.inviteForm.controls['inviteCode'].enable();
    this.inviteForm.controls['inviteEmail'].reset();
    this.inviteForm.controls['inviteMobile'].reset();
    this.inviteForm.controls['inviteCode'].setValue("+65");
    this.inviteForm.controls['inviteCode'].updateValueAndValidity();
    this.emailEntered = "";
    this.mobileEntered = "";
    this.countryCode = "+65";
    this.emailArray = [];
    this.mobileArray = [];
  }

  getInvite() {
    this.loader = true;
    this.loaderMain = true;
    let _url = ApiConstants.GET_COUNTRY_LIST;
    let _reqjson = ""

    ServiceCall.httpGetCall(_url, _reqjson, this.http).subscribe(
      (data) => {
        // let data = { "status": 200, "code": 100, "message": "Success", "errors": [], "countryDetails": [{ "countryId": 1, "countryDialCode": "+65", "countryName": "Singapore", "iso4217CurrencyAlphabeticCode": "SGD", "iso4217CurrencyName": "Singapore Dollar" }, { "countryId": 2, "countryDialCode": "+91", "countryName": "India", "iso4217CurrencyAlphabeticCode": "INR", "iso4217CurrencyName": "Indian Rupee" }, { "countryId": 3, "countryDialCode": "+66", "countryName": "Thailand", "iso4217CurrencyAlphabeticCode": "THB", "iso4217CurrencyName": "Thai baht" }, { "countryId": 4, "countryDialCode": "+61", "countryName": "Australia", "iso4217CurrencyAlphabeticCode": "AUD", "iso4217CurrencyName": "Australian dollar" }, { "countryId": 5, "countryDialCode": "+1", "countryName": "USA", "iso4217CurrencyAlphabeticCode": "USD", "iso4217CurrencyName": "US dollar" }, { "countryId": 6, "countryDialCode": "+32", "countryName": "Belgium", "iso4217CurrencyAlphabeticCode": "EUR", "iso4217CurrencyName": "Euro" }, { "countryId": 7, "countryDialCode": "+60", "countryName": "Malaysia", "iso4217CurrencyAlphabeticCode": "MYR", "iso4217CurrencyName": "Malaysian Ringgit" }, { "countryId": 8, "countryDialCode": "+62", "countryName": "Indonesia", "iso4217CurrencyAlphabeticCode": "IDR", "iso4217CurrencyName": "Indonesian Rupiah" }, { "countryId": 9, "countryDialCode": "+63", "countryName": "Philippines", "iso4217CurrencyAlphabeticCode": "PHP", "iso4217CurrencyName": "Philippine peso" }, { "countryId": 10, "countryDialCode": "+852", "countryName": "Hong Kong", "iso4217CurrencyAlphabeticCode": "HKD", "iso4217CurrencyName": "Hong Kong Dollar" }, { "countryId": 11, "countryDialCode": "+44", "countryName": "United Kingdom", "iso4217CurrencyAlphabeticCode": "GBP", "iso4217CurrencyName": "British Pound" }, { "countryId": 12, "countryDialCode": "+27", "countryName": "South Africa", "iso4217CurrencyAlphabeticCode": "ZAR", "iso4217CurrencyName": "South African Rand" }], "subscriptionPlan": 10, "subscribedUser": 0, "remainingInvitation": 10 }
        this.countryList = data.countryDetails;
        this.subsPlan = data.subscriptionPlan;
        this.subsUsers = data.subscribedUser;
        this.subsRemain = data.remainingInvitation;
        // this.subsRemain = 2;
        this.loader = false;
        this.loaderMain = false;
        if (this.subsPlan == 0) {
          this.currentMessage = AlertNotification.noSubsInvite;
          this.alert.showAlert();
        } else if (this.subsRemain < 1) {
          this.currentMessage = AlertNotification.endSubsInvite;
          this.alert.showAlert();
        } else {
          this.formReset();
          $("#inviteModal").modal('show');
        }

      },
      (error: any) => {
        this.loader = false;
        this.loaderMain = false;
        this.currentMessage = AlertNotification.serverErr;
        this.alert.showAlert();
      }

    );
  }

  get selectToken() { return this.IssueTokenForm.get('selectToken'); }
  get tokenNo() { return this.IssueTokenForm.get('tokenCount'); }

  get inviteEmail() { return this.inviteForm.get('inviteEmail'); }
  get inviteCode() { return this.inviteForm.get('inviteCode'); }
  get inviteMobile() { return this.inviteForm.get('inviteMobile'); }

}

