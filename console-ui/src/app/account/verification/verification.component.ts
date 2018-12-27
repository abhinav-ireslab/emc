import { Component, OnInit } from '@angular/core';
import { ApiConstants } from '../../network/api-constants';
import { ServiceCall } from '../../network/web-service-call';
import { Http } from '@angular/http';
import { AlertComponent } from '../../alert/alert.component';
import { AlertNotification } from '../../alert-notification';

@Component({
  selector: 'app-verification',
  templateUrl: './verification.component.html',
  styleUrls: ['./verification.component.css']
})
export class VerificationComponent implements OnInit {
  _ekycStatusList: any = [];
  _ekycApprovedList: any = [];
  _itemsPerPageAllowed: number = 10;
  p1: number;
  p2: number;
  currentMessage: string = "";
  loader: boolean = false;
  loaderMain: boolean = false;
  _noRecord: boolean = false;

  constructor(private http: Http, private alert: AlertComponent) { }

  ngOnInit() {
    this.getEkycRequest();
  }


  getEkycRequest() {
    this.p1 = 1;
    this.loader = true;
    this.loaderMain = true;
    let _url = ApiConstants.GET_KYC_STATUS;
    let _reqjson = '';
    ServiceCall.httpGetCall(_url, _reqjson, this.http).subscribe(
      (data) => {
        this.loader = false;
        this.loaderMain = false;
        // console.log(data);
        this._ekycStatusList = data.ekycEkybApprovelList
        // console.log(this._ekycStatusList);
      },
      (error) => {
        this.loader = false;
        this.loaderMain = false;
        this.currentMessage = AlertNotification.serverErr;
        this.alert.showAlert();
        // console.log(error)
      });

  }

  approval(e, i) {
    this.loader = true;
    this.loaderMain = true;
    let _url = ApiConstants.UPDATE_KYC_STATUS;
    let _reqjson = {
      "corellationId": e.corellationId,
      "client": e.client,
      "approvelStatus": true
    }

    ServiceCall.httpPostCall(_reqjson, _url, this.http).subscribe(
      (data) => {
        this.loader = false;
        this.loaderMain = false;
        if (data.code == 100) {
          this._ekycStatusList.splice(((this.p1 - 1) * this._itemsPerPageAllowed) + i, 1);
          this.currentMessage = e.name + " " + AlertNotification.ekycSuccess;
          this.alert.showAlert();
        }
      },
      (error) => {
        this.loader = false;
        this.loaderMain = false;
        this.currentMessage = AlertNotification.serverErr;
        this.alert.showAlert();
        // console.log(error)
      });
  }


  getEkycApprovedList() {
    this.p2 = 1;
    this.loader = true;
    this.loaderMain = true;
    let _url = ApiConstants.GET_APPROVED_KYC;
    let _reqjson = '';
    ServiceCall.httpGetCall(_url, _reqjson, this.http).subscribe(
      (data) => {
        console.log(data)
        this.loader = false;
        this.loaderMain = false;
        this._ekycApprovedList = data.ekycEkybApprovelList
      },
      (error) => {
        this.loader = false;
        this.loaderMain = false;
        this.currentMessage = AlertNotification.serverErr;
        this.alert.showAlert();
        // console.log(error)
      });
  }


}
