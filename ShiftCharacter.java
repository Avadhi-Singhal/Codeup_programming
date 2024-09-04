/***
 *shift(int n)
● Description: Moves the first n characters from the start to the end of the current string.
Example:
Input -> abcdefg
shift(3)
output -> efgabcd

Owner : Avadhi-Singhal

Date of Creation : 04-09-2024
 */

import java.util.Scanner;

public class ShiftCharacter {

    public static void shift() {
        Scanner user_input = new Scanner(System.in);

        System.out.println("Enter initial String: ");
        String input = user_input.nextLine();

        System.out.println("Enter number of characters you want to shift from the end:");
        int shiftAmount = user_input.nextInt();

        if (shiftAmount < 0 || shiftAmount > input.length()) {
            System.out.println("Invalid shift amount. It must be between 0 and the length of the string.");
            user_input.close();
            return;
        }

        char[] inputArray = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            inputArray[i] = input.charAt(i);
        }

        char[] result = new char[input.length()];
        
        int shiftStart = input.length() - shiftAmount;
        for (int i = 0; i < shiftAmount; i++) {
            result[i] = inputArray[shiftStart + i];
        }
        for (int i = shiftAmount; i < input.length(); i++) {
            result[i] = inputArray[i - shiftAmount];
        }

        char[] finalResult = new char[result.length];
        for (int i = 0; i < result.length; i++) {
            finalResult[i] = result[i];
        }
        
        String resultString = "";
        for (char c : finalResult) {
            resultString += c;
        }

        System.out.println("String after shifting is: " + resultString);

        user_input.close();
    }

    public static void main(String[] args) {
        shift();
    }
}
