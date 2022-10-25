package tree;

public class Node {
    Node left;
    int value;
    Node right;

    public Node() {
        this.left = null;
        this.value = Integer.MIN_VALUE;
        this.right = null;
    }

    public Node(int value) {
        this.left = null;
        this.value = value;
        this.right = null;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
