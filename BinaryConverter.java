/***
 * Number System Program
 * 
 * This file contains program which converts binary number to octal number or 
 * decimal number or hexadecimal number as per user requirement.
 * 
 * Owner : Avadhi-Singhal
 * 
 * Date of Creation : 25/09/2024
 */
import java.util.Scanner;

public class BinaryConverter {
	
	static Constant constant = new Constant();
	
    // Function to check if the input string is a valid binary string
	
    public static boolean isBinary(String input) {
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch != '0' && ch != '1' && ch != '.') return false;
        }
        return true;
    }

    // Function to manually compute power (base^exponent) 
    public static int power(int base, int exponent) {
        int result = 1;
        while (exponent > 0) {
            result *= base;
            exponent--;
        }
        return result;
    }

    // Function to convert binary to decimal 
    public static double binaryDecimal(String binary, boolean isFraction) {
        double result = 0;
        for (int i = 0; i < binary.length(); i++) {
            int currentValue = binary.charAt(i) - '0';
            if (isFraction) {
                result += currentValue * (1.0 / power(2, i + 1));
            } else {
                result += currentValue * power(2, binary.length() - i - 1);
            }
        }
        return result;
    }

    // Function to convert binary integer part to octal 
    public static String binaryOctal(String binary) {
        int decimal = binaryDecimalInt(binary);
        String octal = "";
        while (decimal > 0) {
            octal = (decimal % 8) + octal;
            decimal /= 8;
        }
        return octal.equals("") ? constant.ZERO : octal;
    }
    
    //This method handles fractional part of input binary string and convert it to octal 
    public static String binaryFractionOctal(String binaryFraction) {
        double fraction = binaryDecimal(binaryFraction, true); 
        String octalFraction = "";
        while (fraction > 0 && octalFraction.length() < 10) { 
            fraction *= 8;
            int octalDigit = (int) fraction;
            octalFraction = octalFraction + octalDigit;
            fraction -= octalDigit;
        }
        return octalFraction;
    }

    
    //This method convert integer part of binary string to octal
    public static String binaryHexadecimal(String binary) {
        int decimal = binaryDecimalInt(binary);
        String hex = "";
        char[] hexDigits = constant.HEXDIGITS.toCharArray();
        while (decimal > 0) {
            hex = hexDigits[decimal % 16] + hex;
            decimal /= 16;
        }
        return hex.equals("") ? "0" : hex;
    }

    //This method converts fractional part of binary string to hexa decimal
    public static String binaryFractionHex(String binaryFraction) {
        double fraction = binaryDecimal(binaryFraction, true); 
        StringBuilder hexFraction = new StringBuilder();
        char[] hexDigits = constant.HEXDIGITS.toCharArray();
        while (fraction > 0 && hexFraction.length() < 10) { 
            fraction *= 16;
            int hexDigit = (int) fraction;
            hexFraction.append(hexDigits[hexDigit]);
            fraction -= hexDigit;
        }
        return hexFraction.toString();
    }

    // Function to convert binary to decimal integer value
    public static int binaryDecimalInt(String binary) {
        int result = 0;
        for (int i = 0; i < binary.length(); i++) {
            int currentValue = binary.charAt(i) - '0';
            result += currentValue * power(2, binary.length() - i - 1);
        }
        return result;
    }

    //this method show menu for binary conversions
    public static void convert(Scanner scanner) {
        char continueProgram = 'y';
        while (continueProgram == 'y' || continueProgram == 'Y') {
        	//-> which exception you are using 
            try {
                String input;
                do {
                    System.out.println(constant.ENTER_BINARY_NUMBER);
                    input = scanner.nextLine();
                    if (!isBinary(input)) {
                        System.out.println(constant.VALID_BINARY_NUMBER);
                    }
                } while (!isBinary(input));

                System.out.println(constant.TOBASE_BINARY);

                int choice = scanner.nextInt();
                scanner.nextLine();  
                
                int dotIndex = input.indexOf('.');

                switch (choice) {
                    case 1:
                        double decimalResult = dotIndex != -1
                            ? binaryDecimal(input.substring(0, dotIndex), false) + binaryDecimal(input.substring(dotIndex + 1), true)
                            : binaryDecimal(input, false);
                        System.out.println(constant.DECIMAL_VALUE + decimalResult);
                        break;
                    case 2:
                        String octalIntegerPart = binaryOctal(dotIndex != -1 ? input.substring(0, dotIndex) : input);
                        String octalFractionPart = dotIndex != -1 ? binaryFractionOctal(input.substring(dotIndex + 1)) : "";
                        System.out.println(constant.OCTAL_VALUE + octalIntegerPart + (octalFractionPart.isEmpty() ? "" : "." + octalFractionPart));
                        break;
                    case 3:
                        String hexIntegerPart = binaryHexadecimal(dotIndex != -1 ? input.substring(0, dotIndex) : input);
                        String hexFractionPart = dotIndex != -1 ? binaryFractionHex(input.substring(dotIndex + 1)) : "";
                        System.out.println(constant.HEXADECIMAL_VALUE+ hexIntegerPart + (hexFractionPart.isEmpty() ? "" : "." + hexFractionPart));
                        break;
                    default:
                        System.out.println(constant.INVALID_CHOICE);
                }
            } catch (Exception e) {
                System.out.println(constant.ERROR_MSG + e.getMessage());
                scanner.nextLine(); 
            }

            try {
                System.out.print(constant.PROMPT_ANOTHER_BINARY);
                continueProgram = scanner.nextLine().charAt(0);
            } catch (Exception e) {
                System.out.println(constant.INVALID_INPUT);
                break;
            }
        }
    }
}
