import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  // editUserForm: FormGroup;
  // IssueTokenForm: FormGroup;
  // exchangeForm: FormGroup;
  // usersList: IUsers[];
  // tempUserDetails: any;
  // tokenList: any;
  _UserNAme: any;
  // userBalance: any = '00.00';
  // tokenCount: number = null;
  // StatusMessage = 'Loading Data.......';
  // ShowLoading = true;
  // AccountHolderName: String;
  // _userRecords: any = [];
  // _userAssets: any = [];
  // _noAssetDetails: boolean = false;
  // _noPayDetails: boolean = false;
  // noRecord: boolean = false;
  // loader: boolean = false;
  // loaderMain: boolean = false;
  // serverError: boolean = false;
  // tokenValueInvalid: boolean = false;
  // serverErrMsg: any = '';
  // tokenValueInput: any;
  // itemsPerPageAllowed: any = 10;
  // nextLedger: any;
  // modalScrollDistance = 10;
  // modalScrollThrottle = 5;
  // _selectedUserObj: any;
  // loaderForLedger: boolean = false;
  // scrollFunctionComplete: boolean = false;
  // _currencies: any = [];
  // exchangeFrom: number;
  // exchangeTo: number;
  // exchangeKeyFrom: string;

  // exchangeKeyTo: string;
  // exchangeKeyToIndex: any;
  // currencyTo: any;
  // noMoreRecords: boolean = false;
  // _indexToDel: number;

  constructor() {
    // this._UserNAme = window.localStorage.getItem('username');
  }

  ngOnInit() {
    $("#myBody").removeClass('loginClass');
    // $("#myBody").addClass('loginClass');
    // this.createEditUserForm(null, "");
    history.pushState(null, null, location.href)
    window.onpopstate = function (event) {
      history.go(1)
    }
    // this.createIssueTokenForm();
    // this.getUsers();
  }

  // getUsers() {
  //   let _url = ApiConstants.GET_USERS_URL;
  //   let _reqJson = "";
  //   ServiceCall.httpGetCall(_url, _reqJson, this.http).subscribe(
  //     (data) => {
  //       if (data.users.length > 0) {
  //         this.noRecord = false;
  //         this.usersList = data.users;
  //       } else {
  //         this.noRecord = true;
  //       }
  //     },
  //     (error) => {
  //       this.StatusMessage = 'Your request cannot be completed at the moment. Please contact administrator.';
  //       console.error(error);
  //     });
  // }

  // createEditUserForm(_user, Flag) {
  //   let _firstName = "";
  //   let _lastName = "";
  //   let _Email = "";
  //   let _Contact = "";
  //   let _CorrelationID = "";
  //   let _UserStatus = "";

  //   if (Flag == "Edit") {
  //     _firstName = _user.firstName;
  //     _lastName = _user.lastName;
  //     _CorrelationID = _user.userCorrelationId;
  //     _UserStatus = _user.status;// == "ACTIVE" ? "0" : _user.status == "SUSPENDED" ? "1" : _user.status == "TERMINATED" ? "2" : "";
  //   }

  //   this.editUserForm = new FormGroup({
  //     firstName: new FormControl({ value: _firstName, disabled: true }),
  //     lastName: new FormControl({ value: _lastName, disabled: true }),
  //     CorrelationID: new FormControl({ value: _CorrelationID, disabled: true }),
  //     UserStatus: new FormControl(_UserStatus)
  //   });
  // }


  enablePopover() {
    $('[data-toggle="tooltip"]').tooltip();
    $('[data-toggle="popover"]').popover();
  }

  // createIssueTokenForm() {
  //   this.tokenValueInvalid = false;
  //   this.userBalance = '00.00';
  //   this.IssueTokenForm = new FormGroup({
  //     selectToken: new FormControl('', [Validators.required]),
  //     tokenCount: new FormControl('', [Validators.required, Validators.pattern(/^\d+(\.\d+)?$/)])
  //   })
  // }

  // openUserViewReportModal(_user) {
  //   window.localStorage.setItem('selectedUserObj', JSON.stringify(_user))
  //   this.nextLedger = 0;
  //   this.userViewReport();
  //   this.noMoreRecords = false;
  // }

  // closeViewReportModal() {
  //   $('#Reporting').modal('hide')
  //   this._userRecords = [];
  // }

  // userViewReport() {
  //   if (this.scrollFunctionComplete == true || this.nextLedger == 1 && this.scrollFunctionComplete == false) {
  //     return;
  //   } else {
  //     this.scrollFunctionComplete = true;
  //     this._selectedUserObj = JSON.parse(window.localStorage.getItem('selectedUserObj'));
  //     this.loaderForLedger = true;
  //     this._noAssetDetails = false;
  //     this._noPayDetails = false;
  //     this.AccountHolderName = this._selectedUserObj.firstName + ' ' + this._selectedUserObj.lastName;
  //     let _userPubKey = this._selectedUserObj.accountPublicKey;
  //     let _url = ApiConstants.REQUEST_USER_LEDGER_URL + _userPubKey + '/' + this.nextLedger + '/' + this.itemsPerPageAllowed;
  //     ServiceCall.httpGetCall(_url, '', this.http).subscribe(
  //       (data) => {
  //         console.log(data)
  //         if (data.next == 'undefined' || data.next == undefined || data.next == '') {
  //         } else {
  //           this.nextLedger = data.next;
  //           console.log(this.nextLedger)
  //         }
  //         if (data.assets.length == 0) {
  //           this._noAssetDetails = true;
  //         }
  //         if (data.assets.length > 0) {
  //           this._userAssets = data.assets;
  //         }
  //         if (data.payment.length == 0 && this._userRecords.length == 0) {
  //           this._noPayDetails = true;
  //         }
  //         if (data.payment.length > 0) {
  //           for (let payment of data.payment) {
  //             this._userRecords.push(payment);
  //           }
  //           console.log(this._userRecords)
  //           if (data.payment.length < 10) {
  //             this.noMoreRecords = true;
  //             this.nextLedger = 1;
  //           }
  //         }
  //         this.loaderForLedger = false;
  //         this.scrollFunctionComplete = false;
  //       },
  //       (error: any) => {
  //         this.scrollFunctionComplete = false;
  //         console.log(error)
  //       }
  //     );
  //   }
  // }

  // getUserDetail(_data) {
  //   this.serverError = false;
  //   this.tempUserDetails = _data
  //   this.tokenList = _data.assetDetails
  // }

  // userAssetBalance() {
  //   this.userBalance = this.IssueTokenForm.controls.selectToken.value.assetQuantity;
  // }

  // issueToken() {
  //   this.serverError = false;
  //   if (this.IssueTokenForm.invalid) {
  //     for (let control in this.IssueTokenForm.controls) {
  //       this.IssueTokenForm.get(control).markAsTouched();
  //       this.IssueTokenForm.get(control).invalid;
  //       this.IssueTokenForm.get(control).updateValueAndValidity();
  //       console.error("form is not valid")
  //     }
  //     return
  //   } else if (this.tokenValueInvalid == true) {
  //     console.error("Token value is not valid")
  //     return
  //   } else {
  //     this.ShowLoading = false
  //     let _url = ApiConstants.LOAD_TOKENS_URL;
  //     let _reqJson = {
  //       "accountStatus": "true",
  //       "noOfTokens": this.IssueTokenForm.controls.tokenCount.value,
  //       "status": this.tempUserDetails.status,
  //       "tokenCorrelationId": this.IssueTokenForm.controls.selectToken.value,
  //       "userCorrelationId": this.tempUserDetails.userCorrelationId,
  //     }
  //     ServiceCall.httpPostCall(_reqJson, _url, this.http).subscribe(
  //       (data) => {
  //         console.log(data);
  //         this.ShowLoading = true
  //         if (data.status == 200) {
  //           this.tokenList = []
  //           this.createIssueTokenForm();
  //           $('#tokensIssue').modal('hide')
  //           this.usersList = data.clientUsersInfoResponse.users
  //         } else {
  //           this.ShowLoading = true
  //           this.serverError = true;
  //           this.serverErrMsg = "Your request cannot be completed at the moment. Please contact administrator.";
  //         }
  //       },
  //       (error) => {
  //         this.StatusMessage = 'Your request cannot be completed at the moment. Please contact administrator.';
  //         console.error(error);
  //       });
  //   }
  // }

  // validateTokenValue(e) {
  //   this.tokenValueInvalid = false;
  //   if (e.target.value.charAt(0) == '.') {
  //     this.tokenValueInput = "0.";
  //   } else {
  //     this.tokenValueInput = e.target.value;
  //   }

  //   $('input').on('input', function () {
  //     this.value = this.value
  //       .replace(/(\..*)\./, '$1')
  //       .replace(/(\.[\d]{7})./, '$1')

  //   });
  //   if (e.target.value == 0) {
  //     for (let control in this.IssueTokenForm.controls) {
  //       this.tokenValueInvalid = true;
  //     }
  //   }
  // }

  // SaveUserId(_userDetail, _index) {
  //   debugger;
  //   window.localStorage.setItem('userCorrelationId', _userDetail.userCorrelationId);
  //   this._indexToDel = _index;
  //   console.log(_userDetail + " -- " + _index);
  // }

  // SuspendUser(flag) {
  //   debugger;
  //   if (flag == 'Y') {

  //     $('#ConfirmationModal1').modal('hide');
  //     this.loader = true;
  //     this.loaderMain = true;
  //     let url = ApiConstants.UpdateUser + "/" + window.localStorage.getItem("userCorrelationId");
  //     let sType = "TERMINATED";

  //     let reqJson = {
  //       "users": [
  //         {
  //           "status": sType
  //         }
  //       ]
  //     };

  //     ServiceCall.httpPutCall(url, reqJson, this.http).subscribe(
  //       (data) => {
  //         console.log("<================ Registration Response ===============>")
  //         console.log(data);
  //         this.loader = false;
  //         this.loaderMain = false;

  //         if (data.code = "200") {
  //           alert("User has been terminated successfully.");
  //           console.log(this._indexToDel + " == == ");
  //           this.usersList.splice(this._indexToDel, 1);
  //         }
  //       },
  //       (error: any) => {
  //         this.loader = false;
  //         this.loaderMain = false;
  //         this.serverError = true;
  //         alert("Your request cannot be completed at the moment. Please contact administrator.error.error_description");
  //       }
  //     );

  //   }
  //   else {
  //     $('#ConfirmationModal1').modal('hide');
  //   }
  // }

  // fillEditValues(_users, _index) {
  //   debugger;
  //   console.log(_users);
  //   window.localStorage.setItem('userCorrelationId', _users.userCorrelationId);
  //   this._indexToDel = _index;
  //   console.log(_users + " -- " + _index);
  //   this.createEditUserForm(_users, "Edit");
  // }

  // modalClose() {
  //   $('#editUserModal').modal('hide');
  // }

  // SaveUser() {
  //   debugger;
  //   this.loader = true;
  //   this.loaderMain = true;
  //   let sType = this.editUserForm.controls['UserStatus'].value;
  //   let CorrelationID = this.editUserForm.controls['CorrelationID'].value;
  //   let url = ApiConstants.UpdateUser + "/" + CorrelationID;

  //   let reqJson = {
  //     "users": [
  //       {
  //         "status": sType
  //       }
  //     ]
  //   };

  //   ServiceCall.httpPutCall(url, reqJson, this.http).subscribe(
  //     (data) => {
  //       debugger;
  //       console.log("<================ Registration Response ===============>")
  //       console.log(data);
  //       this.loader = false;
  //       this.loaderMain = false;

  //       console.log(this._indexToDel);
  //       console.log(this.usersList[this._indexToDel]);

  //       this.usersList[this._indexToDel].status = sType;// == 0 ? "ACTIVE" : sType == 1 ? "SUSPENDED" : "TERMINATED";

  //       if (data.code = "200") {
  //         //alert("Successful");
  //         console.log(this._indexToDel + " == == ");
  //         $('#editUserModal').modal('hide');
  //       }
  //     },
  //     (error: any) => {
  //       this.loader = false;
  //       this.loaderMain = false;
  //       this.serverError = true;
  //       //this.serverErrMsg = "Your request cannot be completed at the moment. Please contact administrator.";
  //       // alert(error.error_description)
  //       alert("Your request cannot be completed at the moment. Please contact administrator.error.error_description");
  //     }
  //   );
  // }

  // get selectToken() { return this.IssueTokenForm.get('selectToken'); }
  // get tokenNo() { return this.IssueTokenForm.get('tokenCount'); }
}




// @Pipe({ name: 'safe' })
// export class SafePipe implements PipeTransform {
//   constructor(private sanitizer: DomSanitizer) { }
//   transform(url) {
//     return this.sanitizer.bypassSecurityTrustResourceUrl(url);
//   }


// }


