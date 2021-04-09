
import java.util.Scanner;

public class Driver {

	
	private int[] denominations = {0, 0, 0, 0, 0, 0};
	
	public static void main(String[] args) {

		System.out.println(isNum("123"));
		System.out.println(isNum("123.2"));
		System.out.println(isNum("ab"));
		System.out.println(isNum("1a2"));
		System.out.println(isNum("12-3"));
		String input;
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			input = sc.nextLine().toUpperCase();
			
			if(input.equals("QUIT"))
				break;
			
			String[] strArr = input.split(" ");
			 
			if(strArr[0].equals("show") && validateInput(strArr, 1))
				displayDenomination();

			else if(strArr[0].equals("put") && validateInput(strArr, 6))
				putDenomination(strArr);

			else if(strArr[0].equals("take") && validateInput(strArr, 6))
				takeDenomination(strArr);

			else if(strArr[0].equals("change") && validateInput(strArr, 2))
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
	
	private static boolean validateInput(String[] strArr, int length) {
		if(length != strArr.length)
			return false;
		
		else if(length > 1)
			for(int i = 1; i < length; i++)
				if(!isNum(strArr[i]))
					return false;
		
		return true;
	}

	private static boolean isNum(String string) {
		return string.matches("\\d+");
	}
}
