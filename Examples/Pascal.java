import java.util.Scanner;

public class Pascal
{

	public static void main(String[] args)
	{
	
		Scanner inputObject = new Scanner(System.in);
		
		System.out.println("What row of Pasal's triangle would you like to see?");
		
		int rowNumber = inputObject.nextInt();
		
		int[] finalArray = new int[rowNumber];
		
		finalArray = calculateNumbers(rowNumber);
		
		for(int i = 0; i < finalArray.length; i++)
			System.out.print(finalArray[i] + " ");
			
		System.out.println();
	
	}
	
	public static int[] calculateNumbers(int n) {
	
		int[] pasArray = new int[n + 1];
		
		if(n == 1) {
			pasArray[0] = 1;
			pasArray[1] = 1;
		}	
		else {
			int[] temp = new int[n + 1];
			temp = calculateNumbers(n - 1);
			
			for(int j = 0; j < temp.length; j++)
				pasArray[j] = temp[j];
			
			pasArray[n] = 1;
			
			for(int k = 0; k < temp.length - 1; k++){
				pasArray[k + 1] = temp[k] + temp[k + 1];
			}
			
		}
		return pasArray;
		
	}
		
}
