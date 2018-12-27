import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
// import { AdminLoginService } from './admin-login.service';
import { AdminLoginModel } from './admin-login-model';
import { ServiceCall } from '../../network/web-service-call';
import { ApiConstants } from '../../network/api-constants';
import { Http, Response, Headers } from '@angular/http';

declare var $:any
@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  adminLoginForm: FormGroup;
  statusCode: any;
  //statusCode: any;
  requestProcessing = false;
  userToSignup = null;
  processValidation = false;
  public token: string = null;
  loaderMain: boolean = false;
  loader: boolean = false;
  serverError: boolean = false;
  serverErrMsg: string = '';

  constructor(private fb: FormBuilder, private http: Http, private router: Router) { }
  ngOnInit() {
    $("#myBody").removeClass('loginClass');
    $("#myBody").addClass('loginClass');
    this.adminLoginForm = new FormGroup({
      'username': new FormControl('', [Validators.required]),
      'password': new FormControl('', [Validators.required])
    });
  }

  adminLogin() {
   
    if (this.adminLoginForm.invalid) {
      for (let control in this.adminLoginForm.controls) {
        this.adminLoginForm.get(control).markAsTouched();
        this.adminLoginForm.get(control).invalid;
        this.adminLoginForm.get(control).updateValueAndValidity();
        // console.error("form is not valid");
    
      }
    } else {
      this.validateCredential();
    }
  }

  validateCredential() {
    this.loader = true;
    this.loaderMain = true;
    if (this.adminLoginForm.get('username').value === 'admin' && this.adminLoginForm.get('password').value === 'admin@123') {
      this.router.navigateByUrl('admin_account');
      this.loader = false;
      this.loaderMain = false;
    } else {
      this.loader = false;
      this.loaderMain = false;
      this.serverError = true;
      this.serverErrMsg = "Invalid credentials ! login failed";
    }
  }

  get username() { return this.adminLoginForm.get('username'); }
  get password() { return this.adminLoginForm.get('password'); }
}

