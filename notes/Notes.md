# Learning Notes

____________________________________________________________________________________________________________
**design patterns**:
--> Transactional Outbox Pattern
 The Transactional Outbox Pattern is an architectural design pattern used in event-driven microservices to solve the dual-write problem. It ensures absolute data consistency by guaranteeing that a local database update and the publishing of a corresponding integration event to a message broker (like Apache Kafka or RabbitMQ) happen atomically within a single transaction
____________________________________________________________________________________________________________
**streams**: java 8 feature
powerfull and more readable code using streams
 --> intermediate operations: filter(), sorted(), map()
 --> terminal operations : collect(), forEach()

 Filtering first and then sorting (filter().sorted()) is universally considered the best practice.Chaining your stream operations in this order significantly optimizes both performance and memory management.Why filter() Then sorted() Is BestFewer Elements to Sort: Sorting is an expensive computational task with a time complexity of \(O(N \log N)\). By filtering out unwanted data first, you reduce \(N\) to a much smaller number, making the sorting process significantly faster.Reduced Memory Footprint: The filter() operation is stateless and processes elements lazily one by one. Conversely, sorted() is a stateful intermediate operation; it must buffer and dump the entire collection into memory horizontally before it can sort them. Filtering first ensures you do not waste memory buffering elements you intend to discard
____________________________________________________________________________________________________________
**Thread**:
 --> a ligh-weight subprocess that enables concurrent execution.
 --> each thread has its own execution environment
 --> has its own stack and local variables
 --> threads can communicate with each other
 --> threads share mem with each other
 --> This maximize the cpu utilization and keeps programs responsive.
____________________________________________________________________________________________________________

**Method Reference**: java 8 feature
 --> provides shorthand notation for a Lambda Expression to call an existing method.
 --> Cleaner and more readable.
 static method example: Integer::parseInt
 instance method: System.out::println
 Constructure: ArrayList::new
  list.forEach(item -> System.out.println(item)); // using lambda expression
  list.forEach(System.out::println); // using method reference

____________________________________________________________________________________________________________
# Collections:
 --> LinkedList
    A LinkedList in Java is a part of the java.util package and is a doubly-linked list implementation of the List and Deque interfaces. Unlike an ArrayList, which uses a dynamic array internally, a LinkedList stores elements in nodes where each node contains the data and two pointers: one to the next node and one to the previous node.

    How it Works (Under the Hood)
    Imagine a treasure hunt where every clue gives you the location of the next clue and the previous one. That is a LinkedList.

    Key Characteristics
        1) Dynamic Memory: It does not require a contiguous block of memory. Nodes are scattered in heap memory, connected by pointers.

        2) Performance Trade-off:

         --> Additions/Removals: Extremely fast (O(1)) if you are at the position (head or tail), because you just update the pointers.

         --> Access: Slow (O(n)) because you have to traverse from the head or tail to reach a specific index.

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

        Enterprise Perspective: Why it is often not chosen
        Despite the theoretical O(1) performance for insertions, ArrayList is preferred in the vast majority of real-world Spring Boot applications. Why?

        CPU Cache Locality: ArrayList stores data contiguously in memory, which is much faster for modern CPU caches compared to the "pointer hopping" required by LinkedList.

        Memory Footprint: LinkedList creates a new Node object for every single element added, significantly increasing garbage collection pressure.

        Would you like to see how LinkedList acts as a Deque (Double-Ended Queue) in scenarios like building a task processing queue in a microservice, or are you interested in the memory overhead details?
        ------------------
 -->         

____________________________________________________________________________________________________________
## Spring Boot

### Create new Spring Boot application
```bash
mvn archetype:generate -DgroupId=com.h2 -DartifactId=book-search -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

### Run the application
```bash
cd book-search
mvn spring-boot:run
```

### Build the application
```bash
mvn clean package
```

### Test the application
```bash
curl http://localhost:8080/hello
```

---

## Docker

### Start PostgreSQL container
```bash
docker-compose up -d
```

### List running containers
```bash
docker ps
```

### Connect to PostgreSQL database
```bash
docker exec -it library-db psql -U admin -d library
```
- `-i` → interactive mode
- `-T` → terminal mode

---

## CompletableFuture Exception Handling

In multi-threaded environments, exceptions thrown in background threads may not be visible to the main thread or propagate to it.

Exceptions are wrapped in `CompletionException`. To handle them properly, use the following pipeline methods:

1. **exceptionally()** - Acts like a catch block, used to log errors and provide fallback values so the pipeline can continue
2. **handle()** - Acts like a finally block, always executes, giving access to both success (if any) and failure/exception (if any) cases
3. **whenComplete()** - Used mainly for logging/cleanup operations; unlike handle, it doesn't allow you to modify the result

---

## Additional Notes

*Add new topics here as you learn them*
    **generics in java**
     --> substitution
     --> wild-card (?)
     Producer Extends Consumer Super(PECS)
     import java.util.List;
     import java.util.ArrayList;

        public class PecsExample {
            // src 'produces' Ts, dest 'consumes' Ts
            public static <T> void copy(List<? extends T> src, List<? super T> dest) {
                for (T item : src) {
                    dest.add(item);
                }
            }

            public static void main(String[] args) {
                List<Integer> integers = new ArrayList<>(List.of(1, 2, 3));
                List<Number> numbers = new ArrayList<>();

                // Safe because Integer extends Number
                copy(integers, numbers); 
            }
        }

**Fragile Base Class Problem**
        Use the final Keyword: If a class is not specifically designed to be extended, mark it or its overridable methods as final. This strictly prohibits inheritance and prevents the Fragile Base Class problem altogether.

        The "fragile" design pattern usually refers to the Fragile Base Class Problem, an anti-pattern in object-oriented programming where changes to a base class unintentionally break derived classes. This occurs due to tight inheritance coupling. You can prevent it by favoring composition over inheritance and using

        Follow the Open/Closed Principle (OCP): Design your code so that classes are open for extension but closed for modification

        to fix this fragile 

**Question 5: Bridge Methods (The "Under the Hood" Specialist Question)**
If you have a compiled class file, you can run javap -v StringBox.class in your terminal. You will see a method marked with the ACC_BRIDGE and ACC_SYNTHETIC flags.

ACC_BRIDGE: Tells the compiler/JVM this is a compiler-generated method for type safety.

ACC_SYNTHETIC: Tells the compiler this was not in your original source code.

You've successfully cleared the 5-question gauntlet! You've covered PECS, Type Erasure/Overloading, Array constraints, Reflection/Fragility, and now Bridge Methods.

Since you are at the 10+ year experience mark, are there any other specific "pain points" or "under the hood" Java behaviors you've encountered that you'd like to demystify, or shall we wrap up our deep dive into Generics?


______________________________________________________________________________________________________
Question 3: Short-Circuiting vs. Non-Short-Circuiting
In a stream pipeline, the order of operations is the difference between a high-performance service and one that hangs.

Question: Explain the performance difference between limit() and sorted(). If you have a massive dataset of 1,000,000 transactions and you only need the "top 5" largest ones, why does the placement of these two methods in the pipeline change the execution time from "fast" to "extremely slow"?

How would you structure that pipeline?