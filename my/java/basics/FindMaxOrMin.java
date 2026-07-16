package my.java.basics;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import my.java.basics.employee.Employee;
import my.java.basics.employee.Transaction;

public class FindMaxOrMin {
    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(1,4,5,6);
        List<Integer> list2 = Arrays.asList(4,5,7,8);
        list2.stream().filter(num -> list1.contains(num)).collect(Collectors.toList()).forEach(System.out::println); // O(n x m) where n is the size of list2 and m is the size of list1
        //list2.stream().filter(set1::contains).collect(Collectors.toList()).forEach(System.out::println); // O(n) where n is the size of list2

        HashSet<Integer> set1 = new HashSet<>(list1);
        list2.stream().filter(set1::contains).forEach(System.out::println); // O(n) where n is the size of list2


        Set<Integer> s1 = new HashSet<>(list1);
        Set<Integer> s2 = new HashSet<>(list2);

        // Find items in s1 not in s2
        Set<Integer> temp1 = new HashSet<>(s1);
        temp1.removeAll(s2);

        // Find items in s2 not in s1
        Set<Integer> temp2 = new HashSet<>(s2);
        temp2.removeAll(s1);

        // Merge them
        temp1.addAll(temp2);
        System.out.println(temp1); // [1, 6, 7, 8]


        //--------------------------------------------------------
        List<Transaction> transactions = List.of(
            new Transaction(1, "IT", 1000.0),
            new Transaction(2, "HR", 2000.0),
            new Transaction(3, "Finance", 3000.0),
            new Transaction(4, "IT", 4000.0)    
        );


        List<List<Integer>> listOfListInteger = Arrays.asList(
            Arrays.asList(8,5,1,2,3,6),
            Arrays.asList(8,5,3,6),
            Arrays.asList(9,8,4)
        );

        List<Integer> result = listOfListInteger.stream()
        .flatMap(innerList -> innerList.stream())
        .distinct()
        .collect(Collectors.toList());
        System.out.println(
            "uniq element from list of list"
            + result);


        //my.java.basics.employee.Transaction@4e25154f  -- without toString() method
        //transactions.stream().max(Comparator.comparing(Transaction::getAmount)).ifPresent(System.out::println); //Transaction [transactionId=4, department=IT, amount=4000.0]
        //transactions.stream().collect(Collectors.groupingBy(Transaction::getDepartment)).forEach((dept, trans) -> System.out.println(dept + " " + trans));
        //var result = transactions.stream().collect(Collectors.groupingBy(Transaction::getDepartment, Collectors.summarizingDouble(Transaction::getAmount)));
        //System.out.println(result);

        // Using a Map<String, Double> to store results
        
        /*Map<String, Double> averageByCurrency = transactions.stream()
        .filter(Objects::nonNull) // Defensive programming
        .collect(Collectors.groupingBy(
            Transaction::getCurrency, 
            Collectors.averagingDouble(Transaction::getAmount)
        ));
        */

        //List<String> languages = List.of("Java", "Python", "C++", "JavaScript");
        //List<String> languages = List.of("Java", "java");
        // legacy way to find the max string by length
        //languages.stream().max((a, b) -> a.length() - b.length()).ifPresent(System.out::println);
        
        // Using Comparator.comparingInt is the standard "clean" way
        // languages.stream()
        // .max(Comparator.comparing(String::length))
        // .ifPresent(System.out::println); // JavaScript

        List<Employee> employees = List.of(
            new Employee(1, "John", 1000),
            new Employee(2, "Jane", 2000),
            new Employee(4, "Raja", 3000),
            new Employee(3, "Bob", 3000)
        );

        // Find the sum of all employee salaries using 
       Double maxSalary = employees.parallelStream()
        .mapToDouble(Employee::getSalary)
        .sum();
        System.out.println("Sum of salary: " + maxSalary);
        /*
Should you use .parallelStream() here?
 For a list of employees (like your list of 4), do not use parallel streams.

  Here is why:

   Overhead Cost: Setting up a parallel operation takes time and CPU resources. 
   For small lists, it takes much longer to "split" the work and "merge" the results back together 
   than it would have taken just to add the numbers sequentially.

   When to actually use it: Only switch to .parallelStream() if you have massive datasets 
    (e.g., hundreds of thousands of records) and the operation you are performing is complex or time-consuming.

   Thread Safety: When using parallelStream(), you must ensure that the operations inside your pipeline are "stateless" 
    and "thread-safe." Summing numbers is perfectly safe, but if you were doing something that updates an external variable or a database, you would run into major bugs.

   Rule of Thumb: Stick to .stream() by default. Only reach for .parallelStream() if you have performed performance 
    testing and proven that your specific use case is actually faster with it.

   The Theory: Why Stateful Lambdas Fail in Parallel
   A lambda is stateless if its result depends only on its input. A stateful lambda (like incrementing a counter outside the stream) 
   depends on the timing of thread execution.

Question: Why is using a stateful lambda (a lambda that modifies an external variable, e.g., int count = 0; list.stream().peek(e -> count++);) considered a "cardinal sin" in parallel streams?


What happens to the thread execution and the final result if you attempt this in a parallelStream()?
  The Race Condition: In a parallelStream(), the JVM splits your data across multiple threads (using the ForkJoinPool). 
  If you try to update an external int count, you are performing a non-atomic read-modify-write(RMW) operation. 
  Multiple threads will read the same value of count, increment it, and write it back, causing "lost updates."

  Thread Interference: Even if you use an AtomicInteger, you are effectively serializing the parallel process. 
  You are forcing threads to wait on each other to update the counter, which completely negates the performance 
  benefit of parallelStream().

  Order Nondeterminism: Because parallel streams process elements in chunks across threads, you have no guarantee of the order 
  in which the side effect occurs.

   */
        


