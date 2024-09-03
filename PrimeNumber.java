/***
 * Write a Java program to determine if a given integer is a prime number.
Example :
● Input: 21
● Output: "The given number is NOT prime"
● Explanation:
The number 21 is divisible by 3 and 7, hence it is not a prime number.

Constraints:
● The input will be a positive integer between 1 and 10^6.

Owner: Avadhi-Singhal

Date of Creation: 02-09-2024
 */

package myproject;

import java.util.Scanner;

public class PrimeNumber {

    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true; 
        
        if (number % 2 == 0 || number % 3 == 0) return false;
        
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }
       
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter numbers to check if they are prime. Type 'done' to finish:");

        while (true) {
            System.out.print("Enter a number or type 'done' to finish: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("done")) {
                break;
            }

            try {
                int number = Integer.parseInt(input);
                if (isPrime(number)) {
                    System.out.println(number + " is a prime number.");
                } else {
                    System.out.println(number + " is not a prime number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer or type 'done' to finish.");
            }
        }

        scanner.close();
    }
}
