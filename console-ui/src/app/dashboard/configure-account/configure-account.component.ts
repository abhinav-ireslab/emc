import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators } from "@angular/forms";
// import { ErrorMessage } from "ng-bootstrap-form-validation";
// import { passwordConfirming } from '../utils/custom-errors';
import { ServiceCall } from '../../network/web-service-call';
import { ApiConstants } from '../../network/api-constants';
import { Router } from '@angular/router';
import { Http, Response, Headers } from '@angular/http';
import { AlertComponent } from '../../alert/alert.component';
import { AlertNotification } from '../../alert-notification';

declare var QRCode: any;
@Component({
  selector: 'app-configure-account',
  templateUrl: './configure-account.component.html',
  styleUrls: ['./configure-account.component.css']
})
export class ConfigureAccountComponent implements OnInit {
  updateForm: FormGroup;
  //resJson: any;
  _userName: any;
  _cName: string = "";
  _cAddress: string = "";
  _country: string = "";
  _state: string = "";
  _city: string = "";
  _pinCode: string = "";
  _cContact: string = "";
  _cFax: string = "";
  _email: string = "";
  _contact: string = "";
  _correlationId: string = "";
  _aType: string = "";
  _desc: string = "";
  _qrpath: any;
  _securityCode: any = "";
  _is2faEnabled: boolean = false;
  _twoStepVerification = false;
  _token: any;
  loader: boolean = false;
  loaderMain: boolean = false;
  loaderText: boolean = false;
  serverError: boolean = false;
  serverErrMsg: string = '';
  currentMessage: string = "";

  constructor(private http: Http, private router: Router, private alert: AlertComponent) {
    this._userName = window.localStorage.getItem('username');
  }

  ngOnInit() {
    this.updateData();
  }
  updateData() {
    let _userDetails = JSON.parse(window.localStorage.getItem('userDetails'));
    let data = _userDetails
    this._cName = data.company_name;
    this._email = data.email;
    this._contact = data.contactNumber;
    this._correlationId = data.clientCorrelationId;
    this._aType = data.account_type == 0 ? "Test Net" : "";
    this._desc = data.description;
    this._is2faEnabled = data.is2faEnabled;
    this._token = "_token";
    this._cAddress = data.companyAddress;
    this._country = data.companyCountry;
    this._state = data.companyState;
    this._city = data.companyCity;
    this._pinCode = data.companyPinCode;
    this._cContact = data.companyContact;
    this._cFax = data.companyFax;
    this.createForm();
  }

  createForm() {
    this.updateForm = new FormGroup({
      cName: new FormControl({ value: this._cName, disabled: true }, [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50),
      ]),
      cAddress: new FormControl({ value: this._cAddress, disabled: false }, [
        Validators.required
      ]),
      cCountry: new FormControl({ value: this._country, disabled: false }, [
        Validators.required
      ]),
      cState: new FormControl({ value: this._state, disabled: false }, [
        Validators.required
      ]),
      cCity: new FormControl({ value: this._city, disabled: false }, [
        Validators.required
      ]),
      cPinCode: new FormControl({ value: this._pinCode, disabled: false }, [
        Validators.required
      ]),
      cContact: new FormControl({ value: this._cContact, disabled: false }, [
        Validators.required
      ]),
      cFax: new FormControl({ value: this._cFax, disabled: false }, [
        Validators.required
      ]),
      email: new FormControl({ value: this._email, disabled: true }, [
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/)
      ]),
      contact: new FormControl({ value: this._contact, disabled: true }, [
        Validators.required,
        Validators.minLength(10),
        Validators.maxLength(10)
      ]),
      correlationId: new FormControl({ value: this._correlationId, disabled: true }, [
        Validators.required,
        Validators.minLength(7),
        Validators.maxLength(100),
      ]),
      aType: new FormControl({ value: this._aType, disabled: true }, []),
      desc: new FormControl({ value: this._desc, disabled: false }, [
        Validators.minLength(7),
        Validators.maxLength(200),
      ])
    });


  }

  requestTwoStepAuth(_eventValue) {
    if (_eventValue.target.checked) {
      this._is2faEnabled = true;
      this._twoStepVerification = true;
      this.twoFaEnable("ACTIVATE");
    } else {
      this._is2faEnabled = false;
      this._twoStepVerification = false;
      this.twoFaEnable("DISABLE");
    }
  }
  twoFaEnable(_data) {
    this.loader = true;
    this.loaderMain = true;
    if (_data == "ENABLE") {
      this._twoStepVerification = false;
    }
    let url = ApiConstants.REQUEST2FA_URL;
    let reqJson = JSON.stringify({
      "users":
        {
          "userName": this._userName,
          "enable2fa": _data,
          "securityCode": this._securityCode,
        }
    });
    ServiceCall.httpPostCall(reqJson, url, this.http).subscribe(
      (data) => {
        this._qrpath = data.users[0].googleAuthenticatorBarCode;
        window.localStorage.setItem('securityCode', data.users[0].securityCode);
        this._securityCode = window.localStorage.getItem('securityCode');
        this.getQRcode();
        this.loader = false;
        this.loaderMain = false;
        if(_data == "ENABLE"){
          this.currentMessage = AlertNotification.twoFaEnabled;
          this.alert.showAlert();
        }else if(_data == "DISABLE"){
          this.currentMessage = AlertNotification.twoFaDisabled;
          this.alert.showAlert();
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



  onUpdate() {
    this.loader = true;
    this.loaderMain = true;

    let reqJson = {
      "company_name": this.updateForm.controls['cName'].value,
      "companyAddress": this.updateForm.controls['cAddress'].value,
      "companyCountry": this.updateForm.controls['cCountry'].value,
      "companyCity": this.updateForm.controls['cCity'].value,
      "companyState": this.updateForm.controls['cState'].value,
      "companyPinCode": this.updateForm.controls['cPinCode'].value,
      "companyContact": this.updateForm.controls['cContact'].value,
      "companyFax": this.updateForm.controls['cFax'].value,
      "email": this.updateForm.controls['email'].value,
      "contactNumber": this.updateForm.controls['contact'].value,
      "clientCorrelationId": this.updateForm.controls['correlationId'].value,
      "account_type": this.updateForm.controls['aType'].value,
      "description": this.updateForm.controls['desc'].value,
      "is2faEnabled": this._is2faEnabled,
    };
    let url = ApiConstants.UPDATE_ACCOUNT_URL;
    // console.log(reqJson)
    ServiceCall.httpPutCall(url, reqJson, this.http).subscribe(
      (data) => {
        // console.log(data)
        this.loader = false;
        this.loaderMain = false;
        if (data.code == 7000) {
          let updatedData = data.accountDetails[0];
          window.localStorage.setItem("userDetails", JSON.stringify(updatedData));
          this.updateData();
          this.currentMessage = AlertNotification.accountSuc;
          this.alert.showAlert();
        }
        else {
          this.currentMessage = AlertNotification.serverErr;
          this.alert.showAlert();
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

  // onReset() {
  //   this.updateForm.reset();
  // }

  getQRcode() {
    var myNode = document.getElementById("qr2fa");
    myNode.innerHTML = "";
    var _qr2fa = new QRCode(document.getElementById("qr2fa"), {
      text: this._qrpath,
      colorDark: "#000000",
      colorLight: "#ffffff",
      width: 170,
      height: 170,
      correctLevel: QRCode.CorrectLevel.H,
    });
  }

}
