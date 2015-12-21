package org.usfirst.frc.team246.robot.overclockedLibraries;

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
		
		Vector2D closeVec1 = new Vector2D(true, -2.914213562373094, 4.012289773726411);
		Vector2D closeVec2 = new Vector2D(true, -2.9142, 4.0123);
		
		Assert.assertTrue(Vector2D.equal(closeVec1, closeVec2));
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
		Vector2D cartVec1 = new Vector2D(true, 3, 5);
		Vector2D cartVec2 = new Vector2D(true, -5, 3);
		Vector2D expectedCartSum = new Vector2D(true, -2, 8);
		
		Vector2D polVec1 = new Vector2D(false, 3, 30);
		Vector2D polVec2 = new Vector2D(false, 2, 45);
		Vector2D expectedPolSum = new Vector2D(true, -2.9142, 4.0123);

		Assert.assertTrue(Vector2D.equal(Vector2D.addVectors(cartVec1, cartVec2), expectedCartSum));
		Assert.assertTrue(Vector2D.equal(Vector2D.addVectors(polVec1, polVec2), expectedPolSum));
	}

	@Test
	public void testSubtractVectors() {
		Vector2D cartVec1 = new Vector2D(true, 3, 5);
		Vector2D cartVec2 = new Vector2D(true, -5, 3);
		Vector2D expectedCartDiff = new Vector2D(true, 8, 2);
		
		Vector2D polVec1 = new Vector2D(false, 3, 30);
		Vector2D polVec2 = new Vector2D(false, 2, 45);
		Vector2D expectedPolDiff = new Vector2D(true, -0.085786, 1.183863);
		
		Assert.assertTrue(Vector2D.equal(Vector2D.subtractVectors(cartVec1, cartVec2), expectedCartDiff));
		Assert.assertTrue(Vector2D.equal(Vector2D.subtractVectors(polVec1, polVec2), expectedPolDiff));
	}

	@Test
	public void testUnitVector() {
		Vector2D cartOriginal = new Vector2D(true, 3, 4);
		Vector2D expectedCartUnit = new Vector2D(true, 3.0/5, 4.0/5);
		
		Vector2D polOriginal = new Vector2D(false, 3, 30);
		Vector2D expectedPolUnit = new Vector2D(true, -0.50000, 0.86603);
		
		Assert.assertTrue(Vector2D.equal(cartOriginal.unitVector(), expectedCartUnit));
		Assert.assertTrue(Vector2D.equal(polOriginal.unitVector(), expectedPolUnit));
	}

	@Test
	public void testDotProduct() {
		Vector2D cartVec1 = new Vector2D(true, 3, 7);
		Vector2D cartVec2 = new Vector2D(true, -5, 3);
		double expectedCart = 6;
		
		Vector2D polVec1 = new Vector2D(false, 3, 30);
		Vector2D polVec2 = new Vector2D(false, 2, 45);
		double expectedPol = 5.7956;
		
		Assert.assertEquals(Vector2D.dotProduct(cartVec1, cartVec2), expectedCart, 0.01);
		Assert.assertEquals(Vector2D.dotProduct(polVec1, polVec2), expectedPol, 0.01);
	}

	@Test
	public void testParallelProjection() {
		Vector2D cartVec = new Vector2D(true, 3, 4);
		Vector2D cartXComponent = new Vector2D(true, 3, 0);
		Vector2D cartYComponent = new Vector2D(true, 0, 4);
		
		Vector2D polVec = new Vector2D(false, 3, 30);
		Vector2D polXComponent = new Vector2D(true, -1.5, 0);
		Vector2D polYComponent = new Vector2D(true, 0, 2.5981);
		
		Vector2D xAxis = new Vector2D(true, 1, 0);
		Vector2D yAxis = new Vector2D(true, 0, 1);
		
		Assert.assertTrue(Vector2D.equal(Vector2D.parallelProjection(cartVec, xAxis), cartXComponent));
		Assert.assertTrue(Vector2D.equal(Vector2D.parallelProjection(cartVec, yAxis), cartYComponent));
		
		Assert.assertTrue(Vector2D.equal(Vector2D.parallelProjection(polVec, xAxis), polXComponent));
		Assert.assertTrue(Vector2D.equal(Vector2D.parallelProjection(polVec, yAxis), polYComponent));
	}

	@Test
	public void testPerpendicularProjection() {
		Vector2D cartVec = new Vector2D(true, 3, 4);
		Vector2D cartXComponent = new Vector2D(true, 3, 0);
		Vector2D cartYComponent = new Vector2D(true, 0, 4);
		
		Vector2D polVec = new Vector2D(false, 3, 30);
		Vector2D polXComponent = new Vector2D(true, -1.5, 0);
		Vector2D polYComponent = new Vector2D(true, 0, 2.5981);
		
		Vector2D xAxis = new Vector2D(true, 1, 0);
		Vector2D yAxis = new Vector2D(true, 0, 1);
		
		Assert.assertTrue(Vector2D.equal(Vector2D.perpendicularProjection(cartVec, xAxis), cartYComponent));
		Assert.assertTrue(Vector2D.equal(Vector2D.perpendicularProjection(cartVec, yAxis), cartXComponent));
		
		Assert.assertTrue(Vector2D.equal(Vector2D.perpendicularProjection(polVec, xAxis), polYComponent));
		Assert.assertTrue(Vector2D.equal(Vector2D.perpendicularProjection(polVec, yAxis), polXComponent));
	}

}
