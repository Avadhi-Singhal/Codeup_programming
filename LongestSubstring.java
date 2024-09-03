/***
 * Given a string s, find the length of the longest substring without repeating characters.
Example :
● Input: s = "abcabcbb"
● Output: 3
● Explanation: The answer is "abc", with the length of 3.

Constraints:
● 0 <= s.length <= 50,000
● s consists of English letters, digits, symbols, and spaces.

Owner: Avadhi-Singhal

Date of Creation: 03-09-2024
 */

package myproject;

import java.util.Scanner;

public class LongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            int currentLength = 0;
            boolean isUnique = true;

            for (int j = i; j < s.length(); j++) {

                for (int k = i; k < j; k++) {
                    if (s.charAt(k) == s.charAt(j)) {
                        isUnique = false;
                        break;
                    }
                }

                if (!isUnique) {
                    break;
                }

                currentLength++;
            }

            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
       
            System.out.println("Enter a string to find the length of the longest substring without repeating characters (or type 'exit' to quit):");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            int result = lengthOfLongestSubstring(input);

            System.out.println("Length of the longest substring without repeating characters: " + result);
        }

        scanner.close();
    }
}
