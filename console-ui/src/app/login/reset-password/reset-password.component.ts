import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Http, Response, Headers } from '@angular/http';
import { ActivatedRoute, Params } from '@angular/router';
import { ApiConstants } from '../../network/api-constants';
import { ServiceCall } from '../../network/web-service-call';
import { AlertComponent } from '../../alert/alert.component';
import { AlertNotification } from '../../alert-notification';


declare var $: any;
@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  resetPasswordForm: FormGroup
  isSerErrorMessage: boolean = false;
  passwordIsValid: boolean = false;
  passwordMissMatch: boolean = false;
  loader: boolean = false;
  loaderMain: boolean = false;
  loaderText: boolean = false;
  _resetPasswordToken: string = "";
  serErrorMessage: string = "";
  currentMessage: string = "";

  constructor(private fb: FormBuilder, private http: Http, private actRoute: ActivatedRoute,private alert: AlertComponent) {
    this._resetPasswordToken = this.actRoute.snapshot.queryParams.resetPasswordToken
  }

  ngOnInit() {
    $("#myBody").removeClass('loginClass');
    $("#myBody").addClass('loginClass');
    history.pushState(null, null, location.href);
    window.onpopstate = function (event) {
      history.go(1);
    }

    this.resetPasswordForm = this.fb.group({
      password: ['', [Validators.required, Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d.*)(?=.*\W.*)[a-zA-Z0-9\S]{6,15}$/)]],
      confirmPassword: ['', Validators.required],
    });
    $('[data-toggle="popover"]').popover();
  }

  passwordMatch() {
    this.passwordMissMatch = false;
    if (this.resetPasswordForm.controls['password'].value != this.resetPasswordForm.controls['confirmPassword'].value) {
      this.passwordMissMatch = true;
    } else {
      this.passwordMissMatch = false;
    }
  }

  resetPassword() {
    this.isSerErrorMessage = false;
    this.passwordMatch();
    if (this.resetPasswordForm.invalid) {
      for (let control in this.resetPasswordForm.controls) {
        this.resetPasswordForm.get(control).markAsTouched();
        this.resetPasswordForm.get(control).invalid;
        this.resetPasswordForm.get(control).updateValueAndValidity();
      }
      // console.error("form is not valid")
      return;
    }
    if (this.passwordMissMatch == true) {
      return;
    }
    else {
      this.loader = true;
      this.loaderMain = true;
      let url = ApiConstants.RESET_PASSWORD_URL;
      let reqJson = JSON.stringify(

        {
          "password": this.resetPasswordForm.controls['password'].value,
          "token": this._resetPasswordToken,
        }

      );
      ServiceCall.httpPostCall(reqJson, url, this.http).subscribe(
        (data) => {
          if (data.passwordResetcode == 5002) {
            this.loader = false;
            this.loaderText = true
          } else {
            this.loader = false;
            this.loaderText = true;
            this.isSerErrorMessage = true;
            this.serErrorMessage = AlertNotification.usedResetLink;
          }
        },

        (error: any) => {
          this.loaderMain = false;
          this.loader = false;
          this.currentMessage = AlertNotification.serverErr;
          this.alert.showAlert();
          // this.isSerErrorMessage = true;
          //   this.serErrorMessage = "Your request cannot be completed at the moment. Please contact administrator.";
          // console.log("error");
        }
      );
    }


  }
  get password() { return this.resetPasswordForm.get('password'); }
  get confirmPassword() { return this.resetPasswordForm.get('confirmPassword'); }
}
