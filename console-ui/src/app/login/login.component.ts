import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Http, Response, Headers } from '@angular/http';
// import { UserService } from './login.services';
// import { Login } from './Login';
import { ApiConstants } from '../network/api-constants';
import { ServiceCall } from '../network/web-service-call';
import { AlertComponent } from '../alert/alert.component';
import { AlertNotification } from '../alert-notification';

declare var $:any;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  statusCode: any;
  requestProcessing = false;
  userToSignup = null;
  processValidation = false;
  public token: string = null;
  loader: boolean = false;
  loaderMain: boolean = false;
  loaderText: boolean = false;
  serverError: boolean = false;
  serverErrMsg: string = '';
  serverResErr: boolean = false;
  serverResErrMsg: string = '';
  credentialIsValid: boolean = false;
  currentMessage: string = "";

  constructor(private router: Router, private http: Http, private alert: AlertComponent) {

    ApiConstants.CO_ID = null;
    ApiConstants.ledgerAccount = null;
  }

  ngOnInit() {
    $("#myBody").removeClass('loginClass');
    $("#myBody").addClass('loginClass');
    history.pushState(null, null, location.href);
    window.onpopstate = function (event) {
      history.go(1);
    }

    this.loginForm = new FormGroup({
      'username': new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
      'password': new FormControl('', [Validators.required]),
    });
  }

  loginRequest() {
    this.credentialIsValid = false;
    this.serverError = false;

    if (this.loginForm.invalid) {
      for (let control in this.loginForm.controls) {
        this.loginForm.get(control).markAsTouched();
        this.loginForm.get(control).invalid;
        this.loginForm.get(control).updateValueAndValidity();
        // console.error("form is not valid")
      }
    } else {
      this.loader = true;
      this.loaderMain = true;
      let reqJson = JSON.stringify({
        "users":
          {
            "userName": this.loginForm.controls['username'].value,
            "password": this.loginForm.controls['password'].value
          }
      }
      );
      let url = ApiConstants.CLIENT_LOGIN_URL;
      // console.log(url)
      ServiceCall.httpPostCall(reqJson, url, this.http).subscribe(
        (data) => {
          localStorage.clear();
          // console.log("<=========== login response ===============>");
          // console.log(data);
          this.loader = false;
          this.loaderMain = false;
          if (data.code === 2003) {
            window.localStorage.setItem("cID", data.users[0].clientCorrelationId);
            this.getUserAccount('/dashboard/home')
          }
          else if (data.code === 2002) {
            window.localStorage.setItem("cID", data.users[0].clientCorrelationId);
            this.getUserAccount('/validating_user')
          }
          else if (data.code === 2001) {
            this.serverError = true;
            this.credentialIsValid = true;
            this.serverErrMsg = data.message;
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
  }

  getUserAccount(redirectTO) {
    let url = ApiConstants.GET_ACCOUNT_URL;
    ServiceCall.httpGetCall(url, "", this.http).subscribe(
      (data) => {
        this.loader = false;
        this.loaderMain = false;
        window.localStorage.setItem("userDetails", JSON.stringify(data));
        window.localStorage.setItem("baseTXN", data.baseTxnAccountPublicKey);
        window.localStorage.setItem("cKey", data.clientApiKey);
        window.localStorage.setItem("sKey", data.clientApiSecret);
        window.localStorage.setItem("username", data.username);
        this.router.navigateByUrl(redirectTO);
      },
      (error: any) => {
        this.loader = false;
        this.loaderMain = false;
        this.currentMessage = AlertNotification.serverErr;
        this.alert.showAlert();
      }
    );
  }

  get username() { return this.loginForm.get('username'); }
  get password() { return this.loginForm.get('password'); }

}
