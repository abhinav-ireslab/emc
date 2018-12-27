import { Component, OnInit } from '@angular/core';
import { NewDatePipePipe } from '../../new-date-pipe.pipe';
import { ApiConstants } from '../../network/api-constants';
import { ServiceCall } from '../../network/web-service-call';
import { Http } from '@angular/http';
import { AccountLedgerService } from './user-account-ledger.service';
import { DatePipe } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlertComponent } from '../../alert/alert.component';
import { AlertNotification } from '../../alert-notification';


declare var $: any;
@Component({
  selector: 'app-user-account-ledger',
  templateUrl: './user-account-ledger.component.html',
  styleUrls: ['./user-account-ledger.component.css'],
  providers: [DatePipe]
})
export class UserAccountLedgerComponent implements OnInit {
  currentMessage: string = "";
  minDate = new Date(2018, 0, 1);
  maxDate = new Date();
  __fromdate: any;
  __todate: any;
  TranHash: any;
  downloadXlsx: any;
  downloadCsv: any;

  searchForm: FormGroup
  userAccountLedger: any;
  balances: any;
  _transactionDetails: any = []
  payment: boolean = false;
  change_trust: boolean = false;
  transaction_hash: boolean = false;
  loader: boolean = false;
  loaderMain: boolean = false;
  _noAssetDetails = false;
  _noPayDetails = false;
  _userAssets: any;
  _userRecords: any = [];
  AccountHolderName: any;
  itemsPerPageAllowed: any = 20;
  // itemsAllowedBusinessLedger: any = 20;
  nextLedger: any = 0;
  loaderForLedger: boolean = false;
  scrollFunctionComplete: boolean = false;
  _selectedUserObj: any;
  _userPubKey: any;
  modalScrollDistance = 2;
  modalScrollThrottle = 50;
  ledgerAccountName: string = '';
  p: any;
  _filterBy: string = "all";
  _totalElements: any;
  _files: any = []
  _fromdate: any = "";
  _todate: any = "";
  _searchname: any = "";
  _filterDateFrom: any = "";
  _filterDateTo: any = "";
  _filterName: any = "";
  searchInvalid: boolean = false;
  toDateInvalid: boolean = false;
  fromDateInvalid: boolean = false;

  pageCount: any;
  constructor(private http: Http, private als: AccountLedgerService, public datepipe: DatePipe, private fb: FormBuilder, private alert: AlertComponent) {
    this.searchForm = this.fb.group({
      findname: "",
      datePickerTwo: "",
      datePickerOne: "",
      fileFormat: "",

    });
    this.callBusinessLedger("new", "")
    this._files = [
      { value: 'EXCEL', viewValue: 'EXCEL' },
      { value: 'CSV', viewValue: 'CSV' },
    ];
  }

  ngOnInit() {
    $('[data-toggle="popover"]').popover();
  }

  fromDate(e) {
    if (e == "" || e == undefined || e == null) {
      this._filterDateFrom = ""
      this._fromdate = ""
    } else {
      this.__fromdate = e;
      this._fromdate = this.datepipe.transform(e, 'yyyy-MM-dd');
      this._filterDateFrom = "dateFrom"
    }
  }
  toDate(e) {
    if (e == "" || e == undefined || e == null) {
      this._filterDateTo = ""
      this._todate = ""
    } else {
      this.__todate = e;
      this._todate = this.datepipe.transform(e, 'yyyy-MM-dd');
      this._filterDateTo = "dateTo"
    }

  }
  searchName(e) {
    if (e.target.value == "" || e.target.value == undefined || e.target.value == null) {
      this._filterName = ""
      this._searchname = ""
    } else {
      this._searchname = e.target.value
      this._filterName = "name"
    }

  }

  // searchReset(){
  //   this.searchForm.reset();
  //   this._filterBy = "all";
  //   this._searchname = ""
  //   this._todate = ""
  //   this._fromdate = ""
  //   this._filterDateFrom = ""
  //   this._filterDateTo = ""
  //   this._filterName = ""
  // }

