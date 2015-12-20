package org.usfirst.frc.team246.robot.overclockedLibraries;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

public class Vector2DTest {

	@Test
	public void testEqual() {
		Vector2D cartVec1 = new Vector2D(true, 2, -3);
		Vector2D cartVec2 = new Vector2D(true, 2, -3);
		
		Assert.assertTrue(Vector2D.equal(cartVec1, cartVec2));
		
		Vector2D polVec1 = new Vector2D(false, 2, 30);
		Vector2D polVec2 = new Vector2D(false, 2, 30);
		
		Assert.assertTrue(Vector2D.equal(polVec1, polVec2));
	}

	@Test
	public void testPolarToCart() {
		double[] expectedSmallAngle = {-1, Math.sqrt(3)};
		double[] actualSmallAngle = Vector2D.polarToCart(2, 30);
		
		Assert.assertEquals(expectedSmallAngle[0], actualSmallAngle[0], 0.01);
		Assert.assertEquals(expectedSmallAngle[1], actualSmallAngle[1], 0.01);
		
		double[] expectedLargeAngle = {1, Math.sqrt(3)};
		double[] actualLargeAnglePos = Vector2D.polarToCart(2, 330);
		double[] actualLargeAngleNeg = Vector2D.polarToCart(2, -30);
		
		Assert.assertEquals(expectedLargeAngle[0], actualLargeAnglePos[0], 0.01);
		Assert.assertEquals(expectedLargeAngle[1], actualLargeAnglePos[1], 0.01);
		
		Assert.assertEquals(expectedLargeAngle[0], actualLargeAngleNeg[0], 0.01);
		Assert.assertEquals(expectedLargeAngle[1], actualLargeAngleNeg[1], 0.01);
	}

	@Test
	public void testCloneVector() {
		Vector2D cartVec = new Vector2D(true, 2, 3);
		Vector2D cartVecClone = cartVec.cloneVector();
		
		Vector2D polarVec = new Vector2D(false, 2, 30);
		Vector2D polarVecClone = polarVec.cloneVector();
		
		Assert.assertTrue(Vector2D.equal(cartVec, cartVecClone));
		Assert.assertTrue(Vector2D.equal(polarVec, polarVecClone));
	}

//	GETTERS
	@Test
	public void testGetX() {
		Vector2D vec = new Vector2D(true, 2, 3);
		Assert.assertEquals(2, vec.getX(), 0.01);
	}

	@Test
	public void testGetY() {
		Vector2D vec = new Vector2D(true, 2, 3);
		Assert.assertEquals(3, vec.getY(), 0.01);
	}

	@Test
	public void testGetAngle() {
		Vector2D cartVec = new Vector2D(true, -1, Math.sqrt(3));
		Vector2D polarVec = new Vector2D(false, 2, 30);
		
		Assert.assertEquals(30, cartVec.getAngle(), 0.01);
		Assert.assertEquals(30, polarVec.getAngle(), 0.01);
	}

	@Test
	public void testGetMagnitude() {
		Vector2D zero = new Vector2D(true, 0, 0);
		Vector2D cartVec = new Vector2D(true, -1, Math.sqrt(3));
		Vector2D polarVec = new Vector2D(false, 2, 30);
		
		Assert.assertEquals(0, zero.getMagnitude(), 0.01);
		Assert.assertEquals(2, cartVec.getMagnitude(), 0.01);
		Assert.assertEquals(2, polarVec.getMagnitude(), 0.01);
	}

//	SETTERS
	@Test
	public void testSetX() {
		Vector2D cartVec = new Vector2D(true, 2, 3);
		cartVec.setX(5);
		Vector2D polVec = new Vector2D(false, 2, 30);
		polVec.setX(5);
		Assert.assertEquals(5, cartVec.getX(), 0.01);
		Assert.assertEquals(5, polVec.getX(), 0.01);
	}

	@Test
	public void testSetY() {
		Vector2D cartVec = new Vector2D(true, 2, 3);
		cartVec.setY(5);
		Vector2D polVec = new Vector2D(false, 2, 30);
		polVec.setY(5);
		Assert.assertEquals(5, cartVec.getY(), 0.01);
		Assert.assertEquals(5, polVec.getY(), 0.01);
	}

	@Test
	public void testSetAngle() {
		Vector2D cartVec = new Vector2D(true, -2, -3);
		cartVec.setAngle(210);
		Vector2D polVec = new Vector2D(false, 2, 30);
		polVec.setAngle(-30);
		Assert.assertEquals(-150, cartVec.getAngle(), 0.01);
		Assert.assertEquals(-30, polVec.getAngle(), 0.01);
	}

	@Test
	public void testSetMagnitude() {
		Vector2D cartVec = new Vector2D(true, -2, -3);
		cartVec.setMagnitude(2);
		Vector2D polVec = new Vector2D(false, 2, 30);
		polVec.setMagnitude(2);
		Assert.assertEquals(2, cartVec.getMagnitude(), 0.01);
		Assert.assertEquals(2, polVec.getMagnitude(), 0.01);
	}

//	MATH OPERATIONS
	@Test
	public void testAddVectors() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubtractVectors() {
		fail("Not yet implemented");
	}

	@Test
	public void testUnitVector() {
		fail("Not yet implemented");
	}

	@Test
	public void testDotProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testParallelProjection() {
		fail("Not yet implemented");
	}

	@Test
	public void testPerpendicularProjection() {
		fail("Not yet implemented");
	}

}
