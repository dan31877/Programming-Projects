import { Component, OnInit } from '@angular/core';
import { TokenizerPipe } from './tokenizer.pipe'

@Component({
  selector: 'app-part2',
  templateUrl: './part2.component.html',
  styleUrls: ['./part2.component.css']
})
export class Part2Component implements OnInit {

	// Declare variables
	input:string = "Angular is awesome";
	delimeter:string = "#";

	constructor() { }

	ngOnInit() {
	}

}
