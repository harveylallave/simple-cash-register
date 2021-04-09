
import java.util.Scanner;

public class Driver {

	
	private int[] denominations = {0, 0, 0, 0, 0, 0};
	
	public static void main(String[] args) {

		String input;
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			input = sc.nextLine().toUpperCase();
			
			if(input.equals("QUIT"))
				break;
			
			String[] strArr = input.split(" ");
			 
			if(strArr[0].equals("show") && strArr.length == 1)
				displayDenomination();

			else if(strArr[0].equals("put") && strArr.length == 6)
				putDenomination(strArr);

			else if(strArr[0].equals("take") && strArr.length == 6)
				takeDenomination(strArr);

			else if(strArr[0].equals("change") && strArr.length == 2)
				getChange(strArr);
			
			else invalidInput();
		}
	}

	private static void displayDenomination() {
		// TODO Auto-generated method stub
		
	}

	private static void putDenomination(String[] strArr) {
		// TODO Auto-generated method stub
		
	}

	private static void takeDenomination(String[] strArr) {
		// TODO Auto-generated method stub
		
	}

	private static void getChange(String[] strArr) {
		// TODO Auto-generated method stub
		
	}

	private static void invalidInput() {
		System.out.println("Please enter a valid input.\n");
	}

}
