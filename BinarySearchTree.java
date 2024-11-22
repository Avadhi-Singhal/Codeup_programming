/***
 * BinarySearchTree represents a binary search tree supporting insertion, deletion, traversal, 
 * and comparison operations with utilities for custom value manipulation and visual display.
 * Owner : Avadhi-Singhal
 * Date of Creation : 21/11/2024
 */

public class BinarySearchTree {
    private Node root;
    
    /***
     * Calculates the height of the tree from a given node.
     * @param node Starting node for height calculation.
     * @return Height of the tree from the node.
     */
    public int getHeight(Node node) {
        if (node == null) return 0;
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /***
     * Inserts a value into the tree.
     * @param value The value to insert.
     * @return true if inserted, false if value already exists.
     */
    public boolean insert(int value) {
        if (contains(root, value)) {
            return false;
        }
        root = insertRec(root, value);
        return true;
    }

    /***
     * Checks if a value exists in the tree.
     * @param node Starting node for search.
     * @param value Value to search for.
     * @return true if found, otherwise false.
     */
    private boolean contains(Node node, int value) {
        if (node == null) return false;
        if (node.value == value) return true;
        return value < node.value ? contains(node.left, value) : contains(node.right, value);
    }

    /***
     * Recursive helper method for inserting a value.
     * @param node Current node in recursion.
     * @param value Value to insert.
     * @return Updated node after insertion.
     */
    private Node insertRec(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.value) {
            node.left = insertRec(node.left, value);
        } else if (value > node.value) {
            node.right = insertRec(node.right, value);
        }
        return node;
    }

    /***
     * Removes a node with the given value.
     * @param value The value to remove.
     * @return true if removed, false if value not found.
     */
    public boolean removeNode(int value) {
        if (!contains(root, value)) {
            return false;
        }
        root = removeNodeRec(root, value);
        return true;
    }

    /***
     * Recursive helper method for removing a node.
     * @param node Current node in recursion.
     * @param value Value to remove.
     * @return Updated node after removal.
     */
    private Node removeNodeRec(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (value < node.value) {
            node.left = removeNodeRec(node.left, value);
        } else if (value > node.value) {
            node.right = removeNodeRec(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.value = minValue(node.right);
            node.right = removeNodeRec(node.right, node.value);
        }
        return node;
    }

    /***
     * Finds the minimum value in a subtree.
     * @param node Starting node of the subtree.
     * @return Minimum value in the subtree.
     */
    private int minValue(Node node) {
        int minv = node.value;
        while (node.left != null) {
            node = node.left;
            minv = node.value;
        }
        return minv;
    }

    /***
     * Inserts multiple values into the tree.
     * @param values Array of integers to insert.
     */
    public void createIndex(int[] values) {
        for (int value : values) {
            insert(value);
        }
    }

    /***
     * Checks if two binary search trees are equal.
     * @param otherTree Another binary search tree to compare.
     * @return true if equal, otherwise false.
     */
    public boolean equals(BinarySearchTree otherTree) {
        return equalsRec(this.root, otherTree.root);
    }

    /***
     * Recursive helper to check if two trees are structurally identical.
     * @param node1 Node of the first tree.
     * @param node2 Node of the second tree.
     * @return true if identical, otherwise false.
     */
    private boolean equalsRec(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        return (node1.value == node2.value) && equalsRec(node1.left, node2.left) && equalsRec(node1.right, node2.right);
    }

    /***
     * Adds a value to all nodes except the root and updates the tree.
     * @param valueToAdd Value to add to each node.
     */
    public void addValueToNodes(int valueToAdd) {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        ValueArray values = new ValueArray();
        collectValuesExcludingRoot(root, values);

        Node oldRoot = root;
        root = null;

        for (int i = 0; i < values.size(); i++) {
            int newValue = values.get(i) + valueToAdd;
            if (newValue > oldRoot.value) {
                insert(newValue);
            }
        }
        insert(oldRoot.value);
    }

    /***
     * Collects values from all nodes except the root.
     * @param node Starting node for traversal.
     * @param values Array to collect node values.
     */
    private void collectValuesExcludingRoot(Node node, ValueArray values) {
        if (node == null) return;
        if (node != root) {
            values.add(node.value);
        }
        collectValuesExcludingRoot(node.left, values);
        collectValuesExcludingRoot(node.right, values);
    }

    /***
     * Generates a string representation of the tree in a structured format.
     * @return Visual string representation of the tree.
     */
    @Override
    public String toString() {
        if (root == null) {
            return "Tree is empty.";
        }
        int height = getHeight(root);
        int width = (1 << height) * 4;
        int rows = height * 4;
        String[][] treeDisplay = new String[rows][width];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < width; j++) {
                treeDisplay[i][j] = " ";
            }
        }
        fillTreeDisplay(root, treeDisplay, 0, 0, width);
        StringBuilder result = new StringBuilder();
        for (String[] row : treeDisplay) {
            int lastNonSpace = row.length - 1;
            while (lastNonSpace >= 0 && row[lastNonSpace].equals(" ")) {
                lastNonSpace--;
            }
            for (int i = 0; i <= lastNonSpace; i++) {
                result.append(row[i]);
            }
            result.append("\n");
        }
        return result.toString();
    }

    /***
     * ecursively fills a 2D array to represent the tree visually.
     * @param node Node to display.
     * @param treeDisplay 2D array for tree structure.
     * @param row Row index for placement.
     * @param col Column index for placement.
     * @param width Width of the current tree section.
     */
    private void fillTreeDisplay(Node node, String[][] treeDisplay, int row, int col, int width) {
        if (node == null) return;
        String nodeValue = String.valueOf(node.value);
        int nodeWidth = nodeValue.length();
        int center = col + width / 2;
        int start = center - nodeWidth / 2;

        for (int i = 0; i < nodeWidth; i++) {
            if (start + i < treeDisplay[row].length) {
                treeDisplay[row][start + i] = String.valueOf(nodeValue.charAt(i));
            }
        }

        int nextWidth = width / 2;

        if (node.left != null) {
            int leftCenter = col + nextWidth / 2;
            for (int i = leftCenter + 1; i < center; i++) {
                if (i < treeDisplay[row + 1].length) {
                    treeDisplay[row + 1][i] = "_";
                }
            }
            if (row + 2 < treeDisplay.length && leftCenter < treeDisplay[row + 2].length) {
                treeDisplay[row + 2][leftCenter] = "/";
            }
            fillTreeDisplay(node.left, treeDisplay, row + 3, col, nextWidth);
        }

        if (node.right != null) {
            int rightCenter = col + width - nextWidth / 2;
            for (int i = center + 1; i < rightCenter; i++) {
                if (i < treeDisplay[row + 1].length) {
                    treeDisplay[row + 1][i] = "_";
                }
            }
            if (row + 2 < treeDisplay.length && rightCenter < treeDisplay[row + 2].length) {
                treeDisplay[row + 2][rightCenter] = "\\";
            }
            fillTreeDisplay(node.right, treeDisplay, row + 3, col + width / 2, nextWidth);
        }
    }
}
