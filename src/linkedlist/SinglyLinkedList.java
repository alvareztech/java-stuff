package linkedlist;

import java.util.Scanner;

public class SinglyLinkedList {
    private Node head;

    public SinglyLinkedList() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(int data) {
        Node n = new Node(data);
        n.next = head;
        head = n;
    }

    public void addLast(int data) {
        Node n = new Node(data);
        if (isEmpty()) {
            n.next = head;
            head = n;
        } else {
            Node X = head;
            while (X.next != null) {
                X = X.next;
            }
            X.next = n;
        }
    }

    public int removeFirst() {
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        int data = head.data;
        head = head.next;
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
        Node x = head;
        Node y = head.next;
        while (y.next != null) {
            x = y;
            y = y.next;
        }
        data = y.data;
        x.next = null;
        return data;
    }

    public int size() {
        int counter = 0;
        Node x = head;
        while (x != null) {
            x = x.next;
            counter++;
        }
        return counter;
    }

    public void read() {
        Scanner in = new Scanner(System.in);
        System.out.print("Numbers of elements: ");
        int n = in.nextInt();
        System.out.println("Elements: ");
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            addLast(a);
        }
    }

    public void show() {
        Node n = head;
        while (n != null) {
            System.out.print(" " + n.data);
            n = n.next;
        }
        System.out.println();
    }

    public void sort() {
        Node n = head;
        while (n.next != null) {
            Node y = n.next;
            while (y != null) {
                if (n.data > y.data) {
                    int aux = n.data;
                    n.data = y.data;
                    y.data = aux;
                }
                y = y.next;
            }
            n = n.next;
        }
    }
}
