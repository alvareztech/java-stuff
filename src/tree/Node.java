package tree;

public class Node {
    private Node left;
    private int value;
    private Node right;

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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
