package my.java.basics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PartitionExamples {
    List<Integer> nums = Arrays.asList(3,5,74,38,1,9);
    
    public void partitionExample() {
        // Partition the list into two parts: even and odd numbers
        // This is a placeholder for the actual partition logic
        System.out.println("Partitioning list: " + nums);

        // Partition by even/odd, key=true for even, key=false for odd
        // Result is a Map<Boolean, List<Integer>>
        
        //var is a type inference feature in Java 10+
        /*
         * Important Rules to Remember
         * While var is convenient, there are specific limitations you should know:
         * 
         * Only for Local Variables: You can use var inside methods (like your code above), 
         * but you cannot use it for class-level fields or method parameters.
         * 
         * Must Initialize Immediately: You must assign a value on the same line
         * so the compiler knows what the type is. You cannot do var x; by itself.
         * 
         * Readability: It is considered best practice to use var when the type is obvious from the right side of the expression. 
         * If the code becomes hard to read because you can't tell what the variable type is, it is better to be explicit.
         */
        //Map<Boolean, List<Integer>>
        var result = nums.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
        result.forEach((key, value) -> System.out.println(key+ " " + value));

        List<Integer> nums = Arrays.asList(1,6,7,4);
        var results = nums.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
        results.forEach((key, value) -> System.out.println(key+" " + value));
    }
    public static void main(String[] args) {
        PartitionExamples partitionExamples = new PartitionExamples();
        partitionExamples.partitionExample();
    }
}
