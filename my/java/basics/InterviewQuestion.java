package my.java.basics;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

public class InterviewQuestion {
    public static void main(String[] args) {
        System.out.println("Hello InterviewQuestion");

        List<Integer> numbers = Arrays.asList(null,8,1, 7, 2, 3, 4, 5);

        try {
            List<Integer> result = numbers.stream()
            .distinct()
            .skip(1)
            .limit(2)
            .collect(Collectors.toList());
            //.forEach(System.out::println); //[8, 1]
            System.out.println(result);
            
            List<Integer> listIntegers = Arrays.asList(45,50,45);
            //Optional is a container object which may or may not contain a non-null value.
            Optional<Integer> resultOptional 
                = listIntegers.stream().distinct().skip(2).findFirst();
            
            // Traditional way to check if Optional has a value
            if (resultOptional.isPresent()) { // isPresent() returns true if the Optional has a value, false otherwise
                System.out.println(resultOptional.get());
            }

            // Modern way to check if Optional has a value
            resultOptional.ifPresent(System.out::println);

            numbers.stream()
            .filter(n -> n % 2 == 0)
            .forEach(System.out::println);

        } catch (NullPointerException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

}
