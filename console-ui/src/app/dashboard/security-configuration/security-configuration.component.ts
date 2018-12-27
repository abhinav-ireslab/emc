import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-security-configuration',
  templateUrl: './security-configuration.component.html',
  styleUrls: ['./security-configuration.component.css']
})
export class SecurityConfigurationComponent implements OnInit {

  _cKey: string;
  _sKey: string;
  loader: boolean = false;
  loaderMain: boolean = false;

  constructor() {
    this.loader = true;
    this.loaderMain = true;
    this._cKey = window.localStorage.getItem("cKey");
    this._sKey = window.localStorage.getItem("sKey");
    this.loader = false;
    this.loaderMain = false;
  }

  ngOnInit() {

  }


}
