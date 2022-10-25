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
        n.setNext(head);
        head = n;
    }

    public void addLast(int data) {
        Node n = new Node(data);
        if (isEmpty()) {
            n.setNext(head);
            head = n;
        } else {
            Node X = head;
            while (X.getNext() != null) {
                X = X.getNext();
            }
            X.setNext(n);
        }
    }

    public int removeFirst() {
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        int data = head.getData();
        head = head.getNext();
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
        Node x = head;
        Node y = head.getNext();
        while (y.getNext() != null) {
            x = y;
            y = y.getNext();
        }
        data = y.getData();
        x.setNext(null);
        return data;
    }

    public int size() {
        int counter = 0;
        Node x = head;
        while (x != null) {
            x = x.getNext();
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
            System.out.print(" " + n.getData());
            n = n.getNext();
        }
        System.out.println();
    }

    public void sort() {
        Node n = head;
        while (n.getNext() != null) {
            Node y = n.getNext();
            while (y != null) {
                if (n.getData() > y.getData()) {
                    int aux = n.getData();
                    n.setData(y.getData());
                    y.setData(aux);
                }
                y = y.getNext();
            }
            n = n.getNext();
        }
    }
}
