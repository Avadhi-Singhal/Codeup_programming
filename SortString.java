/***
 * sort()
● Description: Sorts the characters of the current string in alphabetical order.
Example:
String text = "program";
String sortedText = sort(); // Result: "agmnoprr"

Owner = Avadhi=Singhal

Date of Creation : 04-09-2024
 */

import java.util.Scanner;

public class SortString {

    public static void sort() {
    	
    	Scanner user_input = new Scanner(System.in);
    	
    	System.out.println("Enter String to be sort:");
    	String input = user_input.nextLine();
    	
    	user_input.close();
    	
        char[] chars = input.toCharArray(); 

        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = 0; j < chars.length - i - 1; j++) {
             
                if (chars[j] > chars[j + 1]) {
                   
                    char temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
        }

        System.out.println("Sorted String is: " + new String(chars));
    }

    public static void main(String[] args) {
        sort(); 
    }
}

