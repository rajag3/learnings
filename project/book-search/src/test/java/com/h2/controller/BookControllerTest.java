package com.h2.controller;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import com.h2.entity.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    public final TestRestTemplate restTemplate = null;
    
    @Test
    public void testSearchBooks() {
        String searchterm = "algorithims";
        ResponseEntity<Book[]> response = restTemplate.getForEntity("/books/search?query=" + searchterm, Book[].class);
        //System.out.println(response.getBody());
        assert response.getBody() != null;
    }
}
