/***
 * splice(int start, int length)
● Description: Removes a substring from the current string starting at index start and of
length length.
Example:
j
String text = "Academy";
splice(2, 2); // Result: "Acemy"

Owner : Avadhi-Singhal

Date of Creation : 04-09-2024 
 */

import java.util.Scanner;

public class SpliceString {

    public static void splice() {
    	Scanner user_input = new Scanner(System.in);
    	
    	System.out.println("Enter the string you want to splice:");
    	String input = user_input.nextLine();
    	
    	System.out.println("Enter the start index from where you want to splice you string:");
    	int start = user_input.nextInt();
    	
    	System.out.println("Enter the length which you want to splice:");
    	int length = user_input.nextInt();
    	
    	user_input.close();
        
        if (input == null || start < 0 || start >= input.length() || length < 0) {
            throw new IllegalArgumentException("Invalid start index or length.");
        }
        
        int end = Math.min(start + length, input.length());
        
        char[] result = new char[input.length() - (end - start)];
        
        int resultIndex = 0;
        
        for (int i = 0; i < start; i++) {
            result[resultIndex++] = input.charAt(i);
        }
        
        for (int i = end; i < input.length(); i++) {
            result[resultIndex++] = input.charAt(i);
        }
        
        System.out.println(new String(result));
    }

    public static void main(String[] args) {
       splice();
    }
}
