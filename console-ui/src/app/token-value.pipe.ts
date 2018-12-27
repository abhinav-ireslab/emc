import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'tokenValue'
})
export class TokenValuePipe implements PipeTransform {

   transform(val: number):any {
    //  console.log(val)
    if (val !== undefined && val !== null) {
      // var strText = val.toString().replace(",", "");
      // var numText:number = parseInt(strText);
      return val.toString().replace(",", "");
    } else {
      return "";
    }
  }

}


