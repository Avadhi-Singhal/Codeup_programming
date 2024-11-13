/***
 * Represents a node in a binary tree, containing a value and references to left and right children.
 * 
 * Owner : Avadhi-Singhal
 * 
 * Date of Creation : 12/11/2024
 */

class Node {
    int value;
    Node left;
    Node right;

    /***
     * Initializes a new node with a specified value, setting left and right children to null.
     * @param value The value assigned to the node.
     */
    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}