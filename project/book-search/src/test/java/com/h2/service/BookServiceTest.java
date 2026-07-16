package com.h2.service;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.h2.entity.Book;

@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService bookService;
    
    @Test
    public void testSearchBySearchVector() {
        String searchTerm = "";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bookService.searchBySearchVector(searchTerm);
        });
        assertEquals("Search term cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testSearchBySearchVectorWithValidSearchTerm() {
        String searchTerm = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bookService.searchBySearchVector(searchTerm);
        });
        assertEquals("Search term cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testSearchBySearchVectorWithEmptySearchTerm() {
        String searchTerm = "algorithms";
        // This should not throw an exception since the search term is valid
        List<Book> book = bookService.searchBySearchVector(searchTerm);
        //System.out.println("Search results for '" + searchTerm + "': " + book);
        assertTrue(book.size() > 0);
    }
}
