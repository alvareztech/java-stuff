package linkedlist;

import java.util.Scanner;

public class DoublyLinkedList {

    private DoubleNode head;

    public DoublyLinkedList() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(int a) {
        DoubleNode n = new DoubleNode(a);
        if (head == null) {
            head = n;
        } else {
            n.setNext(head);
            head.setPrevious(n);
            head = n;
        }
    }

    public void addLast(int a) {
        DoubleNode N = new DoubleNode(a);
        if (head == null) {
            head = N;
        } else {
            DoubleNode X = head;
            while (X.getNext() != null) {
                X = X.getNext();
            } // now x is the last node
            X.setNext(N);
            N.setPrevious(X);
        }
    }

    public int removeFirst() {
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        int data = head.getData();
        if (head.getNext() == null) { // one element scenario
            head = null;
        } else {
            head = head.getNext();
            head.setPrevious(null);
        }
        return data;
    }

    public int removeLast() {
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        int data;
        if (head.getNext() == null) {
            data = head.getData();
            head = null;
            return data;
        }
        DoubleNode x = head;
        while (x.getNext() != null) {
            x = x.getNext();
        } // now x is the last node
        data = x.getData();
        DoubleNode y = x.getPrevious();
        y.setNext(null);
        return data;
    }

    public int size() {
        int counter = 0;
        DoubleNode X = head;
        while (X != null) {
            X = X.getNext();
            counter++;
        }
        return counter;
    }

    public void read() {
        Scanner in = new Scanner(System.in);
        System.out.print("Number of elements: ");
        int n = in.nextInt();
        System.out.println(" Elements:");
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            addLast(a);
        }
    }

    public void show() {
        if (head == null) {
            System.out.println("Empty list!");
        } else {
            DoubleNode X = head;
            while (X != null) {
                System.out.print(" " + X.getData());
                X = X.getNext();
            }
            System.out.println("");
        }
    }
}
