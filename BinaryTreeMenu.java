/***
 * /**
 * Menu-driven interface for performing operations on two binary search trees.
 * Owner : Avadhi-Singhal
 * Date of Creation : 21/11/2024
 */

import java.util.Scanner;

public class BinaryTreeMenu {
    private final BinarySearchTree tree1;
    private final BinarySearchTree tree2;
    private final Scanner scanner;

    /**
     * Constructor to initialize the menu and the binary search trees.
     */
    public BinaryTreeMenu() {
        this.tree1 = new BinarySearchTree();
        this.tree2 = new BinarySearchTree();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the main menu loop.
     */
    public void startMenu() {
        int choice = 0;
        while (choice != Constant.MAX_MENU_CHOICE) {
            System.out.println(Constant.MENU_HEADER);
            System.out.println(Constant.MENU_OPTION_1);
            System.out.println(Constant.MENU_OPTION_2);
            System.out.println(Constant.MENU_OPTION_3);
            System.out.println(Constant.MENU_OPTION_4);
            System.out.println(Constant.MENU_OPTION_5);

            choice = getValidatedIntInput(Constant.PROMPT_ENTER_CHOICE, Constant.MIN_MENU_CHOICE, Constant.MAX_MENU_CHOICE);

            switch (choice) {
                case 1 -> handleAddTree();
                case 2 -> handleCheckEquality();
                case 3 -> handleRemoveNode();
                case 4 -> handleAddValueToNodes();
                case 5 -> System.out.println(Constant.EXITING);
                default -> System.out.println(Constant.INVALID_INPUT);
            }
        }
        scanner.close();
    }

    /**
     * Handles adding values to a selected tree.
     */
    private void handleAddTree() {
        int size = getValidatedIntInput(Constant.PROMPT_ADD_TREE_SIZE, Constant.MIN_INPUT_VALUE, 100);
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = getValidatedIntInput(
                    String.format(Constant.PROMPT_ENTER_VALUE, i + 1),
                    Constant.MIN_INPUT_VALUE,
                    Constant.MAX_INPUT_VALUE
            );
        }

        int treeChoice = getValidatedIntInput(
                String.format(Constant.PROMPT_SELECT_TREE, Constant.ADDING_VALUES_TO),
                Constant.MIN_TREE_CHOICE,
                Constant.MAX_TREE_CHOICE
        );

        BinarySearchTree selectedTree = (treeChoice == 1) ? tree1 : tree2;
        selectedTree.createIndex(values);

        System.out.println("Tree " + treeChoice + ":");
        System.out.println(selectedTree.toString());
    }

    /**
     * Handles checking if two trees are equal.
     */
    private void handleCheckEquality() {
        System.out.println(Constant.CHECKING_TREES_EQUAL);
        boolean areEqual = tree1.equals(tree2);
        System.out.println(Constant.TREE_EQUALITY_RESULT + areEqual);
    }

    /**
     * Handles removing a node from a selected tree.
     */
    private void handleRemoveNode() {
        int removeValue = getValidatedIntInput(Constant.ENTER_VALUE_TO_REMOVE, Constant.MIN_INPUT_VALUE, Constant.MAX_INPUT_VALUE);
        int treeChoice = getValidatedIntInput(
                String.format(Constant.PROMPT_SELECT_TREE, Constant.REMOVING_VALUE_FROM),
                Constant.MIN_TREE_CHOICE,
                Constant.MAX_TREE_CHOICE
        );

        BinarySearchTree selectedTree = (treeChoice == 1) ? tree1 : tree2;

        if (selectedTree.removeNode(removeValue)) {
            System.out.println(String.format(Constant.REMOVED_VALUE, removeValue, treeChoice));
        } else {
            System.out.println(String.format(Constant.VALUE_NOT_FOUND, treeChoice));
        }

        System.out.println("Tree " + treeChoice + ":");
        System.out.println(selectedTree.toString());
    }

    /**
     * Handles adding a value to all nodes (except the root) of a selected tree.
     */
    private void handleAddValueToNodes() {
        int addValue = getValidatedIntInput(Constant.ENTER_VALUE_TO_ADD_TO_NODES, Constant.MIN_INPUT_VALUE, Constant.MAX_INPUT_VALUE);
        int treeChoice = getValidatedIntInput(
                String.format(Constant.PROMPT_SELECT_TREE, Constant.MODIFYING_TREE),
                Constant.MIN_TREE_CHOICE,
                Constant.MAX_TREE_CHOICE
        );

        BinarySearchTree selectedTree = (treeChoice == 1) ? tree1 : tree2;
        selectedTree.addValueToNodes(addValue);

        System.out.println(String.format(Constant.TREE_UPDATED, treeChoice));
        System.out.println(selectedTree.toString());
    }

    /**
     * Helper method to validate integer input within a range.
     *
     * @param message the prompt to display to the user.
     * @param min     the minimum valid value (inclusive).
     * @param max     the maximum valid value (inclusive).
     * @return the validated integer input.
     */
    private int getValidatedIntInput(String message, int min, int max) {
        int input;
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    break;
                }
                System.out.println(String.format(Constant.ERROR_OUT_OF_RANGE, min, max));
            } else {
                System.out.println(Constant.INVALID_INPUT);
                scanner.next();
            }
        }
        return input;
    }
}
