import { Injectable, Component } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { ApiConstants } from '../../network/api-constants';
import { ServiceCall } from '../../network/web-service-call';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';


// @Component({
//   providers: [ServiceCall],
// })
declare var $: any;
@Injectable()
export class AccountLedgerService {
  nextLedger: any = '0';
  itemsPerPageAllowed: any = 200;
  scrollFunctionComplete: boolean = false
  loaderForLedger: boolean = false
  dataDuplicate: boolean = false;
  noMoreData: boolean = false;
  ledgerAccountName: string = '';

  public mysubject = new BehaviorSubject("ledger_data");
  ledgerData = this.mysubject.asObservable();

  constructor(private http: Http) { }

  shareAccountLedger(data) {
    this.mysubject.next(data);
  }

  // getUserLedgerDetails(limit) {
  //   if (this.scrollFunctionComplete == true || this.noMoreData == true) {
  //     return
  //   } else {
  //     this.dataDuplicate = false;
  //     this.scrollFunctionComplete = true;
  //     this.loaderForLedger = true;
  //     let _url = ApiConstants.REQUEST_LEDGER_URL;
  //     let reqJson = JSON.stringify({
  //       "nextLink": this._nextLink,
  //       "limit": limit,
  //       "publicKey": window.localStorage.getItem("baseTXN")
  //     });
  //     ServiceCall.httpPostCall(reqJson, _url, this.http).subscribe(
  //       (data) => {
  //         if (data.nextLink == undefined || data.nextLink == '' || data.nextLink == null) {
  //           this.noMoreData = true;
  //           console.log(this.noMoreData)
  //         } else {
  //           this._nextLink = data.nextLink;
  //         }
  //         if (limit == "") {
  //           this.shareAccountLedger(data.records);
  //         } else if (limit == "10") {
  //           this.shareAccountLedger(data);
  //         }
  //         this.loaderForLedger = false;
  //         this.scrollFunctionComplete = false;
  //       },
  //       (error: any) => {
  //         this.loaderForLedger = false;
  //         this.scrollFunctionComplete = false;
  //         console.log(error)
  //       }
  //     )
  //   }
  // }

  // getUserLedgerDetails(limit) {
    
  //   let _url = ApiConstants.REQUEST_LEDGER_URL+ '/' + this.nextLedger + '/' + this.itemsPerPageAllowed;
  //   console.log(_url)
  //   ServiceCall.httpGetCall(_url, '', this.http).subscribe(
  //     (data) => {
  //       console.log(data)
  //       if (data.next == 'undefined' || data.next == undefined || data.next == '') {
          
  //       } else {
  //         this.nextLedger = data.next;
  //         console.log(this.nextLedger)
  //       }
  //       if (data.assets.length == 0) {
  //         this._noAssetDetails = true;
  //       }
  //       if (data.assets.length > 0) {
  //         this._userAssets = data.assets;
  //       }
  //       if (data.payment.length == 0 && this._userRecords.length == 0) {
  //         this._noPayDetails = true;
  //       }
  //       if (data.payment.length > 0) {
  //         for (let payment of data.payment) {
  //           this._userRecords.push(payment);
  //         }
  //         console.log(this._userRecords)
  //       }
  //     },
  //     (error: any) => {
  //       this.loaderForLedger = false;
  //       this.scrollFunctionComplete = false;
  //       console.log(error)
  //     }
  //   );
  // }

  keyRecord(i) {
    // console.log("keyRecord" + i)
    $("#" + i).toggle();
  }


  enablePopover() {
    $('[data-toggle="tooltip"]').tooltip();
    $('[data-toggle="popover"]').popover();
  }
  
  getValueFrom(record): string {
    if (record.fromDetails[record.from].clientName != null) {
      this.ledgerAccountName = record.fromDetails[record.from].clientName;
    } else if (record.fromDetails[record.from].firstName != null) {
      this.ledgerAccountName = record.fromDetails[record.from].firstName + " " + record.fromDetails[record.from].lastName;
    } else {
      this.ledgerAccountName = record.fromDetails[record.from].message
    }
    return this.ledgerAccountName;
  }

  getValueTo(record): string {
    if (record.toDetails[record.to].clientName != null) {
      this.ledgerAccountName = record.toDetails[record.to].clientName;
    } else if (record.toDetails[record.to].firstName != null) {
      this.ledgerAccountName = record.toDetails[record.to].firstName + " " + record.toDetails[record.to].lastName;
    } else {
      this.ledgerAccountName = record.toDetails[record.to].message
    }
    this.enablePopover();
    return this.ledgerAccountName;
  }
  
  getValueAssets(balance): string {
    // console.log(balance);
    if (balance.assetIssuerDetails[balance.asset_issuer].clientName != null) {
      this.ledgerAccountName = balance.assetIssuerDetails[balance.asset_issuer].clientName;
    } else if (balance.assetIssuerDetails[balance.asset_issuer].firstName != null) {
      this.ledgerAccountName = balance.assetIssuerDetails[balance.asset_issuer].firstName + " " + balance.assetIssuerDetails[balance.asset_issuer].lastName;
    } else {
      this.ledgerAccountName = "Unknown"
    }
    this.enablePopover();
    return this.ledgerAccountName;
  }

  getValueChangetrustFrom(record): string {
    // console.log(record);
    if (record.asset_issuerDetails[record.asset_issuer].clientName != null) {
      this.ledgerAccountName = record.asset_issuerDetails[record.asset_issuer].clientName;
    } else if (record.asset_issuerDetails[record.asset_issuer].firstName != null) {
      this.ledgerAccountName = record.asset_issuerDetails[record.asset_issuer].firstName + " " + record.asset_issuerDetails[record.asset_issuer].lastName;
    } else {
      this.ledgerAccountName = "Unknown"
    }
    this.enablePopover();
    return this.ledgerAccountName;
  }
  getValueChangetrustTo(record): string {
    // console.log(balance);
    if (record.sourceDetails[record.source_account].clientName != null) {
      this.ledgerAccountName = record.sourceDetails[record.source_account].clientName;
    } else if (record.sourceDetails[record.source_account].firstName != null) {
      this.ledgerAccountName = record.sourceDetails[record.source_account].firstName + " " + record.sourceDetails[record.source_account].lastName;
    } else {
      this.ledgerAccountName = "Unknown"
    }
    this.enablePopover();
    return this.ledgerAccountName;
  }
}
