import java.util.List;

public class ProblemWithGeneric {
	public static void main(String arg[]){
		System.out.println("Inside main");
	}
	
	public static void print(List<String> list){
		System.out.println(list);
	}
	
	public static void print(List<Integer> list){
		System.out.println(list);
	}
	
	/*
		C:\Users\goraja\learnings\my\java\basics\generics>javac ProblemWithGeneric.java
ProblemWithGeneric.java:12: error: name clash: print(List<Integer>) and print(List<String>) have the same erasure
        public static void print(List<Integer> list){
                           ^
1 error

	*/
	
	/*
	// solution for the above error 
	// 1. just keep printString, printInteger.
	// 2. The "Helper" / "Dispatch" Pattern: If the logic inside is similar, use a single generic method and use instanceof (if checking the contents) or a Type Token/Class object to handle the logic.
	  public <T> print(List<T> list){
		  for(T item: list){
			 System.out.println(item); 
		  }
	  }
	  3. just check the type of obj and do the business logic
	  
	  public void print(List<?> list) {
		  if(list.get(0) instanceof String ) {
			  // handle String logic
		  } else if (list.get(0) instanceof Integer) {
			  // handle Integer logic
		  }
		  
	  }
	 /*
}