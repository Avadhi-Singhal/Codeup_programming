/***
 * Number System Program
 * 
 * This file contains program that converts decimal number to binary, hexadecimal
 * and octal number.
 * 
 * Owner : Avadhi-Singhal
 * 
 * Date of Creation : 26/09/2024
 */

import java.util.Scanner;

public class DecimalConverter {
	
	static Constant constant = new Constant();

    // Function to convert the integer part of a decimal to binary
    public static String decimalBinary(int decimal) {
        if (decimal == 0) return constant.ZERO;
        String binary = constant.EMPTY_STRING;
        while (decimal > 0) {
            binary = (decimal % 2) + binary;
            decimal /= 2;
        }
        return binary;
    }

    // Function to convert the fractional part of a decimal to binary
    public static String fractionalBinary(double fraction) {
        String binary = constant.EMPTY_STRING;
        int precision = 10;  
        while (fraction > 0 && precision-- > 0) {
            fraction *= 2;
            if (fraction >= 1) {
                binary = binary + "1";
                fraction -= 1;
            } else {
                binary = binary + "0";
            }
        }
        return binary;
    }

    // Function to convert the integer part of a decimal to octal
    public static String decimalOctal(int decimal) {
        if (decimal == 0) return "0";
        String octal = constant.EMPTY_STRING;
        while (decimal > 0) {
            octal = (decimal % 8) + octal;
            decimal /= 8;
        }
        return octal;
    }

    // Function to convert the fractional part of a decimal to octal
    public static String fractionalOctal(double fraction) {
        StringBuilder octal = new StringBuilder();
        int precision = 10;  
        while (fraction > 0 && precision-- > 0) {
            fraction *= 8;
            int octalDigit = (int) fraction;
            octal.append(octalDigit);
            fraction -= octalDigit;
        }
        return octal.toString();
    }

    // Function to convert the integer part of a decimal to hexadecimal
    public static String decimalHexadecimal(int decimal) {
        if (decimal == 0) return constant.ZERO;
        String hex = "";
        char[] hexDigits = constant.HEXDIGITS.toCharArray();
        while (decimal > 0) {
            hex = hexDigits[decimal % 16] + hex;
            decimal /= 16;
        }
        return hex;
    }

    // Function to convert the fractional part of a decimal to hexadecimal
    public static String fractionalHexadecimal(double fraction) {
        StringBuilder hex = new StringBuilder();
        char[] hexDigits = constant.HEXDIGITS.toCharArray();
        int precision = 10;  
        while (fraction > 0 && precision-- > 0) {
            fraction *= 16;
            int hexDigit = (int) fraction;
            hex.append(hexDigits[hexDigit]);
            fraction -= hexDigit;
        }
        return hex.toString();
    }

    // Function to handle user input and conversion selection with try-catch blocks
    public static void convert(Scanner scanner) {
        char continueProgram = 'y';
        while (continueProgram == 'y' || continueProgram == 'Y') {
            try {
                System.out.print(constant.ENTER_DECIMAL_NUMBER);
                String input = scanner.nextLine();

                if (!isDecimal(input)) {
                    System.out.println(constant.PROMPT_VALID_DECIMAL);
                    continue; 
                }
                
                int decimalPointIndex = input.indexOf('.');
                int integerPart = decimalPointIndex == -1 ? Integer.parseInt(input) : Integer.parseInt(input.substring(0, decimalPointIndex));
                double fractionalPart = decimalPointIndex == -1 ? 0.0 : Double.parseDouble("0." + input.substring(decimalPointIndex + 1));

                System.out.println(constant.TOBASE_DECIMAL);
               
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        String binaryResult = decimalBinary(integerPart);
                        String binaryFractionResult = fractionalPart > 0 ? fractionalBinary(fractionalPart) : constant.EMPTY_STRING;
                        System.out.println(constant.BINARY_NUMBER + binaryResult + (binaryFractionResult.isEmpty() ? constant.EMPTY_STRING : "." + binaryFractionResult));
                        break;
                    case 2:
                        String octalResult = decimalOctal(integerPart);
                        String octalFractionResult = fractionalPart > 0 ? fractionalOctal(fractionalPart) : constant.EMPTY_STRING;
                        System.out.println(constant.OCTAL_VALUE + octalResult + (octalFractionResult.isEmpty() ? constant.EMPTY_STRING : "." + octalFractionResult));
                        break;
                    case 3:
                        String hexResult = decimalHexadecimal(integerPart);
                        String hexFractionResult = fractionalPart > 0 ? fractionalHexadecimal(fractionalPart) : constant.EMPTY_STRING;
                        System.out.println(constant.HEXADECIMAL_VALUE + hexResult + (hexFractionResult.isEmpty() ? constant.EMPTY_STRING : "." + hexFractionResult));
                        break;
                    default:
                        System.out.println(constant.INVALID_CHOICE);
                }
            } catch (Exception e) {
                System.out.println(constant.ERROR_MSG + e.getMessage());
                scanner.nextLine(); 
            }

            try {
                System.out.print(constant.PROMPT_ANOTHER_DECIMAL);
                continueProgram = scanner.nextLine().charAt(0);
            } catch (Exception e) {
                System.out.println(constant.INVALID_INPUT);
                break;
            }
        }
    }

    // Function to validate if the input is a valid decimal number
    public static boolean isDecimal(String input) {
        if (input == null || input.isEmpty()) return false;
        boolean decimalPointFound = false;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '-') {
                if (i != 0) return false; 
            } else if (ch == '.') {
                if (decimalPointFound) return false; 
                decimalPointFound = true;
            } else if (ch < '0' || ch > '9') {
                return false; 
            }
        }
        return true;
    }
}
