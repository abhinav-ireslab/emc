import { Pipe, PipeTransform } from '@angular/core';
import { DatePipe } from '@angular/common';

@Pipe({
  name: 'newDatePipe'
})
export class NewDatePipePipe implements PipeTransform {

  transform(value: string) {
    var datePipe = new DatePipe("en-US");
    value = datePipe.transform(value, "dd-MM-yyyy h:mm a")
    return value;
    // return new Date(value);
  }

}

