import { Component, OnInit, Input } from '@angular/core';
import { AlertNotification } from '../alert-notification';

declare var $: any;
@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent implements OnInit {

  @Input() message: string;

  constructor() { }

  ngOnInit() { }

  showAlert() {
    $("#alert").modal("show");
    // setTimeout(() => {
    //   $("#alert").modal("hide");
    // }, 6000);
  }

}
