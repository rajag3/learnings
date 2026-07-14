package my.java.basics.collections_demo;

import java.util.HashMap;
import java.lang.Integer;
public class HashMapDemo {
    public static void main(String[] args) {
        //HashMap is unordered, LinedHashMap is ordered
        HashMap<String, Integer> basket = new HashMap<>();
        basket.put("apple", 3);
        basket.put("apple", 2);

        // keys are inside hashmap are uniqu
        System.out.println(basket); // {apple=2}

        basket.put("orange", 2);
        if(basket.containsKey("apple")){
            System.out.println(basket.get("apple"));
        }
        System.out.println(basket);
        //2 + 1
        basket.merge("apple", 1, Integer::sum);  //3
        System.out.println("af merge apple:"+basket.get("apple"));
        basket.merge("banana", 1, Integer::sum);

        System.out.println("final: "+basket);
    }

}
