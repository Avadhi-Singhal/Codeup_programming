/***
 * This file contains program that converts hexadecimal number
 * to binary, decimal and octal according to user requirement.
 * 
 * Owner : Avadhi-Singhal
 * 
 * Date of Creation : 26/09/2024
 */
import java.util.Scanner;

public class HexaDecimalConverter {
	
	static Constant constant = new Constant();

    public static boolean isHexadecimal(String input) {
        String[] parts = input.split("\\."); 
        for (String part : parts) {
            for (int i = 0; i < part.length(); i++) {
                char ch = part.charAt(i);
                if (!((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'F'))) {
                    return false; 
                }
            }
        }
        return true;
    }

    // Function to convert hexadecimal to decimal, including fractional parts
    public static double hexadecimalDecimal(String hex) {
        double decimal = 0;
        String[] parts = hex.split("\\."); 

        for (int i = 0; i < parts[0].length(); i++) {
            char currentChar = parts[0].charAt(parts[0].length() - 1 - i);
            int currentValue = (currentChar >= 'A') ? (currentChar - 'A' + 10) : (currentChar - '0'); 
            decimal += currentValue * power(16, i); 
        }

        if (parts.length > 1) {
            for (int i = 0; i < parts[1].length(); i++) {
                char currentChar = parts[1].charAt(i);
                int currentValue = (currentChar >= 'A') ? (currentChar - 'A' + 10) : (currentChar - '0'); 
                decimal += currentValue * power(16, -(i + 1)); 
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

    // Function to convert decimal to binary without using built-in functions
    public static String decimalBinary(double decimal) {
        if (decimal == 0) return constant.ZERO; 
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

    // Function to convert decimal to octal without using built-in functions
    public static String decimalOctal(double decimal) {
        if (decimal == 0) return "0"; 
        StringBuilder octal = new StringBuilder();
        int wholePart = (int) decimal;
        double fractionalPart = decimal - wholePart;

        while (wholePart > 0) {
            octal.insert(0, (wholePart % 8));
            wholePart /= 8;
        }
        
        if (fractionalPart > 0) {
            octal.append('.'); 
            while (fractionalPart > 0) {
                fractionalPart *= 8;
                int digit = (int) fractionalPart;
                octal.append(digit);
                fractionalPart -= digit;

                if (octal.length() > 32) break;
            }
        }

        return octal.toString();
    }

    // Function to handle user input and conversion selection with try-catch blocks
    public static void convert(Scanner scanner) {
        char continueProgram = 'y';
        while (continueProgram == 'y' || continueProgram == 'Y') {
            try {
                System.out.println(constant.ENTER_HEXADECIMAL_NUMBER);
                String input = scanner.nextLine();

                if (!isHexadecimal(input)) {
                    System.out.println(constant.PROMT_VALID_HEX);
                    continue; 
                }

                double decimalResult = hexadecimalDecimal(input);

                System.out.println(constant.TOBASE_HEX);

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
                        String octalResult = decimalOctal(decimalResult);
                        System.out.println(constant.OCTAL_VALUE + octalResult);
                        break;
                    default:
                        System.out.println(constant.INVALID_CHOICE);
                }
            } catch (Exception e) {
                System.out.println(constant.ERROR_MSG + e.getMessage());
                scanner.nextLine(); 
            }

            try {
                System.out.println(constant.PROMPT_ANOTHER_HEX);
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
