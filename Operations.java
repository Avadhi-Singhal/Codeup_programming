/***
 * Number Converter Program
 * 
 * This file contains program that perform mathematical operation on 
 * numbers of various bases and show result in user required base.
 * 
 *  Owner : Avadhi-Singhal
 *  
 *  Date of Creation : 26/09/2024
 */

import java.util.Scanner;

public class Operations {

    static Constant constant = new Constant();

    // This method performs the desired operation on numbers
    public static void performOperation(String operation, double result1, double result2, String resultBase, Scanner scanner) {
        double result;
        switch (operation) {
            case "1":
                result = result1 + result2;
                break;
            case "2":
                result = result1 - result2;  
                break;
            case "3":
                result = result1 * result2;
                break;
            case "4":
                if (result2 != 0) {
                    result = result1 / result2;
                } else {
                    System.out.println(constant.DIVISION_BY_ZERO);
                    return;
                }
                break;
            default:
                System.out.println(constant.INVALID_OPTION);
                return;
        }

        String convertedResult;
        if (result < 0 && resultBase.equals("2") || resultBase.equals("8") || resultBase.equals("16")) {
            System.out.println(constant.NEGATIVE_NOT_SUPPORTED);
            return;
        }
        
        switch (resultBase) {
            case "2":
                convertedResult = DecimalConverter.decimalBinary((int) result); 
                break;
            case "8":
                convertedResult = DecimalConverter.decimalOctal((int) result); 
                break;
            case "10":
                convertedResult = String.valueOf(result); 
                break;
            case "16":
                convertedResult = DecimalConverter.decimalHexadecimal((int) result); 
                break;
            default:
                System.out.println(constant.INVALID_BASE);
                return;
        }

        System.out.println(constant.RESULT + convertedResult);
    }

    //This method is used to get operation which user wants to perform 
    public static String getOperation(Scanner scanner) {
        String operation;
        while (true) {
            System.out.println(constant.CHOOSE_OPERATION);
            operation = scanner.nextLine();
            if (operation.matches("[1-4]|exit")) {
                break; 
            } else {
                System.out.println(constant.INVALID_OPERATION);
            }
        }
        return operation;
    }

    public static double getNumber(Scanner scanner, String order) {
        double result = Double.NaN;

        while (true) {
            System.out.println(constant.ENTER_BASE_OF + order + constant.NUMBER_PROMPT);
            String base = scanner.nextLine();
            System.out.println(constant.CHOOSE + order + constant.NUMBER);
            String input = scanner.nextLine();

            try {
                switch (base) {
                    case "2":
                        if (!BinaryConverter.isBinary(input)) {
                            System.out.println(constant.INVALID_BINARY);
                            continue; 
                        }
                        result = BinaryConverter.binaryDecimal(input, false);
                        break;
                    case "8":
                        if (!OctalConverter.isOctal(input)) {
                            System.out.println(constant.INVALID_OCTAL);
                            continue; 
                        }
                        result = OctalConverter.octalDecimal(input);
                        break;
                    case "10":
                        try {
                            result = Double.parseDouble(input);
                        } catch (NumberFormatException e) {
                            System.out.println(constant.INVALID_DECIMAL);
                            continue; 
                        }
                        break;
                    case "16":
                        if (!HexaDecimalConverter.isHexadecimal(input)) {
                            System.out.println(constant.INVALID_HEXADECIMAL);
                            continue; 
                        }
                        result = HexaDecimalConverter.hexadecimalDecimal(input);
                        break;
                    default:
                        System.out.println(constant.INVALID_BASE2);
                        continue; 
                }
                break; 
            } catch (Exception e) {
                System.out.println(constant.ERROR_MSG + e.getMessage());
            }
        }

        return result;
    }

    
    public static String getResultBase(Scanner scanner) {
        String resultBase;
        while (true) {
            System.out.println(constant.ENTER_RESULT_BASE);
            resultBase = scanner.nextLine();
            if (resultBase.matches("(2|8|10|16)")) {
                break; 
            } else {
                System.out.println(constant.INVALID_BASE);
            }
        }
        return resultBase;
    }
}

