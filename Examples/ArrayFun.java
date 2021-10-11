public class ArrayFun{
	public static void main(String[] args){
	
	//array declaration
	int[] myArray = new int[10];
	
	//fill array
	for(int i = 0; i < myArray.length; i++)
		myArray[i] = 2 * i;
	
	//copy that array into a new one
	int[] myArray2 = new int [10];
	for(int j = 0; j < myArray2.length; j++)
		myArray2[j] = myArray[j];	
	
	//use foreach loop to process the array
	for(int value:myArray)
		System.out.println(value);
	
	//finding the max value in the array
	int tempMax = 0;
	for(int value: myArray2){
		if(value > tempMax)
			tempMax = value;
	}
	
	//process array in reverse order
	for(int k = myArray.length - 1; k >= 0; k--)
		System.out.println(myArray[k]);
	
	}
}