
import java.util.Scanner;

public class Driver {


	private static final int[] DENOMINATION_VALUE = {20, 10, 5, 2, 1};
	private static final int   DENOMINATION_SIZE  = 5;
	
	private static int[] denomination = {0, 0, 0, 0, 0}; 
	
	public static void main(String[] args) {
 
		System.out.println("ready");
		
		String input;
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			input = sc.nextLine().toLowerCase();
			
			if(input.equals("quit"))
				break;
			
			String[] strArr = input.split(" ");
			 
			if(strArr[0].equals("show") && validateInput(strArr, 1))
				displayDenomination();

			else if((strArr[0].equals("put") || strArr[0].equals("take")) && 
					 validateInput(strArr, DENOMINATION_SIZE + 1)) {
				
				if(updateDenomination(strArr, strArr[0].equals("put")))
					 displayDenomination();
				else System.out.println("Sorry, not enough change.");
			}
				
			else if(strArr[0].equals("change") && validateInput(strArr, 2))
				getChange(strArr);
			
			else invalidInput();
		}
	}

	private static void displayDenomination() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("$" + getTotalAmount());
		for(int val : denomination)
			sb.append(" " + val);
		
		System.out.println(sb.toString());
	}

	private static int getTotalAmount() {
		int total = 0;
		
		for(int i = 0; i < DENOMINATION_SIZE; i++)
			total += denomination[i] * DENOMINATION_VALUE[i];
		
		return total;
	}

	private static boolean updateDenomination(String[] strArr, boolean add) {
		boolean valid = true;
		if(add)
			 for(int i = 1; i < DENOMINATION_SIZE + 1; i++)
				 denomination[i - 1] += Integer.parseInt(strArr[i]);
		else {
			int[] tempDenomination = new int[DENOMINATION_SIZE];
			
			for(int i = 0; i < DENOMINATION_SIZE; i++)
				tempDenomination[i] = denomination[i];
			 
			for(int i = 1; i < DENOMINATION_SIZE + 1; i++)
				if(Integer.parseInt(strArr[i]) > tempDenomination[i - 1]) {
					valid = false;
					break;
				} else tempDenomination[i - 1] -= Integer.parseInt(strArr[i]);
			
			if(valid) denomination = tempDenomination;
		}
		return valid;
	}
 
	// Higher bills have higher priority
	private static void getChange(String[] strArr) { 
		int toChange = Integer.parseInt(strArr[1]);
		int total = getTotalAmount();
		
		if(toChange > total)
			System.out.println("Sorry, not enough change.");
		else {  
			if(checkDenominationPath(toChange, 0, "") != 0)
				System.out.println("Sorry, not enough change."); 
		}
	}
  
	private static int checkDenominationPath(int toChange, int ctr, String path) {
		 
		if(toChange == 0) {
			for(int i = ctr; i < DENOMINATION_SIZE; i++)
				path += (i == 0 ? "" : " ") + "0"; 
			
			System.out.println(path); 
			updateDenomination(("take " + path).split(" "), false);
			return 0;
		} 
		
		else if(ctr >= DENOMINATION_SIZE) 
			return -1;
		
		else if(denomination[ctr] > 0 && toChange >= DENOMINATION_VALUE[ctr]) {
			int newDiv = toChange / DENOMINATION_VALUE[ctr];
 
			if(denomination[ctr] < newDiv)
				newDiv = denomination[ctr];
			
			int newToChange = toChange - DENOMINATION_VALUE[ctr] * newDiv; 
			do {
				newToChange = checkDenominationPath(toChange - DENOMINATION_VALUE[ctr] * newDiv, ctr + 1, 
													path + (path.isEmpty() ? "" : " ") + newDiv);
				
					newDiv -= 1;
				 
			} while(newDiv != -1 && newToChange != 0);
 			
			return newToChange;
			 
		} else return checkDenominationPath(toChange, ctr + 1, 
											path + (path.isEmpty() ? "" : " ") + "0");
	}
	 
	private static void invalidInput() {
		System.out.println("Please enter a valid input.");
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
