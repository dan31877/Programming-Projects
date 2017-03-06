package Shapes;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge
 * 
 * Shape Question - Rectangle 
 */

public class Rectangle implements Shape{

	private double width; 
	private double length; 
	
	public Rectangle() {

	}
	
	public Rectangle(double width, double length) {
		this.width = width;
		this.length = length;
	}
	
	// Methods from Shape Interface
	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return this.getLength()*this.getWidth();
	}

	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		return (2*(this.getLength()) + 2*(this.getWidth()));
	}

	// Getters and Setters
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

}
