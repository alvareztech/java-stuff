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
            n.next = head;
            head.previous = n;
            head = n;
        }
    }

    public void addLast(int a) {
        DoubleNode n = new DoubleNode(a);
        if (head == null) {
            head = n;
        } else {
            DoubleNode X = head;
            while (X.next != null) {
                X = X.next;
            } // now x is the last node
            X.next = n;
            n.previous = X;
        }
    }

    public int removeFirst() {
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        int data = head.data;
        if (head.next == null) { // one element scenario
            head = null;
        } else {
            head = head.next;
            head.previous = null;
        }
        return data;
    }

    public int removeLast() {
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        int data;
        if (head.next == null) {
            data = head.data;
            head = null;
            return data;
        }
        DoubleNode x = head;
        while (x.next != null) {
            x = x.next;
        } // now x is the last node
        data = x.data;
        DoubleNode y = x.previous;
        y.next = null;
        return data;
    }

    public int size() {
        int counter = 0;
        DoubleNode X = head;
        while (X != null) {
            X = X.next;
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
            DoubleNode x = head;
            while (x != null) {
                System.out.print(" " + x.data);
                x = x.next;
            }
            System.out.println();
        }
    }
}
