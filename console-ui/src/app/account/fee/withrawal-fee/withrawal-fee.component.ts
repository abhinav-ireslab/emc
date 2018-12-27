import { Component, OnInit } from '@angular/core';
import { ApiConstants } from '../../../network/api-constants';
import { ServiceCall } from '../../../network/web-service-call';
import { Http } from '@angular/http';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { AlertNotification } from '../../../alert-notification';
import { AlertComponent } from '../../../alert/alert.component';

declare var $: any
@Component({
  selector: 'app-withrawal-fee',
  templateUrl: './withrawal-fee.component.html',
  styleUrls: ['./withrawal-fee.component.css']
})
export class WithrawalFeeComponent implements OnInit {
  currentMessage: string;
  selectedCurrency: string = "SGD";
  exchangeForm: FormGroup;
  updateForm: FormGroup;
  updateId;
  _currentFee;
  convertRate;
  convertCrypto;
  exchangeRate: any;
  exchangeFee: any;
  exchangeToInputValue: any
  exchangeFromInputValue: any
  _currencies: any = [];
  _currencyList: any = [];
  currencyFromInvalid: boolean = false;
  currencyToInvalid: boolean = false;
  selectError: boolean = false;

  loader: boolean = false;
  loaderMain: boolean = false;

  AlertNotification = AlertNotification

  constructor(private http: Http, private fb: FormBuilder, private alert: AlertComponent) { }

  ngOnInit() {
    this.createExchangeForm();
    this.createUpdateForm();
    this.getExchangeRate();
  }
  createExchangeForm() {
    this.exchangeFee = "x.00";
    this.exchangeForm = this.fb.group({
      currencyFromInput: ['', Validators.pattern(/^\d*\.*?\d*\.*?$/)],
      currencyTo: ['', [Validators.required]],
      currencyFrom: ['', [Validators.required]],
      currencyToInput: ['', Validators.pattern(/^\d*\.*?\d*\.*?$/)],
    });
  }
  createUpdateForm() {
    this._currentFee = "";
    this.updateForm = this.fb.group({
      convertFrom: ['', [Validators.required]],
      convertTo: ['', [Validators.required]],
      convertFee: [this._currentFee, Validators.pattern(/^\d*\.*?\d*\.*?$/)],
    });
  }

  getExchangeRate() {
    this.loader = true;
    this.loaderMain = true;
    let _url = ApiConstants.GET_EXCHANGE_RATE
    let _reqJson = ""
    ServiceCall.httpGetCall(_url, _reqJson, this.http).subscribe(
      (data) => {
        // console.log(data)
        this.createExchangeForm();
        this._currencyList = data.currencyList;
        this._currencies = data.exchangeList;
        this.loader = false;
        this.loaderMain = false;
      },
      (error: any) => {
        this.loader = false;
        this.loaderMain = false;
        this.currentMessage = AlertNotification.serverErr;
        this.alert.showAlert();
      }
    );
  }



  validateFromValue(e) {
    if (this.exchangeRate == undefined) {
      this.selectError = true;
      return;
    } else {
      this.selectError = false;
      this.currencyFromInvalid = false;
      if (e.target.value.charAt(0) == '.') {
        this.exchangeFromInputValue = "0.";
      } else {
        e.target.value = e.target.value
          .replace(/(\..*)\./, '$1')
          .replace(/(\.[\d]{2})./, '$1')
        this.exchangeFromInputValue = e.target.value;
        this.exchangeFromInputValue()
      }
    }
  }

  validateToValue(e) {
    if (this.exchangeRate == undefined) {
      this.selectError = true;
      return;
    } else {
      this.selectError = false;
      if (e.target.value.charAt(0) == '.') {
        this.exchangeToInputValue = "0.";
      } else {
        e.target.value = e.target.value
          .replace(/(\..*)\./, '$1')
          .replace(/(\.[\d]{2})./, '$1')
        this.exchangeToInputValue = e.target.value;
        this.calculatEexchangeTo()
      }
    }
  }

  validateFee(e) {
    if (e.target.value.charAt(0) == '.') {
      this._currentFee = "0.";
    } else if (e.target.value == "") {

    } else {
      e.target.value = e.target.value
        .replace(/(\..*)\./, '$1')
        .replace(/(\.[\d]{2})./, '$1')
      this._currentFee = e.target.value;
    }
  }

