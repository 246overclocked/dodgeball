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
	public void testQuickSortLong() {
		double[][] array = new double[ARRAY_SIZE][];
		for (int i = 0; i < ARRAY_SIZE; i++) {
			int l = randomNumbers.nextInt(10) + 5;
			array[i] = new double[l];
			for (int x = 0; x < l; x++) {
				array[i][x] = randomNumbers.nextInt(100);
			}
		}
		quickSorter.quickSort(array, 4);
		for (int i = 0; i < ARRAY_SIZE-1; i++) {
			Assert.assertTrue(array[i][4] <= array[i+1][4]);
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
	
	@Test (expected = NullPointerException.class)
	public void testNullArrayHandeling() {
		double[][] array = null;
		quickSorter.quickSort(array, 0);
	}
	
	@Test
	public void testShortArrays() {
		double[][] array = {};
		double[][] arrayCopy = {};
		double[][] array2 = {{0,0}};
		double[][] array2Copy = {{0,0}};
		quickSorter.quickSort(arrayCopy, 0);
		quickSorter.quickSort(array2Copy, 0);
		Assert.assertTrue(Arrays.deepEquals(array, arrayCopy));
		Assert.assertTrue(Arrays.deepEquals(array2, array2Copy));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testInvalidIndex() {
		double[][] array = {{0,0},{1,1,1},{2,2},{3,3},{4,4},{5,5}};
		quickSorter.quickSort(array, 2);
	}

}
