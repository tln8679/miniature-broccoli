import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
* @author Taylor Noble
*/
public class Matrix {
	private double [][] data;
	private int numRows;
	private int numColumns;

	/**
	 * 
	 * @param data: data is an array of arrays which is a matrix. Each array inside the array represents a row in a matrix.
	 */
	public Matrix(double[][] data) {
		this.data = data;
		numRows = data.length;
		numColumns = data[0].length;
	}

	/**
	 * 
	 * @param filename: takes in a file that has one row of doubles per line. Columns are delimited by spaces.
	 */
	public Matrix(String filename) {
		numRows = 0;
		numColumns = 0;

		File inFile = new File(filename);
		Scanner s = null;
		try {
			FileInputStream fis = new FileInputStream(inFile);
			s = new Scanner(fis);		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (s.hasNext()){	// Need to figure out the dimensions of the matrix so the array of arrays can be instantiated 
			String[] tokens = s.nextLine().split(" ");
			numRows += 1;
			numColumns = tokens.length;
		}

		data = new double[numRows][numColumns]; // new matrix dimensions

		File inFile2 = new File(filename); // Running through the file again to create the matrix
		Scanner s2 = null;
		try {
			FileInputStream fis = new FileInputStream(inFile2);
			s2 = new Scanner(fis);		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (s2.hasNext()){
			for(int i = 0; i<numRows; i++) {
				String[] tokens2 = s2.nextLine().split(" "); // each time the row changes we need to read in a new line
				for(int j = 0;j < numColumns; j++) {
					data[i][j] = Double.parseDouble(tokens2[j]);  
				}
			}
		}
	}

	@Override
	/**
	 * returns the dimensions of the matrix followed by the matrix
	 */
	public String toString() {
		String result = "";
		for(int i = 0; i < numRows; i++){
			if(i>0)
				result += "\n"; // print a new line once the end of a row has been reached
			for(int j = 0; j<data[i].length; j++)
				result += data[i][j] + " ";
		}
		return numRows + "x" + numColumns + "\n" + result + "\n";

	}
	/**
	 * 
	 * @param other: A second matrix that is being asked if it is equal to the original matrix
	 * @return True if they are equal. False otherwise.
	 */
	public boolean equals(Matrix other) {
		if(numRows != other.numRows || numColumns != other.numColumns) // dimensions need to be equal
			return false;
		else {
			for(int i = 0; i < numRows; i++) {
				for(int j = 0; j < numColumns; j++) {
					if(data[i][j] != other.data[i][j]) // iterates through each element
						return false;
				}
			}
		}return true;
	}

	@Override
	/**
	 * overrides the equals method for all type Object. Produces the same result as my other method, but this one compares 
	 * against all objects
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matrix other = (Matrix) obj;
		if (!Arrays.deepEquals(data, other.data))
			return false;
		return true;
	}
	/**
	 * 
	 * @return returns the transpose of the matrix called upon. Rows are switched with columns, mXn to nxm
	 */
	public Matrix transpose() {
		double [][] new_data = new double [numColumns][numRows];
		for(int i = 0; i < numColumns; i++){
			for(int j = 0; j<numRows; j++) {
				new_data[i][j] = data[j][i];		
			}
		}
		return new Matrix(new_data);
	}

	/**
	 * 
	 * @return number of rows
	 */
	public int getNumRows() {
		return numRows;
	}

	/**
	 * 
	 * @return the number of columns
	 */
	public int getNumColumns() {
		return numColumns;
	}

	/**
	 * 
	 * @param other: matrix that we are adding
	 * @return a new matrix that is the sum of this and other
	 */
	public Matrix add(Matrix other) {
		if (numRows == other.numRows & numColumns == other.numColumns) {
			double [][] new_data = new double [numRows][numColumns];
			for(int i = 0; i < numRows; i++){
				for(int j = 0; j < data[i].length; j++) {
					// System.out.println(i + "   " + j);
					new_data[i][j] = data[i][j] +other.data[i][j];		
				}
			}
			return new Matrix(new_data);
		}
		else {
			System.out.println("Not comformable to addition: ");
			return null;
		}
	}
	
	/**
	 * 
	 * @param d: a scalar number
	 * @return a new matrix that is a scaled multiple of the first matrix
	 */
	public Matrix mult(double d) {
		double [][] new_data = new double [numRows][numColumns];
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < data[i].length; j++) {
				new_data[i][j] += d * data[i][j];
			}}
		return new Matrix(new_data);

	}
	
	/**
	 * 
	 * @param other: given an mxn matrices, other is a nxb matrices
	 * @return a mxb matrices that is the matrix product of this and other
	 */
	public Matrix mult(Matrix other) {
		if(numColumns != other.numRows) {
			System.out.println("These matrices are not conformable to addition");
			return null;
		}
		double [][] new_data = new double [numRows][other.numColumns];
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < other.numColumns; j++) {
				for(int k = 0; k < other.numRows; k++) {
					new_data[i][j] += data[i][k] * other.data[k][j];
				}
			}
		}
		return new Matrix(new_data);

	}

}
