package my.java.basics.java8features;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class FindSecondMaxNumber {
    public static void main(String[] args) {
        //List<Integer> numbers = Arrays.asList(8,null,6,9,3,1,-1); // Second max is:8
        //List<Integer> numbers = Arrays.asList(3,3); // java.util.NoSuchElementException: List does not have atleast 2 distinc elements
        //List<Integer> numbers = Arrays.asList(null); // Exception in thread "main" java.lang.NullPointerException
        List<Integer> numbers = Arrays.asList(8,null,6,9,3,1,-1); // Second max is:8
        Stream<Integer> numberStream = numbers.stream();
        try {
            int secondMax = numberStream
                .filter(java.util.Objects::nonNull)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("List does not have atleast 2 distinc elements"));
            System.out.println("Second max is:"+secondMax);

            //try to process the stream again
            numberStream.forEach(item -> System.out.println(item)); // stream has already been operated upon or closed
            
        } catch (NullPointerException e){
            System.err.println("Error: The list contains a null element during processing. " + e.getMessage());
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
