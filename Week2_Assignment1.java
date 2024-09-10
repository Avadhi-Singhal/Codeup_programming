/***
 * Below program performs the following operations:
 * 1.Valid Parentheses Combination Generator
 * 2.Digit Sum Loop
 * 3.Consecutive Number Summer
 * 4.Caesar Cipher with Shift Variability
 * 5.Encoding Challenge with ASCII Conversion
 * 
 * This program runs automatically once executed, and the user have the 
 * option to switch between tasks and execute them as needed.
 * 
 * Owner: Avadhi-Singhal
 * 
 * Date of Creation: 10-09-2924
 * 
 */


import java.util.Scanner;

public class Week2_Assignment1 {

	static Constant constant = new Constant();
	
    
	
	public static void validParenthesesCombinationGenerator(Scanner scanner) {
    	
    	System.out.print(constant.ENTER_STRING);
        String input = scanner.nextLine();
    	
    	 String[] outputArray = new String[100];
         int count = 0;

         if (input.isEmpty() || input.length() == 1 || input.equals(" ")){
             System.out.print(input);
         }
         else {
             for (int i = 0; i < input.length(); i++) {
                 outputArray[count] = String.valueOf(input.charAt(i));
                 count++;
             }

             for (int i = 0; i < input.length(); i++) {
                 for (int j = 0; j < input.length(); j++) {

                     if (i != j) {
                         String combination = String.valueOf(input.charAt(i)) + String.valueOf(input.charAt(j));
                         outputArray[count] = combination;
                         count++;
                     }

                 }
             }
             
             boolean found = false;
             for (int k = 0; k < count; k++) {
                 if (outputArray[k].equals(input)) {
                     found = true;
                     break;
                 }
             }
             if (!found){
             outputArray[count] = String.valueOf(input);
             count++;
             }

             for (int i = 0; i < count; i++) {
                 System.out.print(outputArray[i]);
                 if (i < count - 1) {
                     System.out.print(", ");
                 }
             }
         }
         
         System.out.println();
     }


    
    
	public static void digitSumLoop(Scanner scanner) {
    	System.out.println(constant.NUMBER);
        String input = scanner.nextLine();
        int num;
        try {
            num = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(constant.INVALID_INPUT);
            return;
        }

        if (num < 0) {
            System.out.println(constant.INVALID_INT_INPUT);
            return;
        }

        int result = num;
        while (result >= 10) {
            int sum = 0;
            int tempNum = result;

            while (tempNum > 0) {
                sum += tempNum % 10;
                tempNum /= 10;
            }

            result = sum;
        }

        System.out.println(constant.RESULT + result);
        System.out.println();
    }

    
    
    
	public static void consecutiveNumberSummer(Scanner scanner) {
    	System.out.println(constant.STRING_INT);
        String input = scanner.nextLine();
        int num;
        try {
            num = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(constant.INVALID_INPUT);
            return;
        }

        if (num <= 0) {
            System.out.println(constant.INVALID_INT_INPUT);
            return;
        }

        boolean found = false;
        for (int start = 1; start < num; start++) {
            int sum = 0;
            int current = start;
            while (sum < num) {
                sum += current;
                
                if (sum == num) {
                    for (int i = start; i <= current; i++) {
                        System.out.print(i);
                        if (i < current) {
                            System.out.print(" + ");
                        }
                    }
                    System.out.println(); 
                    found = true;
                    break;
                }
                
                current++;
            }
        }

        if (found) {
            System.out.println(constant.COMBINATION);
        } else {
            System.out.println(constant.NO_COMBINATION);
        }
        System.out.println();
    }

    
    
    
	public static void caesarCipherWithShiftPattern(Scanner scanner) {
	    System.out.println(constant.ENTER_STRING);
	    String input = scanner.nextLine();

	    System.out.println(constant.NUM_OF_SHIFT);
	    int numShifts = scanner.nextInt();
	    int[] shiftPattern = new int[numShifts];

	    System.out.println(constant.SHIFT_VALUES);
	    for (int i = 0; i < numShifts; i++) {
	        shiftPattern[i] = scanner.nextInt();
	    }

	    StringBuilder encryptedText = new StringBuilder();
	    int patternLength = shiftPattern.length;
	    int patternIndex = 0;
	    for (int i = 0; i < input.length(); i++) {
	        char ch = input.charAt(i);
	        int shift = shiftPattern[patternIndex % patternLength];
	        
	        if (Character.isLetter(ch)) {
	            if (ch >= 'A' && ch <= 'Z') {
	                char newChar = (char) (((ch - 'A' + shift) % 26 + 26) % 26 + 'A');
	                encryptedText.append(newChar);
	            } else if (ch >= 'a' && ch <= 'z') {
	                char newChar = (char) (((ch - 'a' + shift) % 26 + 26) % 26 + 'a');
	                encryptedText.append(newChar);
	            }
	            patternIndex++;
	        } else if (Character.isDigit(ch)) {
	            char newChar = (char) (((ch - '0' + shift) % 10 + 10) % 10 + '0');
	            encryptedText.append(newChar);
	            patternIndex++;
	        } else {
	            encryptedText.append(ch);
	        }
	    }

	    System.out.println(constant.ENCRYPTED_TEXT + encryptedText.toString());
	    System.out.println();
	}


    
    
    
	public static void processEncoding(Scanner scanner) {

        System.out.print(constant.ARRAY_DIGITS);
        String[] inputDigits = scanner.nextLine().split("\\s+");
        int[] digitsArray = new int[inputDigits.length];
        for (int i = 0; i < inputDigits.length; i++) {
            digitsArray[i] = Integer.parseInt(inputDigits[i]);
        }

        System.out.println(constant.SERIES_INPUT);
        String[] inputSeries = scanner.nextLine().split("\\s+");
        int[] series = new int[inputSeries.length];
        for (int i = 0; i < inputSeries.length; i++) {
            series[i] = Integer.parseInt(inputSeries[i]) - 1;
        }

        bubbleSortDescending(digitsArray);
        
        StringBuilder result = new StringBuilder();
        for (int index : series) {
            if (index >= 0 && index < digitsArray.length) {
                result.append(digitsArray[index] + 48);
            } else {
                System.out.println(constant.INVALID_INDEX + (index + 1));
                scanner.close();
                return;
            }
        }
        
        System.out.println(constant.ENCODED_RESULT + result.toString());

    }

    public static void bubbleSortDescending(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] < array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        System.out.println();
    }
    
    

    
    public static void operation(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                validParenthesesCombinationGenerator (scanner);
                break;
            case 2:
                digitSumLoop (scanner);
                break;
            case 3:
                consecutiveNumberSummer (scanner);
                break;
            case 4:
                caesarCipherWithShiftPattern (scanner);
                break;
            case 5:
                processEncoding (scanner);
                break;
            default:
                break;
        }
    }

    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println(constant.CHOOSE_OPERATION);
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 6) {
                System.out.println(constant.EXIT);
                break;
            } else {
                operation(choice, scanner);
            }
        }

        scanner.close();
    }
}
