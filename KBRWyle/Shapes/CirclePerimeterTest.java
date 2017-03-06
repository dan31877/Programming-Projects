package Shapes;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge
 * 
 * Shape Question - Circle 
 */

import static org.junit.Assert.*;

import org.junit.Test;


// Test getPerimeter method in Circle class
public class CirclePerimeterTest {

	@Test
	public void test() {
		Circle circle = new Circle(6.0);
		double perimeter = circle.getPerimeter(); 
		assertEquals(18.84, perimeter, 0.01); 
	}

}
