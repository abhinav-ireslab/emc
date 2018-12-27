import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiConstants } from '../../network/api-constants';
import { ServiceCall } from '../../network/web-service-call';
import { Http, Response, Headers } from '@angular/http';
import { AlertComponent } from '../../alert/alert.component';
import { AlertNotification } from '../../alert-notification';

declare var $:any;
@Component({
  selector: 'app-generate-otp',
  templateUrl: './generate-otp.component.html',
  styleUrls: ['./generate-otp.component.css']
})
export class GenerateOtpComponent implements OnInit {
  loader: boolean = false;
  loaderMain: boolean = false;
  otpValidationForm: FormGroup;
  _userName: any;
  isSerErrorMessage: boolean = false;
  serErrorMessage: string = ""
  currentMessage: string = "";

  constructor(private fb: FormBuilder, private router: Router, private http: Http,private alert: AlertComponent) {
    let data = JSON.parse(window.localStorage.getItem('userDetails'));
    this._userName = data.username;
  }

  ngOnInit() {
    $("#myBody").removeClass('loginClass');
    $("#myBody").addClass('loginClass');
    window.onpopstate = function (event) {
      history.go(2);
    }

    this.otpValidationForm = this.fb.group({
      otp: ['',],
    });
  }
  otpCheck() {
    if (this.otpValidationForm.controls['otp'].value === null || this.otpValidationForm.controls['otp'].value === '' || this.otpValidationForm.controls['otp'].value === undefined) {
      this.isSerErrorMessage = true;
      this.serErrorMessage = "OTP is required"
    } else if (this.otpValidationForm.controls['otp'].value.length < "6") {
      this.isSerErrorMessage = true;
      this.serErrorMessage = "Please provide valid otp"
    } else {
      this.isSerErrorMessage = false;
      this.serErrorMessage = ""
    }
  }

  userOtpValidation() {
    if (this.isSerErrorMessage == true) {
      return;
    } else {
      if (this._userName == null || this._userName == '' || this._userName == undefined) {
        this.router.navigateByUrl('login');
      } else {
        this.loader = true;
        this.loaderMain = true;
        let url = ApiConstants.LOGIN_OTP_URL;
        let otp = this.otpValidationForm.controls['otp'].value;
        let Authorization = "Authorization";
        let reqJson = JSON.stringify({
          "users":
            {
              "userName": this._userName,
              "password": "",
              "otpCode": otp,
            }
        }
        );
        ServiceCall.httpAuthPostCall(url, reqJson, otp, Authorization, this.http).subscribe(
          (data) => {
            if (data.code == 2004) {
              window.localStorage.setItem("cID", data.users[0].clientCorrelationId);
              this.loader = false;
              this.loaderMain = false;
              this.router.navigateByUrl('/dashboard/home');
            } else if (data.code == 2005) {
              this.isSerErrorMessage = true;
              this.serErrorMessage = AlertNotification.wrongOtp;
              this.loader = false;
              this.loaderMain = false;
            }
          },
          (error: any) => {
            this.loader = false;
            this.loaderMain = false;
            this.currentMessage = AlertNotification.serverErr;
            this.alert.showAlert();
            // this.serErrorMessage = "Your request cannot be completed at the moment. Please contact administrator";
            // this.isSerErrorMessage = true;
            // console.log("error");
          }
        );
      }

    }

  }


}
