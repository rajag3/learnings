package com.h2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.h2.repository.BookRepository;
import java.util.List;
import com.h2.entity.Book;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    
    public List<Book> searchBySearchVector(String searchTerm) {
        System.out.println("Searching for books with term Service:::::::::::::::::::::::: " + searchTerm);
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be null or empty");
        }
        return bookRepository.searchBySearchVector(searchTerm);
    }
}
