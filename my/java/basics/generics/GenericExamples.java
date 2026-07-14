import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class GenericExamples {
	public static void main(String args[]) {
		/*
		Double[] rate = {1.1, 3.8};
		Integer[] numbers = {10,40,30};
		List<Integer> intList = convertArraytoList(numbers);
		List<Double> rateList = convertArraytoList(rate);
		intList.add(50);
		System.out.println(convertArraytoList(numbers));
		System.out.println(convertArraytoList(rate));
*/		
		public void print(List<String> list);
		public void print(List<Integer> list);

		
	}
	//
	public static <T> void copyElements(List<? super T> destination, List<? extends T> source) {
    for (T item : source) {
        destination.add(item);
    }
}
	//public static <T extends Number> List<T> convertArraytoList(T[] array ){
	public static <T> List<T> convertArraytoList(T[] array ){
		return Arrays.asList(array);
	}
	
	
	/*
	public static void main(String args[]) {
		String[] fruits = {"apple", "orange", "kiwi"};
		Integer[] numbers = {10,40,30};
		
		System.out.println(convertArraytoList(fruits));
		System.out.println(convertArraytoList(numbers));
		
	}
	public static <T> List<T> convertArraytoList(T[] array ){
		return Arrays.asList(array);
	}
	*/
} 