/***
 * This class generates all unique palindromic substrings from a user-provided string (up to 25 characters), 
 * leveraging efficient substring processing and user-friendly menu-based interaction.
 * Owner : Avadhi-Singhal
 * Date of Creation : 21/11/2024
 */

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UniquePalindromes {

	/**
	 * Recursively finds all unique palindromic substrings in a given string.
	 *
	 * @param s          the input string to process
	 * @param start      the starting index for generating substrings
	 * @param current    the current substring being processed
	 * @param palindromes a set to store unique palindromic substrings
	 */
    public static void findPalindromes(String string, int start, String current, Set<String> palindromes) {
        
        if (start == string.length()) {
            return;
        }

        for (int i = start; i < string.length(); i++) {
            current += string.charAt(i); 
            if (isPalindrome(current)) {
                palindromes.add(current); 
            }
            findPalindromes(string, i + 1, current, palindromes); 
            current = current.substring(0, current.length() - 1); 
        }
    }
    
    /**
     * Checks if a given string is a palindrome.
     *
     * @param str the string to check
     * @return true if the string is a palindrome, otherwise false
     */
    public static boolean isPalindrome(String string) {
        int left = 0, right = string.length() - 1;
        while (left < right) {
            if (string.charAt(left) != string.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true; 

        while (continueProgram) {
            System.out.println(Constant.OPTION_MENU);
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 2) {
                System.out.println(Constant.EXIT_PROGRAM_MSG);
                continueProgram = false; 
            } else if (choice == 1) {
                System.out.println(Constant.PROMPT_STRING);
                String input = scanner.nextLine();

                if (input.length() > 25) {
                    System.out.println(Constant.ERROR_MSG);
                    continue;
                }
                
                Set<String> palindromes = new HashSet<>();

                for (int i = 0; i < input.length(); i++) {
                    for (int j = i + 1; j <= input.length(); j++) {
                        String substring = input.substring(i, j);
                        if (isPalindrome(substring)) {
                            palindromes.add(substring);
                        }
                    }
                }

                System.out.println(Constant.UNIQUE_PALINDROME);
                for (String palindrome : palindromes) {
                    System.out.println(palindrome);
                }
                System.out.println(Constant.TOTAL_PALINDROEMS_PROMPT + palindromes.size());
                System.out.println();
            } else {
                System.out.println(Constant.EXIT_PROGRAM_MSG);
            }
        }

        scanner.close();
    }

}
