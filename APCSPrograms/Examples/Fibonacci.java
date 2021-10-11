import java.util.Scanner;

public class Fibonacci
{

	public static void main(String[] args)
	{
	
		Scanner inputObject = new Scanner(System.in);
		
		System.out.println("How many numbers of the Fibonacci sequence do you want to see?");
		
		int numberOfItems = inputObject.nextInt();
		
		int[] finalArray = new int[numberOfItems];
		
		finalArray = calculateNumbers(numberOfItems);
		
		for(int i = 0; i < finalArray.length; i++)
			System.out.print(finalArray[i] + " ");
			
		System.out.println();
	
	}
	
	
	public static int[] calculateNumbers(int n)
	{
		int[] fibArray = new int[n];
		
		if (n == 2)
		{
			fibArray[0] = 1;
			fibArray[1] = 1;
		}
		else
		{
			int[] temp = new int[n-1];
			
			temp = calculateNumbers(n-1);
			
			for(int j = 0; j < temp.length; j++)
				fibArray[j] = temp[j];
			
			fibArray[n-1] = temp[n-2] + temp[n-3];
			
		}
		
		return fibArray;
		
	}
	
	
}