package com.h2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.h2.entity.Book;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    
    @Test
    public void testSearchBySearchVector() {
        List<Book> books = bookRepository.searchBySearchVector("Computing");
        assertTrue(books.size() > 0);
        // for (Book book : books) {
        //     System.out.println(book);
        // }
    }
}