  callBusinessLedger(data, toDownload) {
    // if (data != "new") {
    // if ( this._filterBy == "date") {
    //   var test = (this.__todate - this.__fromdate) / (1000 * 60 * 60 * 24);

    //   if (test > 90) {
    //     this.currentMessage = AlertNotification.dateMaxFilter;
    //     this.alert.showAlert();
    //     return;
    //   }
    //   else if (test < 0) {
    //     if (this._filterBy == "date") {
    //       this.currentMessage = AlertNotification.dateMinFilter;
    //       this.alert.showAlert();
    //     }
    //     return;
    //   }
    // }
    this.p = 1;
    if (data == 'new') {
      this.searchForm.reset();
      this._filterBy = "all";
      this._searchname = ""
      this._todate = ""
      this._fromdate = ""
      this._filterDateFrom = ""
      this._filterDateTo = ""
      this._filterName = ""
    } else {

      if (this._filterDateFrom == "dateFrom" && this._filterDateTo == "dateTo" && this._filterName == "name") {
        this._filterBy = "both";
      } else if (this._filterDateFrom == "dateFrom" && this._filterDateTo == "dateTo" && this._filterName == "") {
        this._filterBy = "date";
      } else if (this._filterDateFrom == "" && this._filterDateTo == "" && this._filterName == "name") {
        this._filterBy = "name";
      } else if (this._filterDateFrom == "dateFrom" && this._filterDateTo == "" && this._filterName == "") {
        this._filterBy = "all"
      } else if (this._filterDateFrom == "" && this._filterDateTo == "dateTo" && this._filterName == "") {
        this._filterBy = "all"
      } else if (this._filterDateFrom == "dateFrom" && this._filterDateTo == "" && this._filterName == "name") {
        this._filterBy = "name";
      } else if (this._filterDateFrom == "" && this._filterDateTo == "dateTo" && this._filterName == "name") {
        this._filterBy = "name";
      } else {
        this._filterBy = "all";
      }
      var datePara = (this.__todate - this.__fromdate) / (1000 * 60 * 60 * 24);
      if (datePara > 90) {
        if (this._filterBy == "date" || this._filterBy == "both") {
          this.currentMessage = AlertNotification.dateMaxFilter;
          this.alert.showAlert();
          return;
        }
        // this.currentMessage = AlertNotification.dateMaxFilter;
        // this.alert.showAlert();
        // return;
      }
      else if (datePara < 0) {
        if (this._filterBy == "date" || this._filterBy == "both") {
          this.currentMessage = AlertNotification.dateMinFilter;
          this.alert.showAlert();
          return;
        }
      }

    }
    this.getBusinessLedgerDetails("0", toDownload)
  }

