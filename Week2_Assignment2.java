import java.math.BigInteger;
import java.util.Scanner;

public class Week2_Assignment2 {
	
	static Constant constant = new Constant();
	
	//This method counts the number of palindrome in input string 
	public static void countPalindrome() {
		System.out.println(constant.NOT_IMPLEMENTED);
	}

	
	//This method prints the Nth Fibonacci Series Number.
	private static BigInteger nthIntegerHelper(int n, BigInteger a, BigInteger b) {
        if (n == 0) {
            return a;
        }
        if (n == 1) {
            return b;
        }
        return nthIntegerHelper(n - 1, b, a.add(b));
    }

   
    private static BigInteger nthInteger(int input) {
        return nthIntegerHelper(input, BigInteger.ZERO, BigInteger.ONE);
    }

    public static void printNthFibonacci(Scanner scanner) {
        System.out.println(constant.POSITIVE_INPUT);
        String input = scanner.nextLine();
        int integerInput;
        try {
            integerInput = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(constant.INVALID_INPUT);
            return;
        }
        if (integerInput < 0) {
            System.out.println(constant.POSITIVE_INPUT);
        } else {
            BigInteger result = nthInteger(integerInput);
            System.out.println(integerInput + constant.FIBONACCI_NUMBER + result);
        }
    }
    
    
    // This method converts snake case to camel case.
    public static void snakeCamelCase() {
    	System.out.println(constant.NOT_IMPLEMENTED);
    }
    
    
    //This method counts the number of consonant in given string. 
    public static void countConsonantCall(Scanner scanner) {
        System.out.println(constant.ENTER_STRING);
        String input = scanner.nextLine();
        
        if (input == null || input.isEmpty()) {
            System.out.println(constant.NULL_STRING);
            return;
        }

        int consonantCount = countConsonants(input, 0); 
        System.out.println(constant.CONSONANT_NUMBER + consonantCount);
    }

    private static int countConsonants(String input, int index) {
        if (index >= input.length()) {
            return 0;
        }

        char currentChar = input.charAt(index);

        boolean isConsonant = (currentChar >= 'a' && currentChar <= 'z' || currentChar >= 'A' && currentChar <= 'Z') 
                              && !(currentChar == 'a' || currentChar == 'e' || currentChar == 'i' || currentChar == 'o' || currentChar == 'u' 
                              || currentChar == 'A' || currentChar == 'E' || currentChar == 'I' || currentChar == 'O' || currentChar == 'U');

        int countForCurrentChar = isConsonant ? 1 : 0;
        return countForCurrentChar + countConsonants(input, index + 1);
    }
    
    
    //This method converts binary number to decimal number
    private static boolean isBinaryString(String input, int index) {
        if (index >= input.length()) {
            return true; 
        }
        char currentchar = input.charAt(index);
        if (currentchar == '0' || currentchar == '1') {
            return isBinaryString(input, index + 1);
        } else {
            return false; 
        }
    }

    private static int decimalValue(String input, int index, int length) {
        if (index >= input.length()) {
            return 0;
        }
        int currentChar = input.charAt(index) - '0';
        int factor = power(2, length - index - 1);
        int decimalValue = currentChar * factor;
        return decimalValue + decimalValue(input, index + 1, length);
    }
    
    private static int power(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        } else {
            return base * power(base, exponent - 1);
        }
    }

    private static void processInput(Scanner scanner) {
        
        String input;
        boolean validInput = false;

        while (!validInput) {
            System.out.println(constant.BINARY_Number);
            input = scanner.nextLine();

            if (isBinaryString(input, 0)) {
                validInput = true;
                int decimal = decimalValue(input, 0, input.length());
                System.out.println(constant.DECIMAL_VALUE + input + constant.CONJUNCTION + decimal);
            } else {
                System.out.println(constant.INVALID_STRING);
            }
        }
    }

	
    
    //This method calls various functions according to user choice.
	public static void operationCall (int choice, Scanner scanner) {
		switch (choice) {
		case 1:
			countPalindrome();
			break;
		case 2:
			printNthFibonacci(scanner);
			break;
		case 3:
			snakeCamelCase();
		    break;
		case 4:
			countConsonantCall(scanner);
			break;
		case 5:
			processInput(scanner);
			break;
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(constant.CHOOSE_OPTION);
		int choice = scanner.nextInt();
		scanner.nextLine();
		while(true) {
			if(choice == 6) {
				System.out.println("Exiting...");
				break;
			}else {
				operationCall(choice, scanner);
			}
		}
	}

}

