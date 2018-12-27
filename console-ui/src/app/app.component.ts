import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';


  ngOnInit() {
    

    document.onkeydown = function (event) {
      if (!event) {
        let event = window.event;
      }
      var keyCode = event.keyCode;
      var value = (<HTMLInputElement>event.target || event.srcElement).tagName
      var readonly = (<HTMLInputElement>event.target).readOnly || (<HTMLInputElement>event.target).disabled;
      if (keyCode == 8 && (value != "TEXTAREA" && value != "INPUT") || readonly) {
        if (navigator.userAgent.toLowerCase().indexOf("msie") == -1) {
          event.stopPropagation();
        } else {
          alert("prevented");
          event.returnValue = false;
        }
        return false;
      }
    };

  }
}
