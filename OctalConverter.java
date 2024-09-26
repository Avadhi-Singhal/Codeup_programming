/***
 * This file contains program that converts octal number to binary or
 * decimal or hexadecimal as per user requirement.
 * 
 *   Owner : Avadhi-Singhal
 *   
 *   Date of Creation : 25/09/2024
 */
import java.util.Scanner;

public class OctalConverter {
	
	static Constant constant = new Constant();

    // Function to check if the input string is a valid octal string
	public static boolean isOctal(String input) {
        String[] parts = input.split("\\."); 
        for (String part : parts) {
            for (char ch : part.toCharArray()) {
                if (ch < '0' || ch > '7') return false; 
            }
        }
        return true;
    }

	//This function converts octal number to binary number
    public static double octalDecimal(String octal) {
        double decimal = 0;
        String[] parts = octal.split("\\."); 

        for (int i = 0; i < parts[0].length(); i++) {
            int currentValue = parts[0].charAt(parts[0].length() - 1 - i) - '0'; 
            decimal += currentValue * power(8, i); 
        }
        
        if (parts.length > 1) {
            for (int i = 0; i < parts[1].length(); i++) {
                int currentValue = parts[1].charAt(i) - '0'; 
                decimal += currentValue * power(8, -(i + 1)); 
            }
        }
        return decimal;
    }

    // Function to manually compute power (base^exponent) without using Math.pow
    public static double power(int base, int exponent) {
        double result = 1;
        for (int i = 0; i < Math.abs(exponent); i++) {
            result *= base;
        }
        return exponent < 0 ? 1 / result : result;
    }

    // Function to convert decimal to binary (whole and fractional part)
    public static String decimalBinary(double decimal) {
        if (decimal == 0) return constant.ZERO; // Handle zero case
        StringBuilder binary = new StringBuilder();
        int wholePart = (int) decimal;
        double fractionalPart = decimal - wholePart;

        while (wholePart > 0) {
            binary.insert(0, (wholePart % 2));
            wholePart /= 2;
        }
        
        if (fractionalPart > 0) {
            binary.append('.'); 
            while (fractionalPart > 0) {
                fractionalPart *= 2;
                int bit = (int) fractionalPart;
                binary.append(bit);
                fractionalPart -= bit;
                
                if (binary.length() > 32) break;
            }
        }

        return binary.toString();
    }

    // Function to convert decimal to hexadecimal (including fractional part)
    public static String decimalHexadecimal(double decimal) {
        if (decimal == 0) return "0"; 
        StringBuilder hex = new StringBuilder();
        int wholePart = (int) decimal;
        double fractionalPart = decimal - wholePart;
        char[] hexDigits = constant.HEXDIGITS.toCharArray();

        while (wholePart > 0) {
            hex.insert(0, hexDigits[wholePart % 16]);
            wholePart /= 16;
        }

        if (fractionalPart > 0) {
            hex.append('.'); 
            while (fractionalPart > 0) {
                fractionalPart *= 16;
                int digit = (int) fractionalPart;
                hex.append(hexDigits[digit]);
                fractionalPart -= digit;

                if (hex.length() > 32) break;
            }
        }

        return hex.toString();
    }

    // Function to handle user input and conversion selection with try-catch blocks
    public static void convert(Scanner scanner) {
        char continueProgram = 'y';
        while (continueProgram == 'y' || continueProgram == 'Y') {
            try {
                System.out.print(constant.ENTER_OCTAL_NUMBER);
                String input = scanner.nextLine();

                if (!isOctal(input)) {
                    System.out.println(constant.PROMPT_VALID_OCTAL);
                    continue; 
                }

                double decimalResult = octalDecimal(input);

                System.out.println(constant.TOBASE_OCTAL);

                int choice = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (choice) {
                    case 1:
                        String binaryResult = decimalBinary(decimalResult);
                        System.out.println(constant.BINARY_NUMBER + binaryResult);
                        break;
                    case 2:
                        System.out.println(constant.DECIMAL_VALUE + decimalResult);
                        break;
                    case 3:
                        String hexResult = decimalHexadecimal(decimalResult);
                        System.out.println(constant.HEXADECIMAL_VALUE + hexResult);
                        break;
                    default:
                        System.out.println(constant.INVALID_CHOICE);
                }
            } catch (Exception e) {
                System.out.println(constant.ERROR_MSG + e.getMessage());
                scanner.nextLine(); 
            }

            try {
                System.out.print(constant.PROMPT_ANOTHER_OCTAL);
                continueProgram = scanner.nextLine().charAt(0);
            } catch (Exception e) {
                System.out.println(constant.INVALID_INPUT);
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        convert(scanner);
        scanner.close();
    }
}
