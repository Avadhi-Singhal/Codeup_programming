/***
 * Main entry point for binary tree program, handling user input and tree manipulations.
 * 
 * Owner : Avadhi-Singhal
 * 
 * Date of Creation : 12/11/2024
 */

import java.util.Scanner;

public class Main {
	
	/***
	 * Executes the binary tree operations: reading inputs, inserting nodes, setting a new root.
	 * @param args Command-line arguments (not used in this program).
	 * @return void This method doesn't return any value.
	 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();
        Utility utility = new Utility();

        int n = utility.getNodeCount(scanner);

        System.out.println(Constant.PROMT_NODE_VALUES);
        int insertedNodes = 0;
        while (insertedNodes < n) {
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value >= 0 && value <= 999) {
                    tree.insert(value);
                    insertedNodes++;
                } else {
                    System.out.println(Constant.NODE_VALUE_EXCEED_MSG);
                }
            } else {
                System.out.println(Constant.INVALID_INPUT);
                scanner.next(); 
            }
        }

        System.out.println(Constant.BINARY_TREE_STRUCTURE);
        tree.printTree();

        if (utility.wantsToSetNewRoot(scanner)) {
            utility.setNewRoot(scanner, tree);
            System.out.println(Constant.UPDATED_TREE_STRUCTURE);
            tree.printTree();
        }

        scanner.close();
    }
}
