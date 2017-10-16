/**
 * A class to test Matrix operations
 * @author taylorn
 *
 */
//MAKE NO CHANGES TO THE CODE IN THIS FILE
public class TestMatrix {

	private static int testCount = 0;
	
	public static void main(String[] args) {
		double [][] data = new double[][]{ {0.5,1,0}, {1,0.5,0}, {0,0.5,1.25}, {0.5,0.5,0.75}};
		Matrix m3 = new Matrix(data);
		System.out.println("Matrix m3 is a:");
		System.out.println(m3);
		Matrix m3b = new Matrix(data.clone());

		data = new double[][]{ {1,1,0}, {1,0,1}, {0,0,1}};
		Matrix m4 = new Matrix(data);
		System.out.println("Matrix m4 is a:");
		System.out.println(m4);
		System.out.println(m3.getNumRows()+ " " + m4.getNumColumns());

		printTest("equals",m3.equals(m3b), true);
		
		printTest("equals",m3.equals(m4), false);
		
		Matrix m3t = m3.transpose();
		System.out.println("Matrix m3t is a:");
		System.out.println(m3t);
		
		Matrix m9 = new Matrix(new double[][]{{0.5,1.0,0.0,0.5},{1.0,0.5,0.5,0.5},{0.0,0.0,1.25,0.75}});
		printTest("transpose",m3t.equals(m9), true);
		
		Matrix m1 = new Matrix("Resources/m1.txt");
		System.out.println("Matrix m1 is a:");
		System.out.println(m1);
		
		Matrix m2 = new Matrix("Resources/m2.txt");
		System.out.println("Matrix m2 is a:");
		System.out.println(m2);
		
		printTest("file", m1.equals(m2), false); 
			
		data = new double[][]{ {-1,0,0,-1}, {0,2,0,2}, {2,-1,0,2}};
		Matrix m5 = new Matrix(data);
		
		Matrix m6 = m1.add(m1);
		System.out.println("Matrix m6 is a:");
		System.out.println(m6);
		
		Matrix m7 = new Matrix(new double[][] { {2.5,1.0,1.0,2.5}, {1.0,2.5,1.0,3}, {2.5,3.0,0.5,2.5}});
		printTest("add",m7.equals(m6), true);
		
			
		printTest("mult(double)", m6.equals(m1.mult(2.0)), true);
		
		Matrix m8 = m1.add(m5);
		System.out.println("Matrix m8 is a:");
		System.out.println(m8);
				
		Matrix m10 = new Matrix(new double[][]{ {0.25,0.5,0.5,0.25},{0.5,3.25,0.5,3.5},{3.25,0.5,0.25,3.25}});
		printTest("add", m8.equals(m10), true);
		
		Matrix m11 = m1.mult(m3);
		System.out.println("Matrix m11 is a:");
		System.out.println(m11);
		
		Matrix m12 = new Matrix(new double[][]{{1.75,2.375,1.5625},{2.25,2.125,1.75},{2.75,2.75,1.25}});
		printTest("mult(Matrix)", m11.equals(m12), true);
		
		Matrix m13 = new Matrix(new double[][]{{0.5, 0.25},{0.25, 0.5},{1.25, 0.25},{0.5,0.5}});
		
		Matrix m14 = m3t.mult(m13);
		System.out.println("Matrix m14 is a:");
		System.out.println(m14);
		
		Matrix m15 = new Matrix(new double[][] {{0.75,0.875},{1.5,0.875},{1.9375,0.6875}});
		printTest("mult(Matrix)",m14.equals(m15), true);
		 
	}

	

	private static void printTest(String s, boolean actual, boolean expected) {
		// TODO Auto-generated method stub
		testCount++;
		System.out.println("\tTesting "+s);
		System.out.print("\tTest "+ testCount+". ");
		if (actual == expected)
			System.out.println("Pass");
		else
			System.out.println("Fail");
		
		System.out.println();
	}

}


	


