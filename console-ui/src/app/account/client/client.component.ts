import { Component, OnInit, } from "@angular/core";
import { FormControl, FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ErrorMessage } from "ng-bootstrap-form-validation";
import { ServiceCall } from "../../network/web-service-call";
import { ApiConstants } from "../../network/api-constants";
import { Router } from '@angular/router';
import { Http, Response, Headers } from '@angular/http';
import { AlertComponent } from '../../alert/alert.component';
import { AlertNotification } from '../../alert-notification';

declare var $:any;
@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {

  registrationForm: FormGroup;
  
  noRecord: boolean = false;
  loader: boolean = false;
  loaderMain: boolean = false;
  loaderText: boolean = false;
  serverError: boolean = false;
  serverErrMsg: string = '';
  emailErr: boolean = false;
  usernameErr: boolean = false;
  serverErrorGetAllClient: boolean = false;
  _clients: any = [];
  test: any = {};
  itemsPerPageAllowed: number = 10;
  _totalElements: number;
  _FormHeader: string;
  _modalSaveButton: string;
  p: any;
  currentMessage: string = "";

  _indexToDel: number;
  disableControl: Boolean;
  constructor(private fb: FormBuilder, private http: Http, private router: Router, private alert: AlertComponent) {
    this.getClientlist();
  }

  ngOnInit() {
    history.pushState(null, null, location.href);
    window.onpopstate = function (event) {
      history.go(1);
    }
    $("#myBody").removeClass('loginClass');
    // $("#myBody").addClass('loginClass');
    // this.currentMessage = "";
    this.createRegistrationForm(null, "Add");

  }

 


  createRegistrationForm(client, Flag) {
    // debugger;
    let _companyName = "";
    let _userName = "";
    let _Email = "";
    let _Contact = "";
    let _accountType = "";
    let _accountStatus = "";
    let _descreption = "";
    this._FormHeader = "Add Client";
    this._modalSaveButton = "Create Account";
    this.disableControl = false;
    this.usernameErr = false;
    if (Flag == "Edit") {
      // debugger;
      _companyName = client.company_name;
      _userName = client.username;
      _Email = client.email;
      _Contact = client.companyContact;
      _accountType = client.account_type;
      _accountStatus = client.status;
      _descreption = client.description;
      this._FormHeader = "Edit Client";
      this._modalSaveButton = "Save";
      this.disableControl = true;
    }
    this.registrationForm = new FormGroup({
      Email: new FormControl(_Email, [
        Validators.required,
        Validators.pattern(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/i)
      ]),
      userName: new FormControl(_userName, [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(20)
      ]),
      Contact: new FormControl(_Contact, [
        Validators.required,
        Validators.minLength(10),
        Validators.maxLength(10)
      ]),
      aType: new FormControl(_accountType, [
        Validators.required,
      ])
      ,
      cName: new FormControl(_companyName, [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50)
      ]),
      sType: new FormControl(_accountStatus, [
        Validators.required
      ]),
      desc: new FormControl(_descreption, [
        Validators.minLength(3),
        Validators.maxLength(200),
      ])
    });
    // if (this.disableControl == true) {
    //   // alert("disabled")
    //   // for (let control in this.registrationForm.controls) {
    //   //   this.registrationForm.get(control).disable();
    //   //   this.registrationForm.get(control).updateValueAndValidity();
    //   //   alert("true")
    //   // }
    //   this.registrationForm.controls['cName'].disable();
    //   this.registrationForm.controls['userName'].disable();
    //   this.registrationForm.controls['Email'].disable();
    //   this.registrationForm.controls['Contact'].disable();
    //   this.registrationForm.controls['desc'].disable();

    // } else {
    //   // alert("enabled")
    //   // for (let control in this.registrationForm.controls) {
    //   //   this.registrationForm.get(control).enable();
    //   // }
    //   this.registrationForm.controls['cName'].enable();
    //   this.registrationForm.controls['userName'].enable();
    //   this.registrationForm.controls['Email'].enable();
    //   this.registrationForm.controls['Contact'].enable();
    //   this.registrationForm.controls['desc'].enable();
    // }

  }

  registerUser() {
    if (this._FormHeader == "Add Client") {
      this.emailErr = false;
      this.usernameErr = false;
      this.serverError = false;
      this.serverErrMsg = "";
      if (this.registrationForm.invalid) {
        for (let control in this.registrationForm.controls) {
          this.registrationForm.get(control).markAsTouched();
          this.registrationForm.get(control).invalid;
          this.registrationForm.get(control).updateValueAndValidity();
          // console.error("form is not valid")
        }
      } else {
        this.loader = true;
        this.loaderMain = true;
        let url = ApiConstants.CREATE_ACCOUNT_URL;
        let companyName = this.registrationForm.controls['cName'].value;
        let userName = this.registrationForm.controls['userName'].value;
        let email = this.registrationForm.controls['Email'].value;
        let contact = this.registrationForm.controls['Contact'].value;
        let aType = this.registrationForm.controls['aType'].value;
        let sType = this.registrationForm.controls['sType'].value;
        let desc = this.registrationForm.controls['desc'].value;

        var today = Date.now();

        let reqJson = JSON.stringify({
          "email": email,
          "company_name": companyName,
          "contactNumber": contact,
          "username": userName,
          "account_type": aType,
          "password": "null",
          "createdBy": "null",
          "createdDate": today,
          "modifiedBy": "null",
          "modifiedDate": today,
          "status": sType,
          "description": desc
        });
        ServiceCall.httpPostCall(reqJson, url, this.http).subscribe(
          (data) => {
            if (data.code == "9000" || data.success == "true") {
              this.modalClose();
              this.getClientlist();
            }
            else if (data.code == "9001") {
              this.loader = false;
              this.loaderMain = false;
              this.emailErr = true;
            }
            else if (data.code == "9002") {
              this.loader = false;
              this.loaderMain = false;
              this.usernameErr = true;
            }
            else {
              if (data.errors != null || data.errors != undefined || data.errors != '') {
                this.loader = false;
                this.loaderMain = false;
                this.currentMessage = AlertNotification.stellerErr;
                this.alert.showAlert();
              } else {
                this.currentMessage = AlertNotification.serverErr;
                this.alert.showAlert();
              }
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
    else if (this._FormHeader == "Edit Client") {
      this.loader = true;
      this.loaderMain = true;

      let sType = this.registrationForm.controls['sType'].value;
      let url = ApiConstants.SuspendUser + "/" + window.localStorage.getItem('clientCorrelationId');

      let reqJson = {
        "clientProfile": [
          {
            "clientStatus": sType
          }
        ]
      };
      ServiceCall.httpPutCall(url, reqJson, this.http).subscribe(
        (data) => {
          this.loader = false;
          this.loaderMain = false;

          this._clients[this._indexToDel].status = sType;//== 0 ? "ACTIVE" : sType == 1 ? "SUSPENDED" : "TERMINATED";
          if (data.code = "200") {
            $('#addClientModal').modal('hide');
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

  SaveUserId(_userDetail, _index) {
    window.localStorage.setItem('clientCorrelationId', _userDetail.clientCorrelationId);
    this._indexToDel = _index;
  }


  TerminateUser(flag) {
    if (flag == 'Y') {
      this.loader = true;
      this.loaderMain = true;
      let url = ApiConstants.SuspendUser + "/" + window.localStorage.getItem("clientCorrelationId");

      let sType = "TERMINATED";

      let reqJson = {
        "clientProfile": [
          {
            "clientStatus": sType
          }
        ]
      };
      ServiceCall.httpPutCall(url, reqJson, this.http).subscribe(
        (data) => {
          this.loader = false;
          this.loaderMain = false;

          if (data.code = "200") {
            this.currentMessage = AlertNotification.terminateCli;
            this.alert.showAlert();
            this._totalElements -= 1;
            this._clients.splice(this._indexToDel, 1);
          }
        },
        (error: any) => {
          this.loader = false;
          this.loaderMain = false;
          this.currentMessage = AlertNotification.serverErr;
          this.alert.showAlert();
        }
      );

      $('#ConfirmationModal').modal('hide');
    }
    else {
      $('#ConfirmationModal').modal('hide');
    }
  }

  modalClose() {
    this.serverError = false;
    $('#addClientModal').modal('hide');
    this.createRegistrationForm(null, "Add");
  }

  fillEditValues(client, _index) {
    window.localStorage.setItem('clientCorrelationId', client.clientCorrelationId);
    this._indexToDel = _index;
    this.createRegistrationForm(client, "Edit");
  }
  getClientlist() {
    this.p = 1;
    this.getAllClients("0");
  }

  getAllClients(pageCount) {
    this.loader = true;
    this.loaderMain = true;
    let _url = ApiConstants.GET_ALL_CLIENTS + "/dummyCid?page=" + pageCount + "&size=" + this.itemsPerPageAllowed;
    ServiceCall.httpGetCall(_url, '', this.http).subscribe(
      (data) => {
        this._clients = data.pageList;
        this._totalElements = data.totalElements;
        this.loaderMain = false;
        this.loader = false;
      },
      (error: any) => {
        this.loader = false;
        this.loaderMain = false;
        this.currentMessage = AlertNotification.serverErr;
        this.alert.showAlert();
      }
    );
  }

  get cName() { return this.registrationForm.get('cName'); }
  get userName() { return this.registrationForm.get('userName'); }
  get Email() { return this.registrationForm.get('Email'); }
  get Contact() { return this.registrationForm.get('Contact'); }
  get desc() { return this.registrationForm.get('desc'); }
  get aType() { return this.registrationForm.get('aType'); }
  get sType() { return this.registrationForm.get('sType'); }

  
}
