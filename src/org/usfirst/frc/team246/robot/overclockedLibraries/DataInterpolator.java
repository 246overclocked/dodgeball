package org.usfirst.frc.team246.robot.overclockedLibraries;

/**
 * This is a generic class for the interpolation of data stored in a 2D array. 
 * The 2D data array is a parameter of this object's constructor. 
 * 
 * @author Jacob Nazarenko
 *
 */
public class DataInterpolator {
	
	private TwoDArrayQuickSorter quickSorter;
	private double[][] dataArray;
	
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
	 */

    public double interpolateValue(double value) {
    	if ((dataArray.length == 0 || dataArray.length == 1) || dataArray == null) {
			throw new NullPointerException();
		}
    	quickSorter.quickSort(dataArray, 0); // sort array by distance
    	if (value <= dataArray[0][0]) {
    		double slope = (dataArray[1][1] - dataArray[0][1])/(dataArray[1][0] - dataArray[0][0]);
			double intercept = (dataArray[1][1] - (dataArray[1][0]*slope));
			return (slope*value) + intercept;
    	}
    	else if (value >= dataArray[dataArray.length-1][0]) {
    		double slope = (dataArray[dataArray.length-1][1] - dataArray[dataArray.length-2][1])/(dataArray[dataArray.length-1][0] - dataArray[dataArray.length-2][0]);
			double intercept = (dataArray[dataArray.length-1][1] - (dataArray[dataArray.length-1][0]*slope));
			return (slope*value) + intercept;
    	}
    	else {
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
}
