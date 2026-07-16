package my.java.basics.io;

import java.util.Scanner;

public class ScannerExample{
	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		System.out.print("enter name:");
		System.out.println(scanner.nextLine());
		System.out.print("enter age:");
		System.out.println(scanner.nextInt()); // nextInt() will not read for nextLine()
        scanner.nextLine(); // so we have to explicitly need to provide nextLine()
        System.out.print("enter occupation:");
        System.out.println(scanner.nextLine());
	}
}