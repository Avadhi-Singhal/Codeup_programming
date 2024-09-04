/***
 * reverse()
● Description: Reverses the current string.
Example:
String text = "Java";
● String reversedText = reverse(); // Result: "avaJ"

Owner = Avadhi-Singhal

Date of Creation: 04-09-2024
 */

import java.util.Scanner;

public class ReverseString {
	public static void reversed() {
		
		Scanner user_input = new Scanner(System.in);
		
		System.out.println("Enter String to be reversed: ");
		String input = user_input.nextLine();
		
		user_input.close();
		
        char[] reverseString = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            reverseString[i] = input.charAt(input.length() - 1 - i);
        }

        System.out.println("Reversed String is: " + new String(reverseString) );
    }
	public static void main(String[] args) {
		reversed();
	}
}
