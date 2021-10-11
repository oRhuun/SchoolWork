import java.util.Scanner;

public class BinarySchaefer	{
	public static void main(String[] args)	{
	
		Scanner keyboard = new Scanner(System.in);
		
		String response;
		
		do{
			System.out.println("Type \"dtb\" to convert a decimal value to binary,\n " +
								"or \"btd\" to convert a binary a to decimal value");
			String input = keyboard.next();
			
					if(input.equals("dtb")) {
						System.out.println("Enter the decimal number you'd like" +
											" to convert to a binary number (Max: 65,535)");
						Int d = keyboard.nextInt();
						System.out.println(convertToBinary(d));
					}else if(input.equals("btd"))	{
						System.out.println("Enter the binary number you'd like" +
											" to convert to a decimal value (Max 16 bits));
						String b = keyboard.next();
						System.out.println(convertToDecimal(b));
					/*}else {
						do{
							System.out.println("Please enter a valid response");
							input = keyboard.next();
						}while(!input.equals("dtb") || !input.equals("btd"));
					}*/
			
			System.out.println("Enter \"y\" if you'd like to convert another number");
			response = keyboard.next();
		}while(response.equals("y")); 
		
	}
	
	private static int convertToDecimal(String binaryInput){
		int binaryLength = binaryInput.length();
		//System.out.println(binaryLength);
		int decimal = parseBinary(binaryLength, binaryInput);
		
		return decimal;
	}
	
	private static int parseBinary(int bLength, String bString)	{
		int decimalValue = 0;
		
		for(int i = bLength; i > 0; i--){
		
			int tempDecimal = 0;
			int tempBinary = 0;

			tempBinary = Character.getNumericValue(bString.charAt(i - 1));
				tempDecimal = tempBinary * (int)Math.pow(2, (bLength - i));
			//System.out.println("****" + tempBinary);
			//System.out.println("****" + tempDecimal);
			
			decimalValue += tempDecimal;
		}
		
		return decimalValue;
	}
	
	private static int convertToBinary(int decimalInput){
		String binaryOutput;
		int binaryOuputLength;
		
		for(int i = 16; i > 0; i--){
		
		}
		
		return binaryOutput;
	}
	
}
