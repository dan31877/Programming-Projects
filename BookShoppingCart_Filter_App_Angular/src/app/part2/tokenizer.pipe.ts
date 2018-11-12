import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'tokenizer'
})
export class TokenizerPipe implements PipeTransform {

  // Declare variables
  myArr:Array<any>; 
  defaultString:string; 
  withOption:string; 

  // Function to transform the value using a delimeter (args)
  transform(value: any, args?: any): any {

  		// Checks if the value is a string
    	if (typeof value === 'string') {

    		// Split the value into an array, set variables to empty strings
			this.myArr = value.split(""); 
			this.defaultString ="";
			this.withOption=""; 

			// If the delimeter (args) is a string, creates a new string with the delimeter between each character
			if (typeof args === 'string') {
				let i:number = 0;
				for(i=0; i<this.myArr.length; i++){

					// No delimeter is added to the last character
					if( i == this.myArr.length - 1){ 
						this.withOption += this.myArr[i]; 
					} else {
						this.withOption += this.myArr[i] + args; 
					}
				}
				// Returns string
				return this.withOption;
			}

			// If the delimeter (args) is not a string, creates a new string with a comma between each character
			else {
				let i:number = 0;
				for(i=0; i<this.myArr.length; i++){

					// No comma is added to the last character 
					if( i == this.myArr.length - 1){ 
						this.defaultString += this.myArr[i]; 
					} else {
						this.defaultString += this.myArr[i] + ','; 
					}
				}
				// Returns string
				return this.defaultString;
			}
		} else {

			// Returns value
			return value;
		}
  }

}
