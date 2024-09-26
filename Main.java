/***
 * Number System Program 
 * 
 *This file is Main file for Week4 Assignment that displays menu 
 * showing conversions and perform operations.
 * 
 * Owner : Avadhi-Singhal
 * 
 * Date of Creation : 26/09/2024
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Constant constant = new Constant();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(constant.MAIN_MENU);
            int choice = getIntInput(scanner, constant.CHOICE, constant);

            if (choice == 0) {
                System.out.println(constant.EXIT);
                break;
            }

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println(constant.PROMPT_BASE);
                        String base = scanner.nextLine().trim();
                        
                        if (base.equalsIgnoreCase("exit")) {
                            System.out.println(constant.RETURN_PROMPT);
                            break;
                        }
                        switch (base) {
                            case "2":
                                BinaryConverter.convert(scanner);
                                break;
                            case "8":
                                OctalConverter.convert(scanner);
                                break;
                            case "10":
                                DecimalConverter.convert(scanner);
                                break;
                            case "16":
                                HexaDecimalConverter.convert(scanner);
                                break;
                            default:
                                System.out.println(constant.PROMPT_VALID_BASE);
                                break;
                        }
                    }
                    break;

                case 2:
                    while (true) {
                        String operation = Operations.getOperation(scanner);

                        if (operation.equalsIgnoreCase("exit")) {
                            System.out.println(constant.RETURN_PROMPT);
                            break;
                        }

                        double result1 = Operations.getNumber(scanner, "first");

                        double result2 = Operations.getNumber(scanner, "second");

                        String resultBase = Operations.getResultBase(scanner);

                        Operations.performOperation(operation, result1, result2, resultBase, scanner);
                    }
                    break;

                default:
                    System.out.println(constant.INVALID_CHOICE_MENU);
            }
        }

        scanner.close();
    }

    // Helper method to get and validate integer input
    public static int getIntInput(Scanner scanner, String prompt, Constant constant) {
        int input = -1;
        while (true) {
            try {
                System.out.println(prompt);
                input = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println(constant.INVALID_NUMBER);
            }
        }
        return input;
    }
}