  getBusinessLedgerDetails(pageCount, toDownload) {
    this.p = pageCount + 1
    let _url = ApiConstants.REQUEST_BUSINESS_LEDGER_URL + '/' + pageCount + '/' + this.itemsPerPageAllowed;
    let _urlDownload = ApiConstants.DOWNLOAD_BUSINESS_LEDGER_URL;
    let _reqjson = JSON.stringify(
      {
        "filterBy": this._filterBy,
        "filterData": this._searchname,
        "sourceCorrelationId": "",
        "destinationCorrelationId": "",
        "sourceAddress": "",
        "fromDate": this._fromdate,
        "toDate": this._todate,
        "dataFormat": this.searchForm.controls['fileFormat'].value,
        // "dataFormat": "",
      }
    );
    // console.log(_reqjson)
    if (toDownload == 'yes') {
      if (this.searchForm.controls['fileFormat'].value != null) {
        // console.log(this._filterBy);
        if (this._filterBy == "date" || this._filterBy == "both") {
          debugger;
          this.downloadXlsx = "Businessledger_" + this._fromdate + " " + "-" + " " + this._todate + ".xlsx";
          this.downloadCsv = "Businessledger_" + this._fromdate + " " + "-" + " " + this._todate + ".csv";

        } else {
          debugger;
          let today = new Date();
          let currentdate = this.datepipe.transform(today, 'yyyy-MM-dd');
          let lastDate1 = today.setDate(today.getDate()-90) ;
          let lastDate =  this.datepipe.transform(lastDate1, 'yyyy-MM-dd');
          
          this.downloadXlsx = "Businessledger_" + lastDate + " " + "-" + " " + currentdate + ".xlsx";
          this.downloadCsv = "Businessledger_" + lastDate + " " + "-" + " " + currentdate + ".csv";
        }
        this.loader = true;
        this.loaderMain = true;
        ServiceCall.httpDownloadPostCall(_reqjson, _urlDownload, this.http).subscribe(
          (data) => {
            // console.log(data)
            this.loader = false;
            this.loaderMain = false;


            if (this.searchForm.controls['fileFormat'].value == 'EXCEL') {
              let blob = new Blob([data._body], { type: 'application/vnd.ms-excel' });
              if (window.navigator && window.navigator.msSaveOrOpenBlob) {
                window.navigator.msSaveOrOpenBlob(blob, this.downloadXlsx);
              } else {
                var a = document.createElement('a');
                a.href = URL.createObjectURL(blob);
                a.download = this.downloadXlsx;
                document.body.appendChild(a);
                a.click();
                document.body.removeChild(a);
              }

            } else if (this.searchForm.controls['fileFormat'].value == 'CSV') {
              var blob = new Blob([data._body], { type: 'text/csv' });
              if (window.navigator && window.navigator.msSaveOrOpenBlob) {
                window.navigator.msSaveOrOpenBlob(blob, this.downloadCsv);
              } else {
                var a = document.createElement('a');
                a.href = URL.createObjectURL(blob);
                a.download = this.downloadCsv;
                document.body.appendChild(a);
                a.click();
                document.body.removeChild(a);
              }

            } else {

            }
          },
          (error: any) => {
            // console.log(error)
            this.loader = false;
            this.loaderMain = false;
            this.currentMessage = AlertNotification.serverErr;
            this.alert.showAlert();
          }
        );
      } else {
        this.currentMessage = AlertNotification.selectFormat;
        this.alert.showAlert();
        return;
      }

    } else {
      this.loader = true;
      this.loaderMain = true;
      ServiceCall.httpPostCall(_reqjson, _url, this.http).subscribe(
        (data) => {
          // console.log(data)
          this.loader = false;
          this.loaderMain = false;
          this._transactionDetails = data.transactionList;
          this._totalElements = data.totalElements;
        },
        (error: any) => {
          // console.log(error)
          this.loader = false;
          this.loaderMain = false;
          this.currentMessage = AlertNotification.serverErr;
          this.alert.showAlert();
        }
      );

    }

  }

  openUserViewReportModal(_user, _userPubKey, userName) {
    this._userPubKey = _userPubKey;
    this.AccountHolderName = userName;
    this.nextLedger = 0;
    this.userViewReport();
  }
  closeViewReportModal() {
    $('#userLedger').modal('hide')
    this._userRecords = [];
  }
  userViewReport() {
    if (this.scrollFunctionComplete == true) {
      return;
    } else {
      this.scrollFunctionComplete = true;
      this.loaderForLedger = true;
      this._noAssetDetails = false;
      this._noPayDetails = false;
      let _url = ApiConstants.REQUEST_USER_LEDGER_URL + this._userPubKey + '/' + this.nextLedger + '/' + this.itemsPerPageAllowed;

      ServiceCall.httpGetCall(_url, '', this.http).subscribe(
        (data) => {

          if (data.next == 'undefined' || data.next == undefined || data.next == '') {
          } else {
            this.nextLedger = data.next;
          }
          if (data.assets.length == 0) {
            this._noAssetDetails = true;
          }
          if (data.assets.length > 0) {
            this._userAssets = data.assets;
          }
          if (data.payment.length == 0 && this._userRecords.length == 0) {
            this._noPayDetails = true;
          }
          if (data.payment.length > 0) {
            for (let payment of data.payment) {
              this._userRecords.push(payment);
            }
          }
          this.loaderForLedger = false;
          this.scrollFunctionComplete = false;
        },
        (error: any) => {
          this.loaderForLedger = false;
          this.scrollFunctionComplete = false;
          this.currentMessage = AlertNotification.serverErr;
          this.alert.showAlert();
        }
      );
    }
  }



}
