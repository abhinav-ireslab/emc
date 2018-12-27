import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiConstants } from '../../network/api-constants';
import { ServiceCall } from '../../network/web-service-call';
import { Http, Response, Headers } from '@angular/http';
import { AccountLedgerService } from '../user-account-ledger/user-account-ledger.service';
import { AlertComponent } from '../../alert/alert.component';
import { AlertNotification } from '../../alert-notification';

declare var $: any;
@Component({
  selector: 'app-create-token',
  templateUrl: './create-token.component.html',
  styleUrls: ['./create-token.component.css'],
})
export class CreateTokenComponent implements OnInit {
  currentMessage: string = "";
  createTokenForm: FormGroup;
  cID: any;
  _disabledSubmit: boolean = false;
  loader: boolean = false;
  loaderMain: boolean = false;
  loaderText: boolean = false;
  serverError: boolean = false;
  serverErrorToken: boolean = false;
  serverErrMsg: string = '';
  batchQuantityErr: boolean = false;
  _tokens: any = [];
  p: any;
  noRecord: boolean = false;
  itemsPerPageAllowed: number = 10;
  _totalElements: any;
  constructor(private fb: FormBuilder, private http: Http, private als: AccountLedgerService, private alert: AlertComponent) {
    this.cID = window.localStorage.getItem('cID');
  }

  ngOnInit() {
    this.tokenForm();
    this.getTokenlist();
  }

  tokenForm() {
    this.serverError = false;
    this.batchQuantityErr = false;
    this.createTokenForm = this.fb.group({
      clientCorrelationId: [this.cID, [Validators.required,]],
      batchQuantity: ['', [Validators.required, Validators.maxLength(10)]],
      tokenName: ['', [Validators.required, Validators.maxLength(20), Validators.minLength(3), Validators.pattern("[A-Za-z ]*$")]],
      tokenCode: ['', [Validators.required, Validators.pattern("[A-Za-z]{3}")]]

    });
  }

  checkBatchQuantity(e) {
    this.batchQuantityErr = false;
    if (e.target.value > 50000 || e.target.value == 0) {
      this.createTokenForm.get('batchQuantity').markAsTouched();
      this.createTokenForm.get('batchQuantity').invalid;
      this.createTokenForm.get('batchQuantity').updateValueAndValidity();
      this.batchQuantityErr = true;
    } else {
      this.batchQuantityErr = false;
    }
  }

  getTokenlist() {
    this.p = 1;
    this.getAllTokens("0");
  }

  createToken() {
    this.serverError = false;
    if (this.createTokenForm.invalid) {
      for (let control in this.createTokenForm.controls) {
        this.createTokenForm.get(control).markAsTouched();
        this.createTokenForm.get(control).invalid;
        this.createTokenForm.get(control).updateValueAndValidity();
        console.error("form is not valid")
      }
      return;
    } if (this.batchQuantityErr == true) {
      return;
    } else {
      this.loader = true;
      this.loaderMain = true;
      let _url = ApiConstants.CONFIG_CLIENT_TOKEN_URL;
      let _req = {
        "clientCorrelationId": this.createTokenForm.get('clientCorrelationId').value,
        "batchQuantity": this.createTokenForm.get('batchQuantity').value,
        "tokenName": this.createTokenForm.get('tokenName').value,
        "tokenCode": this.createTokenForm.get('tokenCode').value.toUpperCase(),
      }
      ServiceCall.httpPostCall(_req, _url, this.http).subscribe(
        (data) => {
          this.loader = false;
          this.loaderMain = false;
          if (data.code == 7001) {
            this.currentMessage = AlertNotification.tokenCrt;
            this.alert.showAlert();
            this.modalClose();
            this.getTokenlist();
          } else if (data.code == 7002) {
            this.serverErrorToken = true;
            this.serverErrMsg = 'Token code already exists ! Try new one';
          }
        },
        (error: any) => {
          this.loader = false;
          this.loaderMain = false;
          this.currentMessage = AlertNotification.serverErr;
          this.alert.showAlert();
          // console.error("error");
        }
      );
    }
  }

  modalClose() {
    this.tokenForm();
    $('#addTokenModal').modal('hide');
  }

  getAllTokens(pageCount) {
    this.loaderMain = true;
    this.loader = true;
    let _url = ApiConstants.GET_ALL_TOKENS + "?page=" + pageCount + "&size=" + this.itemsPerPageAllowed;
    console.log(_url)
    ServiceCall.httpGetCall(_url, '', this.http).subscribe(
      (data) => {
        console.log(data)
        if (data.assetTokenRequestsList == undefined) {
          this.noRecord = true;
        }
        else if (data.assetTokenRequestsList.length > 0) {
          this._tokens = data.assetTokenRequestsList;
          this._totalElements = data.totalElements;
        }
        else {
          this.noRecord = true;
        }
        this.loaderMain = false;
        this.loader = false;
      },
      (error: any) => {
        this.loader = false;
        this.loaderMain = false;
        this.currentMessage = AlertNotification.serverErr;
        this.alert.showAlert();
        // alert(error.error_description)
      }
    );
  }

  get clientCorrelationId() { return this.createTokenForm.get('clientCorrelationId'); }
  get batchQuantity() { return this.createTokenForm.get('batchQuantity'); }
  get tokenName() { return this.createTokenForm.get('tokenName'); }
  get tokenCode() { return this.createTokenForm.get('tokenCode'); }
}
