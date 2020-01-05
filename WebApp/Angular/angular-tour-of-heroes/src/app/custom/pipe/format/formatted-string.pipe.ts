import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formattedString'
})
export class FormattedStringPipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    //console.log('Value: ' + value);
    //console.log('Args: ' + args);
    return value + ' (' + value.length + ' ||| ' + args[0] + ')';
  }

}
