package com.h2.controller;

import org.springframework.web.bind.annotation.RestController;

import com.h2.entity.Book;
import com.h2.service.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String searchTerm) {
        System.out.println("Searching for books with term Controller:::::::::::::::::::::::: " + searchTerm);
        return bookService.searchBySearchVector(searchTerm);
    }
}
