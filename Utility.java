/***
 * Handles user input validation for node count, root setting, and binary tree interaction.
 * 
 * Owner : Avadhi-Singhal
 * 
 * Date of Creation : 12/11/2024
 */

import java.util.Scanner;

public class Utility {
	
	/***
	 * Prompts and validates the number of nodes (1-15) for the binary tree.
	 * @param scanner The Scanner object used to read user input.
	 * @return int The validated number of nodes for the tree.
	 */
	 public int getNodeCount(Scanner scanner) {
	        int n = -1;
	        while (n < 1 || n > 15) {
	            System.out.print(Constant.PROMPT_NUMBER_OF_NODES);
	            if (scanner.hasNextInt()) {
	                n = scanner.nextInt();
	                if (n < 1 || n > 15) {
	                    System.out.println(Constant.PROMPT_VALID_INTEGER);
	                }
	            } else {
	                System.out.println(Constant.INVALID_INPUT);
	                scanner.next(); 
	            }
	        }
	        return n;
	    }
	 
	 /***
	  * Asks if the user wants to set a new root for the tree.
	  * @param scanner The Scanner object used to read the user's response.
	  * @return boolean true if the user wants to set a new root, otherwise false.
	  */
	 public boolean wantsToSetNewRoot(Scanner scanner) {
	        System.out.print(Constant.PROMPT_TO_SET_NEW_ROOT);
	        String response = scanner.next();
	        return response.equalsIgnoreCase("yes");
	 }
	
	 /***
	  * Prompts for and sets the new root value in the binary tree.
	  * @param scanner The Scanner object for reading user input.
	  * @param tree The BinaryTree object whose root will be updated.
	  * @return void This method does not return any value.
	  */
	 public void setNewRoot(Scanner scanner, BinaryTree tree) {
	        int newRootValue = -1;
	        boolean validRoot = false;
	        while (!validRoot) {
	            System.out.print(Constant.PROMPT_NEW_ROOT_VALUE);
	            if (scanner.hasNextInt()) {
	                newRootValue = scanner.nextInt();
	                validRoot = true; 
	                tree.updateRoot(newRootValue);
	            } else {
	                System.out.println(Constant.INVALID_INPUT);
	                scanner.next(); 
	            }
	        }
	    }
}
