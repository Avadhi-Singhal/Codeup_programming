/***
 * isPalindrome()
● Description: Checks if the current string is a palindrome. A palindrome is a word,
phrase, or sequence that reads the same backward as forward.
Example:
String text = "madam";
boolean isPal = isPalindrome(); // Result: true

Owner: Avadhi-Singhal

Date of Creation : 04-09-2024
 */

import java.util.Scanner;

public class IsPalindrome {
 
    public static String reversed(String input) { 
    	
    	char[] reverseString = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            reverseString[i] = input.charAt(input.length() - 1 - i);
        }
      
        return new String(reverseString);
    }

    public static void isPalindrome() {
    	
        Scanner user_input = new Scanner(System.in);
    	
    	System.out.println("Enter the String to check palindrome: ");
    	String input = user_input.nextLine(); 
    	
    	user_input.close();
    	
        String reverseString = reversed(input);
        boolean flag = true;
        
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != reverseString.charAt(i)) {
                flag = false;
                break;
            }
        }
        
        if (flag) {
            System.out.println("String is Palindrome");
        } else {
            System.out.println("String is not Palindrome");
        }
    }

    public static void main(String[] args) {
        isPalindrome();
    }
}
