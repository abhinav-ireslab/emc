import { Component, OnInit } from '@angular/core';
// import { AlertComponent } from '../../alert/alert.component';
// import { AlertNotification } from '../../alert-notification';

declare var $: any;
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  // currentMessage: string = "";

  constructor() { }
  // constructor(private alert: AlertComponent) { }

  ngOnInit() { }

  // test() {
  //   this.currentMessage = AlertNotification.serverErr;
  //   this.alert.showAlert();
  // }

}
