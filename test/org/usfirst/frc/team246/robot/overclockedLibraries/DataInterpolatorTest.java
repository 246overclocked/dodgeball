package org.usfirst.frc.team246.robot.overclockedLibraries;

import static org.junit.Assert.*;
import org.junit.*;

public class DataInterpolatorTest {
	
	DataInterpolator interpolator;

	@Test
	public void testInterpolateValueMedium() {  // values within range of array[i][0] values
		double[][] sampleSpeedData = {{3,9},{5,10.5},{9,10.925},{1,4},{2,7},{6,10.75},{7,10.85},{4,10},{8,10.9}};
		assertEquals(9.5, DataInterpolator.interpolateValue(3.5, sampleSpeedData), DataInterpolator.getTolerance());
		assertEquals(10.75, DataInterpolator.interpolateValue(6, sampleSpeedData), DataInterpolator.getTolerance());
		assertEquals(10.91875, DataInterpolator.interpolateValue(8.75, sampleSpeedData), DataInterpolator.getTolerance());
	}
	
	@Test
	public void testInterpolateValueSmall() {  // values below range of array[i][0] values
		double[][] sampleSpeedData = {{3,9},{5,10.5},{9,10.925},{1,4},{2,7},{6,10.75},{7,10.85},{4,10},{8,10.9}};
		assertEquals(1.75, DataInterpolator.interpolateValue(0.25, sampleSpeedData), DataInterpolator.getTolerance());
		assertEquals(1, DataInterpolator.interpolateValue(0, sampleSpeedData), DataInterpolator.getTolerance());
		assertEquals(-8, DataInterpolator.interpolateValue(-3, sampleSpeedData), DataInterpolator.getTolerance());
	}
	
	@Test
	public void testInterpolateValueLarge() {  // values above range of array[i][0] values
		double[][] sampleSpeedData = {{3,9},{5,10.5},{9,10.925},{1,4},{2,7},{6,10.75},{7,10.85},{4,10},{8,10.9}};
		assertEquals(10.95, DataInterpolator.interpolateValue(10, sampleSpeedData), DataInterpolator.getTolerance());
		assertEquals(11.95, DataInterpolator.interpolateValue(50, sampleSpeedData), DataInterpolator.getTolerance());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testInterpolateValueEmptyArray() {
		double[][] emptyArray = {};
		DataInterpolator.interpolateValue(5, emptyArray);
	}
	
	@Test (expected = NullPointerException.class)
	public void testInterpolateValueNullArrayHandling() {
		double[][] nullArray = null;
		DataInterpolator.interpolateValue(5, nullArray);
	}

}
