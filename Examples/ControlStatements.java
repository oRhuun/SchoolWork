import java.util.Scanner;

public class ControlStatements	{
	public static void main(String[] args) {
	
		Scanner keyboard = new Scanner(System.in);
		String responseString = "";
		int responseInt = 0;
	
		System.out.println("Please enter an integer");
		responseInt = keyboard.nextInt();
	
		int n1 = 4;
	
		if(responseInt == 4) {
			System.out.println("Please enter your name");
			responseString = keyboard.next();
		
			if(responseString.equals("Tom"))
				System.out.println("You Suck!");
			else
				System.out.println("You must be better than Tom!");
		}
		else
			System.out.println("It's not 4");
			
		
		
//		--------------------------
		
		String myString;
	
		switch(responseInt){
			case 4:		myString = "It's 4!";
						break;
							
			case 8:		myString = "It's 8!";
						break;
			
			case 12:	myString = "It's 12!";
						break;
						
			default:	myString = "It's not 4, 8, or 12!";
						break;
		}
	
		System.out.println(myString);
	
//		It's time to get loopy
		
		for(int i = 0; i < 10; i++)	{
			System.out.println("i is " + i);	
		}
		
		for(int i = 5; i < 12; i += 3)	{
			System.out.println("i is " + i);	
		}
		
		
		for(int i = 5; (i < 10 && i > 8); i += 3)	{
			System.out.println("i is " + i);	
		}
		
		int i = 0;
		for( ; ; )	{
			System.out.println("i is " + i);
			if(i > 10)
				break;
			i++;
		}
		
		
		int j = 0;
		while(j < 10)	{
			System.out.println("j is " + j);
			j++;
		}
		
		int k = 0;
		do{
			System.out.println("k is " + k);
			k++;
		}while( k < 10);
		
		
		System.out.println("What is your address");
		responseString = keyboard.next();
		Scanner keyboard2 = new Scanner(responseString);
		String outputString = "";
		
		while(keyboard2.hasNext())	{
		
			String s1 = keyboard2.next(); //retrieves next token
			outputString += s1.charAt(3);
		}
		
		System.out.println(outputString);
		
	}
}