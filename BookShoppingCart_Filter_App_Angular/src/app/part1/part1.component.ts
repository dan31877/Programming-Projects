import { Component, OnInit } from '@angular/core';
import { Book } from './book'; 

@Component({
  selector: 'app-part1',
  templateUrl: './part1.component.html',
  styleUrls: ['./part1.component.css']
})

export class Part1Component implements OnInit {

  // Declare variables
 	books:Array<any>; 
 	newBook:any;   
  storedData:any;

  // Constructor - define attributes
	constructor() { 
      this.storedData = window.localStorage.getItem("Anderson_cart");
      if(this.storedData) {
        this.books = JSON.parse(this.storedData);
      } else {
  	  	this.books = [{title: 'Absolute Java', qty: 1, price: 114.95},
  	                  {title: 'Pro HTML5', qty: 1, price: 27.95},
  	                  {title: 'Head First HTML5', qty: 1, price: 27.89}];
        }
      	this.newBook = {title: 'New Book', qty: 1, price: 10.99}; 
	}

	ngOnInit() {
	}

    // Function to add a book to the list
    addBook() {
       this.books.push(this.newBook);
       this.newBook = {title: 'New Book', qty: 1, price: 10.99}
    }

    // Function to get the running total amount
    getTotal():number { 
        let total:number = 0;
        let i:number = 0; 
        for( i=0;i<this.books.length;i++){ 
        	total += this.books[i].qty * this.books[i].price;
        }
        return total; 
    }

    // Saves to local storage
    save() { 
        window.localStorage.setItem("Anderson_cart", JSON.stringify(this.books));
     }

    // Removes the book from the list
    removeBook(index) {
        this.books.splice(index, 1);
    
    }

}
