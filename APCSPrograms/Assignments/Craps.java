import java.util.Scanner;

public class Craps
{

	public static void main(String[] args)
	{
		Die die1 = new Die();
		Die die2 = new Die();
		
		int wins = 0;
		int losses = 0;
		double winPercent = 0;
		
		int firstRollTotal = 0, otherRollTotal = 0;
		String response = "";
	
		Scanner scanObject = new Scanner(System.in); // represents an object that takes input from the keyboard
	
		System.out.println("Welcome to craps.");
		
			do
			{
				die1.roll();
				die2.roll();
				firstRollTotal = die1.getFaceValue() + die2.getFaceValue();
				System.out.println("Your first roll was a " + firstRollTotal);
				
				if(firstRollTotal == 7 || firstRollTotal == 11)
				{
					System.out.println("You win.");
					System.out.println("Would you like to play again?");
					response = scanObject.next();
				}	
				else
				{
					do	//changed to do while loop so it runs at least once and gets a new
						//otherRollTotal
					{
						die1.roll();
						die2.roll();
						otherRollTotal = die1.getFaceValue() + die2.getFaceValue();
						System.out.println("You rolled a " + otherRollTotal);
					}while(otherRollTotal != 7 && otherRollTotal != firstRollTotal);
						
					if(otherRollTotal == firstRollTotal)
					{
						wins++;
						System.out.println("You win.");
						System.out.println("Would you like to play again? Enter y for yes");
						response = scanObject.next();
					}
					else
					{
						losses++;
						System.out.println("You lost.");
						System.out.println("Would you like to play again? Enter y for yes");
						response = scanObject.next();
					}
				
				}
			} while (response.equals("y"));
			
			winPercent = Math.round(100 * ((double)wins/(wins + losses)));
			
			System.out.println("You won " + wins + " games.");
			System.out.println("Win Percentage: " + 	winPercent + "%");
	}
}