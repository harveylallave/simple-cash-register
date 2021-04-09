
import java.util.Scanner;

public class Driver {


	private static final int[] DENOMINATION_VALUE = {20, 10, 5, 2, 1};
	private static final int   DENOMINATION_SIZE  = 5;
	
	private static int[] denomination = {0, 0, 0, 0, 0}; 
	
	public static void main(String[] args) {
 
		String input;
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			input = sc.nextLine().toLowerCase();
			
			if(input.equals("quit"))
				break;
			
			String[] strArr = input.split(" ");
			 
			if(strArr[0].equals("show") && validateInput(strArr, 1))
				displayDenomination();

			else if(strArr[0].equals("put") && validateInput(strArr, DENOMINATION_SIZE + 1))
				updateDenomination(strArr, true);

			else if(strArr[0].equals("take") && validateInput(strArr, DENOMINATION_SIZE + 1))
				updateDenomination(strArr, false);

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

	private static void updateDenomination(String[] strArr, boolean add) {
		boolean valid = true;
		if(add)
			 for(int i = 1; i < DENOMINATION_SIZE + 1; i++)
				 denomination[i - 1] += Integer.parseInt(strArr[i]);
		else 
			for(int i = 1; i < DENOMINATION_SIZE + 1; i++)
				if(Integer.parseInt(strArr[i]) > denomination[i - 1]) {
					valid = false;
					System.out.println("Sorry, not enough change.");
					break;
				} else denomination[i - 1] -= Integer.parseInt(strArr[i]);
		
		if(valid) displayDenomination();
	}
 
	// Make higher bills more priority
	private static void getChange(String[] strArr) {
		int[] changeDenomination = new int[DENOMINATION_SIZE];
		int toChange = Integer.parseInt(strArr[1]);
		int total = getTotalAmount();
		
		if(toChange > total)
			System.out.println("Sorry, not enough change.");
		else {
			for(int i = 0; i < DENOMINATION_SIZE; i++)
				if(toChange > DENOMINATION_VALUE[i] && 
						denomination[i] > 0) {
					int div = toChange / DENOMINATION_VALUE[i];

					if(denomination[i] < div)
						div = denomination[i];
					 
					while(!checkDenominationPathValid(toChange, div, i) && div != 0)
						div -= 1;
						
					if(div != 0) {
						toChange -= DENOMINATION_VALUE[i] * div;
						denomination[i] -= div;
						
						changeDenomination[i] = div;
					} 		 
				} 
 
			if(toChange != 0) 
				System.out.println("Sorry, not enough change.");
			else {
				for(int i = 0; i < DENOMINATION_SIZE; i++)
					System.out.print((i == 0 ? "" : " ") + changeDenomination[i]);
				System.out.println();
			}
				
		}
	}

	// Check bottom-up
	private static boolean checkDenominationPathValid(int toChange, int div, int ctr) {
		
		toChange -= DENOMINATION_VALUE[ctr] * div;
		
		if(ctr < DENOMINATION_SIZE)
			for(int i = DENOMINATION_SIZE - 1; i > ctr; i--)  
				if(toChange > DENOMINATION_VALUE[i] &&  denomination[i] > 0) {
					int newDiv = toChange / DENOMINATION_VALUE[i];

					if(denomination[i] < newDiv)
						newDiv = denomination[i];
					
					toChange -= DENOMINATION_VALUE[i] * newDiv;
				} 
		return toChange == 0;
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
