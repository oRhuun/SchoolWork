

public class ArrayTester {
	public static void main(String[] args){
	
		//filling an array using and initializer unit
		//String[] array1 = {"Monday", "Tuesday", "Wednesday", "Thursday"};
		
		/*searching the array for an item
			usually done on a data structure (array, arraylist, tree, heap, hashtable, etc.)
			on average you do n/2 searchers (n = arraysize)
			Linear - Big "O" notation - order of magnitude
				a linear search runs in O(n) "time"
			
			Quadratic - in a sorted array, do a binary search - O(logn)
				logbase2n searches
			Sorting - O(n^2)
		*/
		
		/*String day = "Friday";
		int index = -1;
		for(int i = 0; i < array1.length; i++) {
			if(array1[i].equals(day)){
				index = i;
				break;
			}
			
		}*/
		
		/*
		int[] numbers = {0,4,7,10,11,13,15,20,31,35,36,40,43,50,54,60,97,108,200,301};
		int numberToFind = 108;
		int index = findNum(numbers, numberToFind);
		System.out.println(index);
		*/
		
		int[] numbersToSort = 
		/*
		Bubble sort
		for(int i = 0; i < a.length; i++)
			for(int j = 0; j < a.length-1-i;j++)
				Compare and switch
				
				
		Selection Sort
		for(int i = 0;i < a.length;i++)
			for(int j = i; j < a.length;j++)
				Find index of the smallest, move it to i
				
		
		Bubble, Selection, and Insertion sort are O(n^2)
			to get a better sort you have to use recursion or have prior knowledge
		
		
		
		
	}
	
	/*public static int findNum(int[] n, int nToFind){
		int found = 0;
		int index = (n.length/2) - 1;
		while(found == 0){
			if(n[index] == nToFind){
				found = 1;
			}
			else if(n[index] < nToFind){
				index = (index + (index/2)) - 1;
			}
			else{
				index = (index/2) - 1;
			}
		}	
		return index;
	}
	*/
	
}