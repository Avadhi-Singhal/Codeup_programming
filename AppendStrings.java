/***
 * append(String newString)
● Description: Appends the newString provided by the user to the existing string. The
method should remember the appended result.

Example:
String currentString = "Hello";
append(" World"); // Result: "Hello World"
append(" Java"); // Result: "Hello World Java"

Owner = Avadhi-Singhal

Date of Creation : 04-09-2024
 */

import java.util.Scanner;

public class AppendStrings {

    public static void append() {
        Scanner userInput = null;

        try {
            userInput = new Scanner(System.in);
            
            System.out.println("Enter the first string: ");
            String input = userInput.nextLine();

            while (true) {
                System.out.println("Type 'yes' if you want to append a string; otherwise type 'no': ");
                String decision = userInput.nextLine();

                if ("yes".equalsIgnoreCase(decision)) {
                    System.out.println("Enter the string you want to append:");
                    String nextInput = userInput.nextLine();
                    input += nextInput;
                    System.out.println("Appended String is: " + input);
                } else if ("no".equalsIgnoreCase(decision)) {
                    break;
                } else {
                    System.out.println("Invalid input. Please type 'yes' or 'no'.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            if (userInput != null) {
                userInput.close();
            }
        }
    }

    public static void main(String[] args) {
        append();
    }
}
