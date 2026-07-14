package my.java.basics.utils;
import java.util.Arrays;
import my.java.basics.utils.NumberUtils;

public class Main {
    public static void main(String[] args) {
        Integer[] numIntegers = {1, 3, 2, 4};

        // Option A: Using Stream with Method Reference
        Arrays.stream(numIntegers)
              .forEach(num -> System.out.println(num + " is Odd: " + NumberUtils.isOdd(num)));
    }
}