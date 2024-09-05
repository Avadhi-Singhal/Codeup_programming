import java.util.Scanner;

public class CompleteAssignment {

    public static void append(Scanner userInput) {
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
    }

    public static void countWords(Scanner userInput) {
        System.out.println("Enter String:");
        String input = userInput.nextLine();

        int count = 0;
        boolean inWord = false;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            boolean isAlphanumeric = (ch >= 'A' && ch <= 'Z') ||
                                     (ch >= 'a' && ch <= 'z') ||
                                     (ch >= '0' && ch <= '9');

            if (isAlphanumeric) {
                if (!inWord) {
                    count++;
                    inWord = true;
                }
            } else {
                inWord = false;
            }
        }

        System.out.println("Word count: " + count);
    }

    public static void handleUserInput(Scanner userInput) {
        String currentString;

        System.out.println("Enter the initial string: ");
        currentString = userInput.nextLine();

        while (true) {
            System.out.println("Choose an option: \n1. Replace a character \n2. Replace a substring \n3. Exit");
            int choice = userInput.nextInt();
            userInput.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter the character to replace: ");
                    char oldChar = userInput.nextLine().charAt(0);
                    System.out.println("Enter the new character: ");
                    char newChar = userInput.nextLine().charAt(0);
                    currentString = currentString.replace(oldChar, newChar);
                    System.out.println("Updated String: " + currentString);
                    break;

                case 2:
                    System.out.println("Enter the substring to replace: ");
                    String oldSubstr = userInput.nextLine();
                    System.out.println("Enter the new substring: ");
                    String newSubstr = userInput.nextLine();
                    if (!oldSubstr.isEmpty()) {
                        currentString = currentString.replace(oldSubstr, newSubstr);
                        System.out.println("Updated String: " + currentString);
                    } else {
                        System.out.println("Invalid input. Substrings cannot be null or empty.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting.");
                    return;

                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    break;
            }
        }
    }

    public static String reversed(String input) {
        char[] reverseString = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            reverseString[i] = input.charAt(input.length() - 1 - i);
        }
        return new String(reverseString);
    }

    public static void isPalindrome(Scanner userInput) {
        System.out.println("Enter the String to check palindrome: ");
        String input = userInput.nextLine();

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

    public static void splice(Scanner userInput) {
        System.out.println("Enter the string you want to splice:");
        String input = userInput.nextLine();

        System.out.println("Enter the start index from where you want to splice your string:");
        int start = userInput.nextInt();

        System.out.println("Enter the length which you want to splice:");
        int length = userInput.nextInt();

        if (start < 0 || start >= input.length() || length < 0) {
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

    public static void splitIntoWords(Scanner userInput) {
        System.out.println("Enter String to split: ");
        String input = userInput.nextLine();

        if (input == null || input.isEmpty()) {
            System.out.println("Input string is empty.");
            return;
        }

        String tempWord = "";
        String[] tempWords = new String[input.length()];
        int wordCount = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == ' ' || i == input.length() - 1) {
                if (c != ' ') {
                    tempWord += c;
                }
                if (!tempWord.isEmpty()) {
                    tempWords[wordCount++] = tempWord;
                    tempWord = "";
                }
            } else {
                tempWord += c;
            }
        }

        if (!tempWord.isEmpty()) {
            tempWords[wordCount++] = tempWord;
        }

        System.out.print("[");
        for (int i = 0; i < wordCount; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print("\"" + tempWords[i] + "\"");
        }
        System.out.println("]");
    }

    public static void maxRepeat(Scanner userInput) {
        System.out.println("Enter String for maximum character count:");
        String input = userInput.nextLine();

        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string is empty.");
        }

        int maxCount = 0;
        char maxChar = input.charAt(0);

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            int count = 0;

            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) == currentChar) {
                    count++;
                }
            }

            if (count > maxCount) {
                maxCount = count;
                maxChar = currentChar;
            }
        }

        System.out.println("Character with maximum count = " + maxChar + " with maximum count = " + maxCount);
    }

    public static void sort(Scanner userInput) {
        System.out.println("Enter String to be sorted:");
        String input = userInput.nextLine();

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

    public static void shift(Scanner userInput) {
        System.out.println("Enter initial String: ");
        String input = userInput.nextLine();

        System.out.println("Enter number of characters you want to shift from the end:");
        int shiftAmount = userInput.nextInt();

        if (shiftAmount < 0 || shiftAmount > input.length()) {
            System.out.println("Invalid shift amount. It must be between 0 and the length of the string.");
            return;
        }

        char[] inputArray = input.toCharArray();
        char[] result = new char[input.length()];

        int shiftStart = input.length() - shiftAmount;
        for (int i = 0; i < shiftAmount; i++) {
            result[i] = inputArray[shiftStart + i];
        }
        for (int i = shiftAmount; i < input.length(); i++) {
            result[i] = inputArray[i - shiftAmount];
        }

        System.out.println("String after shifting is: " + new String(result));
    }

    public static void reversed(Scanner userInput) {
        System.out.println("Enter String to be reversed: ");
        String input = userInput.nextLine();

        char[] reverseString = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            reverseString[i] = input.charAt(input.length() - 1 - i);
        }

        System.out.println("Reversed String is: " + new String(reverseString));
    }

    public static void choice(int option, Scanner userInput) {
        switch (option) {
            case 1:
                append(userInput);
                break;
            case 2:
                countWords(userInput);
                break;
            case 3:
                handleUserInput(userInput);
                break;
            case 4:
                isPalindrome(userInput);
                break;
            case 5:
                splice(userInput);
                break;
            case 6:
                splitIntoWords(userInput);
                break;
            case 7:
                maxRepeat(userInput);
                break;
            case 8:
                sort(userInput);
                break;
            case 9:
                shift(userInput);
                break;
            case 10:
                reversed(userInput);
                break;
            default:
                System.out.println("Invalid option. Please choose a number between 1 and 10.");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Choose operation to perform on String or choose exit:");
                System.out.println("1. Append string \n2. Count words in string \n3. Replace character or substring \n4. Check palindrome \n5. Splice string \n6. Split string into array of words \n7. Maximum character count \n8. Sort string \n9. Shift characters \n10. Reverse string \nType 'exit' to quit.");

                String input = userInput.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    try {
                        int option = Integer.parseInt(input);
                        choice(option, userInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number between 1 and 10 or 'exit' to quit.");
                    }
                }
            }
        } finally {
            userInput.close(); // Close the Scanner object when done
        }
    }
}
