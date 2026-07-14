package my.java.basics.java8features;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FindWordDemo {
    public static void main(String[] args) {
        List<String> fruitList = Arrays.asList("apple", "aa", "orange", "banana", "kiwi");
        fruitList.stream()
        .filter(fruit -> fruit.startsWith("a") && fruit.length() >= 5)
        .forEach(item -> System.out.println(item));


    }
}
