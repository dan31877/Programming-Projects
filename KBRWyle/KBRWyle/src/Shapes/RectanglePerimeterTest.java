package Shapes;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge
 * 
 * Shape Question - Rectangle 
 */

import static org.junit.Assert.*;

import org.junit.Test;

//Tests getPerimeter method in Rectangle class
public class RectanglePerimeterTest {

	@Test
	public void test() {
		Rectangle rectangle = new Rectangle(7.0, 8.0); 
		double perimeter = rectangle.getPerimeter(); 
		assertEquals(30.0, perimeter, 0); 
	}

}
