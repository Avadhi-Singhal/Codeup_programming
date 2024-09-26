/***
 * This file is Main file for Week4 Assignment that displays menu 
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
		System.out.println(constant.MAIN_MENU);
		int choice = scanner.nextInt();
		switch(choice) {
		case 1:
			while(true) {
				System.out.println(constant.PROMPT_BASE);
				String base = scanner.nextLine();
				if(base == "exit") {
					System.out.println(constant.RETURN_PROMPT);
					break;
				}else {
					if(base == "2") {
						BinaryConverter.convert(scanner);
					}else if (base == "8") {
						OctalConverter.convert(scanner);
					}else if(base == "10") {
						DecimalConverter.convert(scanner);
					}else if (base == "16") {
						HexaDecimalConverter.convert(scanner);
					}else {
						System.out.println(constant.PROMPT_VALID_BASE);
					}
				}
			}
		case 2:
			 while (true) {
		            String operation = Operations.getOperation(scanner);
		            if (operation.equals("exit")) {
		                System.out.println(constant.RETURN_PROMPT);
		                break; 
		            }
		            
		            double result1 = Operations.getNumber(scanner, "first");
		            
		            double result2 = Operations.getNumber(scanner, "second");

		            String resultBase = Operations.getResultBase(scanner);

		            Operations.performOperation(operation, result1, result2, resultBase, scanner);
		        }
		}
		scanner.close();
	}
}
