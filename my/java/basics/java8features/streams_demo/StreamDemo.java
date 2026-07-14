package my.java.basics.java8features.streams_demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,8);
        numbers.stream().forEach(n -> System.out.println(n));
        numbers.stream().filter(n -> n % 2 == 0).forEach(n -> {System.out.println("Even numbers"+n);});
        
        //Calculator program with functional interface
        Calculator calculator = (x, y) -> {
            Random random = new Random();
            return x * y + random.nextInt(50);
        };
        System.out.println(calculator.calculator(3, 4));

        //we no need any interface to do the able calculator program
        IntBinaryOperator cal = (x, y) -> {
             Random random = new Random();
            return x * y + random.nextInt(50);
        };
        System.out.println("calculation with out intreface:" + cal.applyAsInt(1, -1));


        /*
        To play with different streams
        */
       Integer[] scores = new Integer[]{80,60,90,70};

       Stream<Integer> scoresStreams = Arrays.stream(scores);

       Stream<String> letterStream = Stream.of("a", "b", "c");

       List<String> shoppingList = new ArrayList<>();
       shoppingList.add("coffee");
       shoppingList.add("bread");
       shoppingList.add("apple");
       shoppingList.add("orange");
       shoppingList.add("brush");

        Stream<String> shoppingListStream = shoppingList.stream();
       //shoppingListStream.sorted().forEach(item -> System.out.println(item));

        // this is used ot sent filtered items to terminal 
    //    shoppingListStream.sorted()
    //     .map(item -> item.toUpperCase())
    //     .filter(item -> item.startsWith("B"))
    //     .forEach(item -> System.out.println(item));

        //
        List<String> sortedShoppingList = shoppingListStream.sorted()
        .distinct()
        .filter(item -> item.matches("^[b|a].*")) // to filter the word start with b or a
        .collect(Collectors.toList()); 
        System.out.println(sortedShoppingList);   // [apple, bread, brush]

    }
}
