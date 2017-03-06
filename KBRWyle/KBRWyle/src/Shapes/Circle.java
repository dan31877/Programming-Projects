package Shapes;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge
 * 
 * Shape Question - Circle 
 */

public class Circle implements Shape {


	private double diameter; 
	
	public Circle() {
	}
	

	public Circle(double diameter) {
		this.diameter = diameter;
	}
	
	// Methods from Shape Interface
	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return (Math.PI*(Math.pow((this.getDiameter()/2), 2)));
	}

	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		return (Math.PI*this.getDiameter());
	}

	// Getters and Setters
	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

}
