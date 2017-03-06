package Shapes;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge
 * 
 * Shape Question - Circle 
 */

import static org.junit.Assert.*;
import org.junit.Test;

//Test getArea method in Circle class
public class CircleAreaTest {

	@Test
	public void test() {
		Circle circle = new Circle(4.0); 
		double area = circle.getArea(); 
		assertEquals(12.56, area, 0.01); 
	}

}
