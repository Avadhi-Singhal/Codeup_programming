/***
 * Represents a binary tree with methods for inserting nodes, visualizing the tree, and updating the root.
 * 
 * Owner : Avadhi-Singhal
 * 
 * Date of Creation : 12/11/2024
 */

import java.util.HashSet;
import java.util.Set;

class BinaryTree {
    private Node root;
    private Set<Integer> nodeValues;

    /***
     * Initializes an empty binary tree with no root and an empty set of node values.
     * @param none No parameters.
     * @return A new instance of BinaryTree.
     */
    public BinaryTree() {
        root = null;
        nodeValues = new HashSet<>();
    }

    /***
     * Inserts a value into the tree, ignoring duplicates and ensuring binary search tree properties.
     * @param value The value to be inserted into the tree.
     * @return void No return value.
     */
    public void insert(int value) {
        if (nodeValues.contains(value)) {
            System.out.println(Constant.DUPLICATE_VALUE + value + Constant.IGNORED);
            return;
        }
        root = insertRecursive(root, value);
        nodeValues.add(value);
    }

    /***
     * Recursively inserts a value into the binary tree, maintaining its structure.
     * @param node The current node to compare the value against.
     * @param value The value to be inserted.
     * @return The node after insertion.
     */
    private Node insertRecursive(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.value) {
            node.left = insertRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = insertRecursive(node.right, value);
        }
        return node;
    }

    /***
     * Calculates the height of the binary tree from a given node.
     * @param node The node from which the height is calculated.
     * @return The height of the tree from the given node.
     */
    private int getHeight(Node node) {
        if (node == null) return 0;
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    /***
     * Prints the tree structure to the console in a visually formatted manner.
     * @param none No parameters.
     * @return void No return value.
     */
    public void printTree() {
        int height = getHeight(root);
        int width = (int) Math.pow(2, height + 1);
        
        String[][] levels = new String[height][width];
        fillLevels(root, 0, 0, width - 1, levels);
        
        System.out.println(Constant.TREE_VISUALIZATION_MESSAGE);
        for (String[] level : levels) {
            for (String node : level) {
                System.out.print(node == null ? " " : node);
            }
            System.out.println();
        }
    }

    /***
     * Fills the levels array with values to visualize the tree structure.
     *@param node The current node to process.
     *@param level The current level of the node in the tree.
     *@param left The left boundary for visualization.
     *@param right The right boundary for visualization.
     *@param levels The 2D array holding the tree's visual representation.
     *@return void No return value.
     */
    private void fillLevels(Node node, int level, int left, int right, String[][] levels) {
        if (node == null) return;

        int mid = (left + right) / 2;
        
        levels[level][mid] = String.valueOf(node.value);
        
        if (node.left != null) {
            for (int i = mid - 1; i > (left + mid) / 2; i--) {
                levels[level][i] = Constant.CONNECTOR;
            }
        }
        if (node.right != null) {
            for (int i = mid + 1; i < (right + mid) / 2; i++) {
                levels[level][i] = Constant.CONNECTOR;
            }
        }

        fillLevels(node.left, level + 1, left, mid - 1, levels);
        fillLevels(node.right, level + 1, mid + 1, right, levels);
    }

    /***
     * Updates the root of the tree to a new value and rebuilds the tree structure.
     * @param newRootValue The new root value to set for the tree.
     * @return void No return value.
     */
    public void updateRoot(int newRootValue) {
        if (!nodeValues.contains(newRootValue)) {
            System.out.println(Constant.SPECIFIED_VALUE + newRootValue + Constant.NOT_EXIST_MSG);
            return;
        }

        Set<Integer> oldValues = new HashSet<>(nodeValues);
        nodeValues.clear();
        root = null;

        System.out.println(Constant.REBUILDING_TREE_MSG + newRootValue + Constant.NEW_ROOT_MSG);
        
        insert(newRootValue);

        for (int value : oldValues) {
            if (value != newRootValue) {
                insert(value);
            }
        }
    }
}