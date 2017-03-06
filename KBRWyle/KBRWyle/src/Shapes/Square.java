package Shapes;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge
 * 
 * Shape Question - Square
 */

// Inherits the methods from the Shape interface from Rectangle class
public class Square extends Rectangle implements Shape {

	private double side; 
	
	
	public Square() {

	}
	
	public Square(double side) {
		super(side, side); 
		this.side = side;
	}

	// Setters and Getters
	public double getSide() {
		return side;
	}

	public void setSide(double side) {
		this.side = side;
	}


}
