package org.usfirst.frc.team246.robot.overclockedLibraries;

import org.junit.*;
import java.util.Arrays;
import java.util.Random;

public class TwoDArrayQuickSorterTest {
	
	TwoDArrayQuickSorter quickSorter = new TwoDArrayQuickSorter();
	Random randomNumbers = new Random();
	private final int ARRAY_SIZE = 20;

	@Test
	public void testQuickSort1() {
		double[][] array = new double[ARRAY_SIZE][2];
		for (int i = 0; i < ARRAY_SIZE; i++) {
			array[i][0] = randomNumbers.nextInt(100);
			array[i][1] = randomNumbers.nextInt(100);
		}
		quickSorter.quickSort(array, 0);
		for (int i = 0; i < ARRAY_SIZE-1; i++) {
			Assert.assertTrue(array[i][0] <= array[i+1][0]);
		}
	}
	
	@Test
	public void testQuickSort2() {
		double[][] array = new double[ARRAY_SIZE][2];
		for (int i = 0; i < ARRAY_SIZE; i++) {
			array[i][0] = randomNumbers.nextInt(100);
			array[i][1] = randomNumbers.nextInt(100);
		}
		quickSorter.quickSort(array, 1);
		for (int i = 0; i < ARRAY_SIZE-1; i++) {
			Assert.assertTrue(array[i][1] <= array[i+1][1]);
		}
	}
	
	@Test
	public void testQuickSortConsistency() {
		double[][] array = new double[ARRAY_SIZE][2];
		for (int i = 0; i < ARRAY_SIZE; i++) {
			array[i][0] = randomNumbers.nextInt(100);
			array[i][1] = array[i][0];
		}
		double[][] array2 = new double[ARRAY_SIZE][2];
		for (int i = 0; i < ARRAY_SIZE; i++) {
			array2[i] = array[i];
		}
		quickSorter.quickSort(array, 0);
		quickSorter.quickSort(array2, 1);
		Assert.assertTrue(Arrays.deepEquals(array, array2));
	}

}
