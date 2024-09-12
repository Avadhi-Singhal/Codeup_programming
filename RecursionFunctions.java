/***
 * Below program converts the following functions:
 * 1. Count Palindrome in string.
 * 2.To get Nth Fibonacci number.
 * 3.To get text in camel case.
 * 4. To get number of consonat in string.
 * 5. To get decimal number from binary number
 * 
 * Below code dinot use any loop or inbuils function in implementation of logic. All 
 * logics are build on recursion only.
 * 
 *  Owner: Avadhi-Singhal
 *  
 *  Date of creation: 12-09-2024
 */

import java.math.BigInteger;
import java.util.Scanner;

public class RecursionFunctions {
	
	static Constant constant = new Constant();
	
	//This method counts the number of palindrome in input string 
// Function to check if a substring is a palindrome
    private static boolean isPalindrome(String str, int start, int end) {
        if (start >= end) {
            return true;
        }
        if (str.charAt(start) != str.charAt(end)) {
            return false;
        }
        return isPalindrome(str, start + 1, end - 1);
    }
// Function to check if a substring was already counted
    private static boolean wasCounted(String str, String palindrome, int lastEnd) {
        int index = str.indexOf(palindrome);
        while (index != -1 && index <= lastEnd) {
            index = str.indexOf(palindrome, index + 1);
        }
        return index != -1;
    }
// Function to count unique palindromic substrings
    private static void findUniquePalindromes(Scanner scanner) {
    	 System.out.print(constant.ENTER_STRING);
         String input = scanner.nextLine();
        int len = input.length();
        if (len == 0) {
            return;
        }
        int count = 0;
        String previousPalindrome = "";
        int lastEnd = -1;

        for (int start = 0; start < len; start++) {
            for (int end = start; end < len; end++) {
                if (isPalindrome(input, start, end)) {
                    String palindrome = input.substring(start, end + 1);
                    if (!palindrome.equals(previousPalindrome) || !wasCounted(input, palindrome, lastEnd)) {
                        count++;
                        previousPalindrome = palindrome;
                        lastEnd = end;
                    }
                }
            }
        }

        int totalPalindromes = count;
        System.out.println(constant.UNIQUE_PALINDROMES + totalPalindromes);
        System.out.println();
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
        System.out.println(constant.ENTER_integer);
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
        System.out.println();
    }
    
    
    
    // This method converts snake case to camel case.
    private static void processString(Scanner scanner) {
        System.out.println(constant.CHECK_CONVERT);
        String input = scanner.nextLine();
        input = removeLeadingSpecialCharacters(input);
        if (input.isEmpty()) {
            System.out.println(constant.EMPTY_STRING);
            return;
        }

        boolean isSnakeCase = isSnakeCaseHelper(input, 0);
        String snakeCaseString = isSnakeCase ? input : convertToSnakeCase(input);
        String result = convertToCamelCase(snakeCaseString);
        
        System.out.println(constant.CAMELCASE_RESULT + result);
        System.out.println();
    }

    // Method to remove leading special characters and numbers (excluding underscore)
    private static String removeLeadingSpecialCharacters(String input) {
        int index = 0;
        while (index < input.length() && 
              !(Character.isLetter(input.charAt(index)) || input.charAt(index) == '_')) {
            index++;
        }
        return input.substring(index);
    }

    private static boolean isSnakeCaseHelper(String input, int index) {
        if (index >= input.length()) {
            return true; 
        }

        char currentChar = input.charAt(index);
        if (!(currentChar == '_' || (currentChar >= 'a' && currentChar <= 'z') || (currentChar >= '0' && currentChar <= '9'))) {
            return false; 
        }

        return isSnakeCaseHelper(input, index + 1); 
    }
//convert string to snake case
    private static String convertToSnakeCase(String input) {
        return toSnakeCaseHelper(input, 0, false);
    }

