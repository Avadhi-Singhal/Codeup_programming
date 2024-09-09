import java.util.Scanner;

public class Week2_Assignment1 {

    public static void validParenthesesCombinationGenerator() {

        System.out.println("Valid Parentheses Combination Generator is not implemented yet.");
    }

    public static void digitSumLoop(String input) {
        int num;
        try {
            num = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return;
        }

        if (num < 0) {
            System.out.println("Invalid Input. Please Enter Valid Input");
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

        System.out.println("The Result is: " + result);
    }

    public static void consecutiveNumberSummer(String input) {
        int num;
        try {
            num = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return;
        }

        if (num <= 0) {
            System.out.println("Invalid input. Please enter a positive integer.");
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
            System.out.println("Combinations found.");
        } else {
            System.out.println("No combinations found.");
        }
    }

    public static void caesarCipherWithShiftPattern(String input, Scanner scanner) {
        System.out.print("Enter number of shifts in the pattern: ");
        int numShifts = scanner.nextInt();
        int[] shiftPattern = new int[numShifts];
        
        System.out.println("Enter shift values for the pattern: ");
        for (int i = 0; i < numShifts; i++) {
            shiftPattern[i] = scanner.nextInt();
        }

        StringBuilder encryptedText = new StringBuilder();
        int patternLength = shiftPattern.length;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int shift = shiftPattern[i % patternLength];
            
            if (ch >= 'A' && ch <= 'Z') {
                char newChar = (char) (((ch - 'A' + shift) % 26 + 26) % 26 + 'A');
                encryptedText.append(newChar);
            } else if (ch >= 'a' && ch <= 'z') {
                char newChar = (char) (((ch - 'a' + shift) % 26 + 26) % 26 + 'a');
                encryptedText.append(newChar);
            } else {
                encryptedText.append(ch);
            }
        }

        System.out.println("Encrypted text: " + encryptedText.toString());
    }

    public static void processEncoding() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the array of digits (space-separated): ");
        String[] inputDigits = scanner.nextLine().split("\\s+");
        int[] digitsArray = new int[inputDigits.length];
        for (int i = 0; i < inputDigits.length; i++) {
            digitsArray[i] = Integer.parseInt(inputDigits[i]);
        }

        System.out.print("Enter the series of indices (space-separated): ");
        String[] inputSeries = scanner.nextLine().split("\\s+");
        int[] series = new int[inputSeries.length];
        for (int i = 0; i < inputSeries.length; i++) {
            series[i] = Integer.parseInt(inputSeries[i]) - 1;
        }

        bubbleSortDescending(digitsArray);
        
        StringBuilder result = new StringBuilder();
        for (int index : series) {
            if (index >= 0 && index < digitsArray.length) {
                result.append((char) (digitsArray[index] + 48));
            } else {
                System.out.println("Invalid index: " + (index + 1));
                scanner.close();
                return;
            }
        }
        
        System.out.println("Encoded result: " + result.toString());

        scanner.close();
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
    }

    public static void operation(int choice, String input, Scanner scanner) {
        switch (choice) {
            case 1:
                validParenthesesCombinationGenerator();
                break;
            case 2:
                digitSumLoop(input);
                break;
            case 3:
                consecutiveNumberSummer(input);
                break;
            case 4:
                caesarCipherWithShiftPattern(input, scanner);
                break;
            case 5:
                processEncoding();
                break;
            default:
                System.out.println("Invalid option. Please choose a number between 1 and 5.");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter String: ");
        String input = scanner.nextLine();
        
        while (true) {
            System.out.println("Choose Operation:\n1. Valid Parentheses Combination Generator\n"
                + "2. Digit Sum Loop\n3. Consecutive Number Summer\n4. Caesar Cipher with Shift Pattern\n"
                + "5. Encoding Challenge with ASCII Conversion\n6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 6) {
                System.out.println("Exiting...");
                break;
            } else {
                operation(choice, input, scanner);
            }
        }

        scanner.close();
    }
}
