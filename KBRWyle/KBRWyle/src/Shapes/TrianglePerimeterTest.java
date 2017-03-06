package Shapes;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge
 * 
 * Shape Question - Triangle
 */

import static org.junit.Assert.*;

import org.junit.Test;

// Test for the getPerimeter method in Triangle class
public class TrianglePerimeterTest {

	@Test
	public void test() {
		Triangle triangle = new Triangle(4.0, 5.0, 6.0, 3.0);
		double perimeter = triangle.getPerimeter(); 
		assertEquals(15.0, perimeter, 0); 
	}

}