/***
 * This is the utility class. It provides functioning for validation of user inputs while creating quiz.
 * 
 * Owner: Avadhi-Singhal
 * 
 * Date of Creation : 21/10/2024
 */
import java.util.Scanner;

public class Utility {
	
	Constant constant = new Constant();

	/**
     * Displays the menu options to the user.
     */
    public void displayMenu() {
        System.out.println(constant.CHOOSE_OPTION);
    }
    
    /**
     * Gets a valid menu choice from the user.
     * 
     * @param scanner The scanner to read user input.
     * @return A valid choice between 1 and 3.
     */
    public int getValidChoice(Scanner scanner) {
        int choice = 0;
        while (true) {
            try {
                System.out.print(constant.ENTER_CHOICE);
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 3) {
                    System.out.println(constant.INVALID_NUMBER);
                    continue;
                }
                break; // Valid choice, exit the loop
            } catch (NumberFormatException e) {
                System.out.println(constant.PROMPT_VALID_CHOICE);
            }
        }
        return choice;
    }
    
    /**
     * Gets the title of the quiz from the user.
     * 
     * @param scanner The scanner to read user input.
     * @return The title of the quiz.
     */
    public String getValidTitle(Scanner scanner) {
        String title;
        do {
            System.out.println(constant.PROMPT_TITLE);
            title = scanner.nextLine();
        } while (title.trim().isEmpty() || title.length() > constant.MAXIMUM_CHARACTERS);
        return title;
    }
    
    /**
     * Gets the question text from the user.
     * 
     * @param scanner The scanner to read user input.
     * @return The question text.
     */
    public String getQuestionText(Scanner scanner) {
        String questionText;
        do {
            System.out.println(constant.PROMPT_QUESTION_TEXT);
            questionText = scanner.nextLine();
        } while (questionText.trim().isEmpty() || questionText.length() > constant.MAXIMUM_CHARACTERS);
        return questionText;
    }
    
    /***
     * This methos finds the minimun of two interger.
     * 
     * @param length is parameter for comparing
     * @return minString -> minimum of length, minString
     */
    public static int minimum(int length) {
    	int minString = 3;
    	if (minString > length) {
    		minString = length;
    	}
    	return minString;
    }
}