  calculatEexchangeFrom() {
    this.exchangeToInputValue = (this.exchangeRate * this.exchangeFromInputValue).toFixed(2);
    this.exchangeFromInputValue = this.exchangeForm.get('currencyFromInput').value;
    // this.finalRate = (this.exchangeFee * this.exchangeFromInputValue).toFixed(2)
  }

  calculatEexchangeTo() {
    this.exchangeFromInputValue = (1 / this.exchangeRate * this.exchangeToInputValue).toFixed(2)
    // this.finalRate = (this.exchangeFee * this.exchangeFromInputValue).toFixed(2)
  }


  exchangeCurrency() {
    // debugger
    this.selectedCurrency = this.exchangeForm.get('currencyFrom').value;
    if (this.exchangeForm.get('currencyFrom').value != "" && this.exchangeForm.get('currencyTo').value != "") {
      // this.selectedCurrency = this.exchangeForm.get('currencyFrom').value;
      this.exchangeFee = "x.00"
      this.exchangeRate = "";
      this.exchangeForm.controls['currencyFromInput'].setValue('x.00')
      this.exchangeForm.controls['currencyToInput'].setValue('x.00')
      this.exchangeForm.controls['currencyToInput'].setErrors({ pattern: false });
      this.exchangeForm.controls['currencyFromInput'].setErrors({ pattern: false });
      for (let cur of this._currencies) {
        if (this.exchangeForm.get('currencyFrom').value == cur.exchangeToken && this.exchangeForm.get('currencyTo').value == cur.nativeCurrency) {
          this.selectError = false;
          this.exchangeRate = cur.exchangeRate
          this.exchangeFee = parseFloat(cur.transferFee).toFixed(2)
          this.exchangeForm.controls['currencyFromInput'].setValue('1')
          this.exchangeForm.controls['currencyToInput'].setValue(parseFloat(this.exchangeRate).toFixed(2))
          break;
        }
      }
    }
  }

  exCurrency() {
    if (this.updateForm.get('convertFrom').value != "" && this.updateForm.get('convertTo').value != "") {
      for (let cur of this._currencies) {
        if (this.updateForm.get('convertFrom').value == cur.exchangeToken && this.updateForm.get('convertTo').value == cur.nativeCurrency) {
          this.updateId = cur.exchangeId
          this.convertRate = cur.exchangeRate
          this.convertCrypto = cur.isCryptocurrency
          this._currentFee = parseFloat(cur.transferFee).toFixed(2)
        }
      }
    }
  }

  updateFee() {
    if (this.updateForm.invalid) {
      for (let control in this.updateForm.controls) {
        this.updateForm.get(control).markAsTouched();
        this.updateForm.get(control).invalid;
        this.updateForm.get(control).updateValueAndValidity();
      }
      return;
    } else {
      $('#updateFee').modal('hide');
      this.currentMessage = AlertNotification.cSoon;
      this.alert.showAlert();
      // this.loader = true;
      // this.loaderMain = true;
      // $('#updateFee').modal('hide');
      // let _reqJson = {
      //   "exchangeId": this.updateId,
      //   "exchangeToken": this.updateForm.get('convertFrom').value,
      //   "nativeCurrency": this.updateForm.get('convertTo').value,
      //   "exchangeRate": this.convertRate,
      //   "transferFee": this._currentFee,
      //   "isCryptocurrency": this.convertCrypto,
      // }
      // let _url = ApiConstants.UPDATE_FEE;
      // console.log(_reqJson);
      // ServiceCall.httpPostCall(_reqJson, _url, this.http).subscribe(
      //   (data) => {
      //     if (data.code == 200) {
      //       this.getExchangeRate();
      //       this.currentMessage = AlertNotification.updatedFee;
      //       this.alert.showAlert();
      //     }
      //   },
      //   (error: any) => {
      //     this.loader = false;
      //     this.loaderMain = false;
      //     this.currentMessage = AlertNotification.serverErr;
      //     this.alert.showAlert();
      //   });
    }
  }



  get convertFrom() { return this.updateForm.get('convertFrom'); }
  get convertTo() { return this.updateForm.get('convertTo'); }
  get convertFee() { return this.updateForm.get('convertFee'); }

  get currencyFromInput() { return this.exchangeForm.get('currencyFromInput'); }
  get currencyToInput() { return this.exchangeForm.get('currencyToInput'); }
  get currencyTo() { return this.exchangeForm.get('currencyTo'); }
  get currencyFrom() { return this.exchangeForm.get('currencyFrom'); }

}
