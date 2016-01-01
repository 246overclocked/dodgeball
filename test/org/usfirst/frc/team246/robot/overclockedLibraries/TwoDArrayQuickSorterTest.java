package org.usfirst.frc.team246.robot.overclockedLibraries;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Random;

public class TwoDArrayQuickSorterTest {
	
	Random randomNumbers = new Random();
	private final int ARRAY_SIZE = 20;

	@Test
	public void testQuickSortGeneral() {
		double[][] array = new double[ARRAY_SIZE][2];
		double[][] array2 = new double[ARRAY_SIZE][2];
		for (int i = 0; i < ARRAY_SIZE; i++) {
			array[i][0] = randomNumbers.nextInt(100);
			array[i][1] = randomNumbers.nextInt(100);
		}
		for (int i = 0; i < ARRAY_SIZE; i++) {  // duplicate array into array2
			array2[i] = array[i];
		}
		TwoDArrayQuickSorter.quickSort(array, 0); // array sorted by index 0
		TwoDArrayQuickSorter.quickSort(array2, 1); // the same array is being sorted here, but by index 1
		for (int i = 0; i < ARRAY_SIZE-1; i++) {
			assertTrue(array[i][0] <= array[i+1][0]);
		}
		for (int i = 0; i < ARRAY_SIZE-1; i++) {
			assertTrue(array2[i][1] <= array2[i+1][1]);
		}
	}
	
	@Test
	public void testQuickSortLong() {
		double[][] array = new double[ARRAY_SIZE][];
		for (int i = 0; i < ARRAY_SIZE; i++) {
			int l = randomNumbers.nextInt(10) + 5; // the size shouldn't go below 5
			array[i] = new double[l];
			for (int x = 0; x < l; x++) {
				array[i][x] = randomNumbers.nextInt(100);
			}
		}
		TwoDArrayQuickSorter.quickSort(array, 4); // because the size doesn't go below 5, index 4 is always available
		for (int i = 0; i < ARRAY_SIZE-1; i++) {
			assertTrue(array[i][4] <= array[i+1][4]);
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
		TwoDArrayQuickSorter.quickSort(array, 0);
		TwoDArrayQuickSorter.quickSort(array2, 1);
		assertTrue(Arrays.deepEquals(array, array2));
	}
	
	@Test (expected = NullPointerException.class)
	public void testQuickSortNullArrayHandling() {
		double[][] array = null;
		TwoDArrayQuickSorter.quickSort(array, 0);
	}
	
	@Test
	public void testQuickSortShortArrays() {
		double[][] array = {};
		double[][] arrayCopy = {};
		double[][] array2 = {{0,0}};
		double[][] array2Copy = {{0,0}};
		TwoDArrayQuickSorter.quickSort(arrayCopy, 0);
		TwoDArrayQuickSorter.quickSort(array2Copy, 0);
		assertTrue(Arrays.deepEquals(array, arrayCopy));
		assertTrue(Arrays.deepEquals(array2, array2Copy));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testQuickSortInvalidIndex() {
		double[][] array = {{0,0},{1,1,1},{2,2},{3,3},{4,4},{5,5}};
		TwoDArrayQuickSorter.quickSort(array, 2);
	}

}
