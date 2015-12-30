package org.usfirst.frc.team246.robot.overclockedLibraries;

import static org.junit.Assert.*;
import org.junit.*;

public class DataInterpolatorTest {
	
	DataInterpolator interpolator;

	@Test
	public void testInterpolateValueMedium() {  // values within range of array[i][0] values
		double[][] sampleSpeedData = {{3,9},{5,10.5},{9,10.925},{1,4},{2,7},{6,10.75},{7,10.85},{4,10},{8,10.9}};
		interpolator = new DataInterpolator(sampleSpeedData);
		assertEquals(9.5, interpolator.interpolateValue(3.5), DataInterpolator.getTolerance());
		assertEquals(10.75, interpolator.interpolateValue(6), DataInterpolator.getTolerance());
		assertEquals(10.91875, interpolator.interpolateValue(8.75), DataInterpolator.getTolerance());
	}
	
	@Test
	public void testInterpolateValueSmall() {  // values below range of array[i][0] values
		double[][] sampleSpeedData = {{3,9},{5,10.5},{9,10.925},{1,4},{2,7},{6,10.75},{7,10.85},{4,10},{8,10.9}};
		interpolator = new DataInterpolator(sampleSpeedData);
		assertEquals(1.75, interpolator.interpolateValue(0.25), DataInterpolator.getTolerance());
		assertEquals(1, interpolator.interpolateValue(0), DataInterpolator.getTolerance());
		assertEquals(-8, interpolator.interpolateValue(-3), DataInterpolator.getTolerance());
	}
	
	@Test
	public void testInterpolateValueLarge() {  // values above range of array[i][0] values
		double[][] sampleSpeedData = {{3,9},{5,10.5},{9,10.925},{1,4},{2,7},{6,10.75},{7,10.85},{4,10},{8,10.9}};
		interpolator = new DataInterpolator(sampleSpeedData);
		assertEquals(10.95, interpolator.interpolateValue(10), DataInterpolator.getTolerance());
		assertEquals(11.95, interpolator.interpolateValue(50), DataInterpolator.getTolerance());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testEmptyArray() {
		double[][] emptyArray = {};
		interpolator = new DataInterpolator(emptyArray);
		interpolator.interpolateValue(5);
	}
	
	@Test (expected = NullPointerException.class)
	public void testNullArrayHandling() {
		double[][] nullArray = null;
		interpolator = new DataInterpolator(nullArray);
		interpolator.interpolateValue(5);
	}

}
