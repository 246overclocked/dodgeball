package org.usfirst.frc.team246.robot.overclockedLibraries;

/**
 * This is a generic class for the interpolation of data stored in a 2D array. 
 * The 2D data array is a parameter of this object's constructor. The array used
 * in the constructor MUST BE OF LENGTH 1 OR GREATER!! Otherwise, an
 * IllegalArgumentException will be thrown.
 * 
 * @author Jacob Nazarenko
 *
 */
public class DataInterpolator {
	
	private TwoDArrayQuickSorter quickSorter;
	private double[][] dataArray;
	private static final double TOLERANCE = 0.001;
	
	public DataInterpolator(double[][] array) {
		dataArray = array;
		quickSorter = new TwoDArrayQuickSorter();
	}
	
	/**
	 * This method can find a value based on piecewise linear functions generated
	 * from the data points entered. 
	 * 
	 * @param value
	 * 			The value whose y-coordinate you would like to return
	 * @return 
	 * 			The value's y-coordinate on the corresponding piecewise linear function
	 * @throws
	 * 			NullPointerException
	 * 			IllegalArgumentException
	 */

    public double interpolateValue(double value) throws NullPointerException, IllegalArgumentException {
    	if (dataArray == null) {
			throw new NullPointerException();
		} else if (dataArray.length == 0) {
			throw new IllegalArgumentException();
		} else if (dataArray.length == 1) { // defaults to the only y-coordinate entered if array has only 1 element
			return dataArray[0][1];
		}
    	quickSorter.quickSort(dataArray, 0); // sort array by first value in each inner array
    	if (value <= dataArray[0][0]) {  // uses lowest two points if value is below range of array[i][0] values
    		double slope = (dataArray[1][1] - dataArray[0][1])/(dataArray[1][0] - dataArray[0][0]);
			double intercept = (dataArray[1][1] - (dataArray[1][0]*slope));
			return (slope*value) + intercept;
    	}
    	else if (value >= dataArray[dataArray.length-1][0]) { // uses lowest two points if value is below range of array[i][0] values
    		double slope = (dataArray[dataArray.length-1][1] - dataArray[dataArray.length-2][1])/(dataArray[dataArray.length-1][0] - dataArray[dataArray.length-2][0]);
			double intercept = (dataArray[dataArray.length-1][1] - (dataArray[dataArray.length-1][0]*slope));
			return (slope*value) + intercept;
    	}
    	else {  // otherwise uses a range within the values entered
    		for (int i = 0; i < dataArray.length; i++) { 
        		if (dataArray[i][0] > value) {
        			double slope = (dataArray[i][1] - dataArray[i-1][1])/(dataArray[i][0] - dataArray[i-1][0]);
        			double intercept = (dataArray[i][1] - (dataArray[i][0]*slope));
        			return (slope*value) + intercept;
        		}
        	}
        	return 0.0; // impossible case
    	}
    }
    
    public static double getTolerance() {
		return TOLERANCE;
	}
}