        // Find the first employee with salary greater than 1500
        // employees.stream()
        // .filter(emp -> emp.getSalary() > 1500).findFirst()
        // .ifPresent(System.out::println); // Employee{id=2, name='Jane', salary=2000.0}

        //employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).forEach(System.out::println);
        // Employee [empid=3, name=Bob, salary=3000.0]
        // Employee [empid=2, name=Jane, salary=2000.0]
        // Employee [empid=1, name=John, salary=1000.0]

        
        // Sort by salary, then by name if salaries are equal
        /*
        employees.stream().sorted(( Employee emp1, Employee emp2) -> {
            if (emp1.getSalary() > emp2.getSalary()) {
                return 1;
            } else if (emp1.getSalary() < emp2.getSalary()) {
                return 0;
            } else {
                return emp1.getName().compareTo(emp2.getName());
            }
        }).forEach(System.out::println);
        */

        /* 
                //Grouping by length
             
        List<String> animals = List.of("dog", "cat", "cow", "tiger");
        var groupedAnimals = animals.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(groupedAnimals); //{3=[dog, cat, cow], 5=[tiger]}
        */

        //List.of(), which was introduced in Java 9
        /*
        What to Follow in the Current Market
        In the current market, List.of() is the industry standard for modern Java development (Java 11, 17, 21, and beyond).

        Why you should default to List.of():
        Immutability by Default: Modern software engineering values immutability because it prevents unintended side effects, makes your code inherently thread-safe, and reduces bugs.

        Readability: List.of(...) is shorter, cleaner, and clearly signals to other developers that this list is static and should not be modified.

        Performance: For small numbers of elements, List.of() uses highly optimized, low-overhead internal classes that are faster and use less memory than wrapping an array.

        When to still use Arrays.asList():
        You are working on a legacy codebase running on Java 8 or older.

        You explicitly need a fixed-size list where you plan to modify elements in place via .set().

        You need to allow null values inside the list.
        */
        // List<Integer> numbers = List.of(1, 2, 2,3, 4,1, 5);
        // //var maxNumber = numbers.stream().collect(Collectors.groupingBy(n -> n)); // {1=[1, 1], 2=[2, 2], 3=[3], 4=[4], 5=[5]}
        // var maxNumber = numbers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // {1=2, 2=2, 3=1, 4=1, 5=1}
        // System.out.println(maxNumber);
        

        
        /*
            Why this matters
            This is a perfect example of functional programming in Java:

            Declarative: You aren't writing a loop, setting a max variable, and checking every item manually.

            Safe: By using ifPresent, you avoid errors if the list is empty (no null checks required).

            Flexible: If you wanted to find the shortest string, you would simply change .max(...) to .min(...)—the rest of your logic stays exactly the same.

            Does the comparison logic (a, b) -> a.length() - b.length() make sense, or would you like to see how to handle a tie (i.e., what happens if two languages have the same length)?
        */

/*
        
        Feature,partitioningBy,groupingBy
        Logic,Boolean (True/False),"Any key (e.g., Remainder, Length, Type)"
        Return Type,"Map<Boolean, List<T>>","Map<K, List<T>>"
        Use Case,"Even vs. Odd, Pass vs. Fail",Categorizing into multiple groups

        import java.util.stream.Collectors;

public class GroupingExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Grouping by remainder (n % 3)
        Map<Integer, List<Integer>> grouped = numbers.stream()
            .collect(Collectors.groupingBy(n -> n % 3));

        // Result: {0=[3, 6, 9], 1=[1, 4, 7], 2=[2, 5, 8]}
        System.out.println(grouped);
    }
}
Key Takeaways for your learning:partitioningBy is a specialized, high-performance version of groupingBy designed specifically for True/False predicates.groupingBy is the "Swiss Army Knife" for categorization. You can even chain them to create nested groups (e.g., group by even/odd, and then by whether they are greater than 10).Does seeing the difference between these two approaches help clarify how you should structure your data processing in the future?
        */
        
    }
}
