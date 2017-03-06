package Shapes;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge
 * 
 * Shape Question - Triangle
 */

public class Triangle implements Shape {
	
	private double leftSide; 
	private double rightSide; 
	private double base;
	private double height; 
	
	public Triangle() {
	
	}
	
	public Triangle(double leftSide, double rightSide, double base, double height) {
		this.leftSide = leftSide;
		this.rightSide = rightSide;
		this.base = base;
		this.height = height;
	}
	
	// Methods from Shape Interface
	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return (this.getBase()*this.getHeight())/2;
	}

	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		return this.getBase() + this.getLeftSide() + this.getRightSide();
	}

	// Getters and Setters
	public double getLeftSide() {
		return leftSide;
	}

	public void setLeftSide(double leftSide) {
		this.leftSide = leftSide;
	}

	public double getRightSide() {
		return rightSide;
	}

	public void setRightSide(double rightSide) {
		this.rightSide = rightSide;
	}

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

}
