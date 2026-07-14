package my.java.basics.utils;

public class NumberUtils {
    public static boolean isOdd(int n){
        return (n & 1) == 0;
    }

    public static boolean isEven(int n){
        return (n & 1) != 0;
    }
}
