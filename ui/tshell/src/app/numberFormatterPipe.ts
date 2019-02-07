import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'numberFormatter'
})
export class NumberFormatterPipe implements PipeTransform {
    
    transform(value:number){
        if(value == 0) {
        return 0;
    }
    else
    {        
      // hundreds
      if(value <= 999){
        return value ;
      }
      // thousands
      else if(value >= 1000 && value <= 999999){
        return (value / 1000) + 'K';
      }
      // millions
      else if(value >= 1000000 && value <= 999999999){
        return (value / 1000000) + 'M';
      }
      // billions
      else if(value >= 1000000000 && value <= 999999999999){
        return (value / 1000000000) + 'B';
      }
      else
        return value ;
      }
    }
}