    private static String toSnakeCaseHelper(String input, int index, boolean lastWasSeparator) {
        if (index >= input.length()) {
            return "";
        }
        
        char currentChar = input.charAt(index);
        String result = "";
        boolean isSeparator = (currentChar == ' ' || currentChar == '-' || currentChar == '/' ||
                               currentChar == '\\' || currentChar == '.' || currentChar == '+' || 
                               currentChar == '=');

        if (currentChar >= 'A' && currentChar <= 'Z') {
            result += (index > 0 && !lastWasSeparator ? "_" : "") + (char)(currentChar + ('a' - 'A'));
            lastWasSeparator = false;
        } else if (isSeparator) {
            if (lastWasSeparator) {
                result = "";
            } else {
                result += "_";
                lastWasSeparator = true;
            }
        } else {
            result += currentChar;
            lastWasSeparator = false;
        }

        return result + toSnakeCaseHelper(input, index + 1, lastWasSeparator);
    }
//convert string to camel case
    private static String convertToCamelCase(String input) {
        return convertToCamelCaseHelper(input, 0, false);
    }

    private static String convertToCamelCaseHelper(String input, int index, boolean capitalizeNext) {
        if (index >= input.length()) {
            return "";
        }

        char currentChar = input.charAt(index);
        String result;

        if (currentChar == '_') {
            result = convertToCamelCaseHelper(input, index + 1, true);
        } else {
            if (capitalizeNext) {
                result = Character.toUpperCase(currentChar) + convertToCamelCaseHelper(input, index + 1, false);
            } else {
                result = Character.toLowerCase(currentChar) + convertToCamelCaseHelper(input, index + 1, false);
            }
        }

        return result;
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
        System.out.println();
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
    
    
    
  // Below functions calculates the decimal value of a binary number 
 // Function to check if the input string is a binary string
    private static boolean isBinaryString(String input, int index) {
        if (index >= input.length()) {
            return true; 
        }
        char ch = input.charAt(index);
        if (ch == '0' || ch == '1' || ch == '.') {
            return isBinaryString(input, index + 1);
        } else {
            return false; 
        }
    }

    // Function to calculate power using recursion
    private static int power(int base, int exponent) {
        if (exponent == 0) {
            return 1; 
        }
        return base * power(base, exponent - 1); 
    }

    // Function to convert the binary integer part to decimal
    private static int binaryToDecimal(String binary, int index) {
        if (index < 0) {
            return 0; // base case
        }
        char currentChar = binary.charAt(index);
        int currentValue = currentChar - '0'; 
        int factor = power(2, binary.length() - index - 1); 
        return currentValue * factor + binaryToDecimal(binary, index - 1); 
    }

    // Function to convert the binary fractional part to decimal
    private static double binaryFractionToDecimal(String binary, int index) {
        if (index >= binary.length()) {
            return 0.0; 
        }
        char currentChar = binary.charAt(index);
        int currentValue = currentChar - '0'; 
        double factor = 1.0 / power(2, index + 1); 
        return currentValue * factor + binaryFractionToDecimal(binary, index + 1); 
    }

    // Main function to process input and display the result
    private static void processInput(Scanner scanner) {
        char continueProgram = 'y';

        while (continueProgram == 'y' || continueProgram == 'Y') {
            String input = "";
            boolean validInput = false;

            while (!validInput) {
                System.out.println(constant.BINARY_Number);
                input = scanner.nextLine();
                validInput = isBinaryString(input, 0);
                if (!validInput) {
                    System.out.println(constant.INVALID_STRING);
                }
            }

            // Call the recursive function to convert binary to decimal
            int decimalResult = 0;
            double fractionalResult = 0.0;
            int dotIndex = input.indexOf('.');

            if (dotIndex != -1) {
                decimalResult = binaryToDecimal(input.substring(0, dotIndex), dotIndex - 1);
                fractionalResult = binaryFractionToDecimal(input.substring(dotIndex + 1), 0);
            } else {
                decimalResult = binaryToDecimal(input, input.length() - 1);
            }

            System.out.println(constant.DECIMAL_VALUE + input + constant.CONJUNCTION + (decimalResult + fractionalResult));

            System.out.print(constant.Y_OR_N);
            continueProgram = scanner.nextLine().charAt(0);
        }
    }

	
    
    //This method calls various functions according to user choice.
	public static void operationCall (int choice, Scanner scanner) {
		switch (choice) {
		case 1:
			findUniquePalindromes(scanner);
			break;
		case 2:
			printNthFibonacci(scanner);
			break;
		case 3:
			processString(scanner);
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
				System.out.println(constant.EXITING_MESSAGE);
				break;
			}
			else {
				operationCall(choice, scanner);
                System.out.println(constant.CHOOSE_OPTION);
                choice = scanner.nextInt();
                scanner.nextLine();
			}
		}
	}

}

