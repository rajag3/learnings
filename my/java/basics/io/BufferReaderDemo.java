package my.java.basics.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferReaderDemo {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\goraja\\learnings\\my\\java\\basics\\io\\example.txt"));
            System.out.println(reader.readLine());
            
            StringBuilder stringBuilder = new StringBuilder();
            //reader.lines().forEach(stringBuilder::append);
            reader.lines().forEach((line) -> System.out.println(line));
            //System.out.println("\n\n");
            //System.out.println(stringBuilder);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
}
