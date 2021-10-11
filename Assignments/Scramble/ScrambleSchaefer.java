import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class ScrambleSchaefer
{
	public static void main(String[] args)
	{
		try{
		
			//declare variables and such
			Scanner keyboard = new Scanner(System.in);
		
			Scanner reader = new Scanner(new File("words.txt"));
			PrintWriter writer = new PrintWriter(new File("scrambledWords.txt"));
		
			//get the wordcount of the file
			int wordCount = countWords();
			
			//create an array with a spot for each words and put them in there
			//System.out.println("*** " + wordCount + " Words ***");
			String[] wordsArray = new String[wordCount];
			reader = new Scanner(new File("words.txt"));
		
			//fill the array
			for(int i = 0; i < wordCount; i++){
				wordsArray[i] = reader.next();
			}
		
			//scramble the words
			String[] scrambledWords = scramble(wordsArray, wordCount);
			
			//output the words and write them to a new file
			for(int i = 0; i < wordCount; i++){
					writer.print(scrambledWords[i] + " ");
					//System.out.println("*** " + scrambledWords[i] + " ***");
			}
			writer.close();
		
			System.out.println("Words successfully scrambled");
		
			}catch(Exception e){
				e.printStackTrace();
		}
	}
	
	
	
	//count the words in the file
	private static int countWords(){
	
		int wc = 0;
		try{
			Scanner counter = new Scanner(new File("words.txt"));
			while(counter.hasNext()){
				wc++;
				counter.next();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return wc;
	}
	
	
	
	//scramble the words and put them in a new array
	private static String[] scramble(String[] sWords, int wc){
		
		Random rand = new Random();
		
		String tempString = "";
		int firstRandomIndex = 0;
		int secondRandomIndex = 0;
		
		for(int i = 0; i < (wc * 10); i++){
			firstRandomIndex = rand.nextInt(wc);
			tempString = sWords[firstRandomIndex];
			secondRandomIndex = rand.nextInt(wc);
			sWords[firstRandomIndex] = sWords[secondRandomIndex];
			sWords[secondRandomIndex] = tempString;
		}
		return sWords;
		
	}
}