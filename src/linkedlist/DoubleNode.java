package linkedlist;

public class DoubleNode {

    private DoubleNode previous;
    private int data;
    private DoubleNode next;

    public DoubleNode(int data) {
        this.previous = null;
        this.data = data;
        this.next = null;
    }

    public DoubleNode getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleNode previous) {
        this.previous = previous;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public DoubleNode getNext() {
        return next;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }
}
