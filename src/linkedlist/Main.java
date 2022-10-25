package linkedlist;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.read();
        list.show();
        list.sort();
        System.out.println("*");
        list.show();

        // Use an ArrayList for storing and accessing data, and LinkedList to manipulate data.

        LinkedList<String> cars = new LinkedList<String>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");
        System.out.println(cars);
    }
}
