package Shapes;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge
 * 
 * Shape Question - Rectangle 
 */

import static org.junit.Assert.*;

import org.junit.Test;

// Tests getArea method in Rectangle class
public class RectangleAreaTest {

	@Test
	public void test() {
		Rectangle rectangle = new Rectangle(4.0, 6.0); 
		double area = rectangle.getArea(); 
		assertEquals(24.0, area, 0); 
	}

}
