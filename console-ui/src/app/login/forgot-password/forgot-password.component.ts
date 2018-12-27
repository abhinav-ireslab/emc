import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiConstants } from '../../network/api-constants';
import { ServiceCall } from '../../network/web-service-call';
import { Http, Response, Headers } from '@angular/http';
import { AlertComponent } from '../../alert/alert.component';
import { AlertNotification } from '../../alert-notification';

declare var $:any;
@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
  currentMessage: string = "";
  forgotPasswordForm: FormGroup;
  isError: boolean = false;
  loader: boolean = false;
  loaderMain: boolean = false;
  loaderText: boolean = false;
  emailErr: string = '';
  isSerErrorMessage: boolean = false;
  serErrorMessage: string = '';

  constructor(private fb: FormBuilder, private http: Http, private alert: AlertComponent) { }

  ngOnInit() {
    $("#myBody").removeClass('loginClass');
    $("#myBody").addClass('loginClass');
    window.onpopstate = function (event) {
      history.go(2);
    };
    this.forgotPasswordForm = this.fb.group({
      email: ['', [Validators.required, Validators.pattern(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/i)]],
    });
  }

  forgotPassword() {
    debugger;
    this.isSerErrorMessage = false;
    if (this.forgotPasswordForm.invalid) {
      this.forgotPasswordForm.get('email').markAsTouched();
      this.forgotPasswordForm.get('email').invalid;
      this.forgotPasswordForm.get('email').updateValueAndValidity();
      return;
    } else {
      this.loader = true;
      this.loaderMain = true;
      let _email = this.forgotPasswordForm.controls['email'].value;
      let _url = ApiConstants.FORGOT_URL + _email;
      let _reqJson = "";
      ServiceCall.httpGetCall(_url, _reqJson, this.http).subscribe(
        (data) => {
          // console.log(data)
          if (data.passwordResetcode == 5002) {
            this.loader = false;
            this.loaderText = true;
          } else {
            this.loaderMain = false;
            this.loaderText = false;
            this.isSerErrorMessage = true;
            this.serErrorMessage = data.errors[0].description;
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
  get email() { return this.forgotPasswordForm.get('email'); }
}
