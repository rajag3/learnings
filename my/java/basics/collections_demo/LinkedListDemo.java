package my.java.basics.collections_demo;

import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList fruitListlist = new LinkedList<>();
        fruitListlist.add("mango");
        fruitListlist.add("banana");
        fruitListlist.add("apple");

        System.out.println(fruitListlist.get(0)); //mango
        System.out.println(fruitListlist.getFirst());//mango
        System.out.println(fruitListlist.getLast()); //apple
        System.out.println(fruitListlist.poll()); // mango
        System.out.println(fruitListlist.pop()); // banana

        System.out.println(fruitListlist.remove()); // apple

        // if the list is empty poll will return but pop will through exception
        System.out.println(fruitListlist.poll()); // null
        System.out.println(fruitListlist.pop()); // Exception in thread "main" java.util.NoSuchElementException
    }
}
