

public class NavigateMatrix
{


	public static void main(String[] args)
	{
	
		// with the code below make sure to write the code so that it will work for ANY square matrix, not just the one given below
	
		
		// 5 x 5 matrix filled with integers
		int[][] myArray = { {1,2,3,4,5}, {6,7,8,9,10}, {11,12,13,14,15}, {16,17,18,19,20}, {21,22,23,24,25} };
		
		// print out the second row of the array
		for(int col = 0; col < myArray[1].length; col++) {
			System.out.println(myArray[1][col]);
		}
		
		System.out.println("**********************************************************************************");
		// print out the third column of the array
		for(int row = 0; row < myArray.length; row++) {
					System.out.println(myArray[row][2]);			
		}
		
		System.out.println("**********************************************************************************");
		// print out the matrix column by column instead of row by row
		for(int row = 0; row < myArray.length; row++) {
			for(int col = 0; col < myArray[row].length; col++){
				System.out.println(myArray[col][row]);
			}
		}
		
		System.out.println("**********************************************************************************");
		// print out the diagonal 21, 17, 13, 9, 5
		for(int row = myArray.length - 1, col = 0; row >= 0 && col < myArray[row].length; row--, col++) {
				System.out.println(myArray[row][col]);
		}
		
		System.out.println("**********************************************************************************");
		// print out the diagonal 1, 7, 13, 19, 25
		for(int row = 0, col = 0; row < myArray.length && col < myArray[row].length; row++, col++) {
				System.out.println(myArray[row][col]);
		}
		
		System.out.println("**********************************************************************************");
		// print out every other element in the matrix
		boolean odd = true;
		
		for(int row = 0; row < myArray.length; row++) {
			for(int col = 0; col < myArray[row].length; col++){
				if(odd)
					System.out.println(myArray[row][col]);
					
				odd = !odd;
			}
		}
		
		System.out.println("**********************************************************************************");
		// print out the middle matrix of 9 numbers
		for(int row = 1; row < myArray.length - 1; row++) {
			for(int col = 1; col < myArray[row].length - 1; col++){
				System.out.println(myArray[row][col]);
			}
		}
	
	
	}
}