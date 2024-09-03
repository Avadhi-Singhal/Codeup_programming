/***
 * Write a Java program that takes a string input and outputs the frequency of
each character in a compressed form.
Example :
● Input: "aabcccdeee"
● Output: "a2b1c3d1e3"

Constraints:
● The input string will only contain lowercase English letters.
● The input string length will be at most 1000.

Owner-Name: Avadhi-Singhal

Date of Creation: 03-09-2024 
*/

package myproject;

import java.util.Scanner;

public class CompressString {

    public static String compressString(String s) {
     
        StringBuilder result = new StringBuilder();
       
        StringBuilder processedChars = new StringBuilder();
        
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            char currentChar = s.charAt(i);
            
            if (processedChars.toString().indexOf(currentChar) == -1) {
               
                int count = 0;
                for (int j = 0; j < n; j++) {
                    if (s.charAt(j) == currentChar) {
                        count++;
                    }
                }
                
                result.append(currentChar).append(count);
                
                processedChars.append(currentChar);
            }
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
       
        try (Scanner scanner = new Scanner(System.in)) {
           
        	boolean continueRunning = true;
            
            while (continueRunning) {
            
                System.out.println("Enter a string to compress (or type 'exit' to quit):");
                String input = scanner.nextLine();
                             
                if (input.equalsIgnoreCase("exit")) {
                    continueRunning = false;
                    System.out.println("Exiting...");
                } else {
                    String compressedString = compressString(input);                  
                    System.out.println("Compressed string:");
                    System.out.println(compressedString);
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while reading input: " + e.getMessage());
        }
    }
}
