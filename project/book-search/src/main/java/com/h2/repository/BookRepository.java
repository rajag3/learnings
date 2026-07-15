package com.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.h2.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM books b WHERE b.search_vector @@ to_tsquery(:searchTerm)", nativeQuery = true)
    List<Book> searchBySearchVector(@Param("searchTerm") String searchTerm);
}
