

public class ArrayTester2
{

	public static void main(String[] args)
	{
	
		// filling an array using an initializer list 
		String[] array1 = {"Monday", "Tuesday", "Wednesday", "Thursday"};
		
		
		// Search the array for an item
		String day = "Friday";
		int index = -1;
		for(int i = 0; i < array1.length; i++)
		{
			if(array1[i].equals(day))
			{
				index = i;
				break;
			}
		}
		
		
		
		
		// below is binary search on an array
		
		int[] array2 = {2,5,7,9,15,23,45,67,78,92,105};
		
		index = -1; // lets reset the value of index for the next search
		
		int low = 0;
		int high = array1.length - 1;
		int middle = (low + high) / 2;
		
		int target2 = 18; // we can change this to test other values
		
		while(array2[middle] != target2 && low <= high)
		{
			if(target2 < array2[middle])
				high = middle - 1;
			else
				low = middle + 1;
			
			middle = (low + high) / 2;
		}
		
		if(array2[middle] == target2)
			index = middle;
		
		
	
		// working on a two-dimensional array
		// the array below is an array of arrays
		// the array below has three rows and five columns
		// each of the rows is an array with five elements in it
		
		int[][] arrayTwoD = { {2, 4, 6, 8, 10} , {1, 3, 5, 7, 9}, {0, 11, 15, 17, 19} };
	
		
		// we will print out the array below
		
		for(int row = 0; row < arrayTwoD.length; row++)
			for(int column = 0; column < arrayTwoD[row].length; column++)
				System.out.println(arrayTwoD[row][column]);
				
		// notice above that we get the length of each row by using arrayTwoD[row].length
		// What is the algorithmic complexity, (Big O notation), of the code written on the 2D array above?
		//quadratic
		
		//sort, bubble sort, O(n^2)
	
	
	}
	
	
}