package com.h2.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;
    
    private String title;
    private String author;
    private BigDecimal rating;
    private String description;
    private String language;
    private String isbn;
    
    @Column(name = "book_format")
    private String bookFormat;
    
    private String edition;
    private Integer pages;
    private String publisher;
    
    @Column(name = "publish_date")
    private LocalDate publishDate;
    
    @Column(name = "first_publish_date")
    private LocalDate firstPublishDate;
    
    @Column(name = "linked_percent")
    private BigDecimal linkedPercent;
    
    private BigDecimal price;
    
    @Column(name = "search_vector", columnDefinition = "tsvector")
    private String searchVector;

    // Getters and Setters
    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public BigDecimal getRating() {
        return rating;
    }
    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getBookFormat() {
        return bookFormat;
    }
    public void setBookFormat(String bookFormat) {
        this.bookFormat = bookFormat;
    }
    public String getEdition() {
        return edition;
    }
    public void setEdition(String edition) {
        this.edition = edition;
    }
    public Integer getPages() {
        return pages;
    }
    public void setPages(Integer pages) {
        this.pages = pages;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public LocalDate getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
    public LocalDate getFirstPublishDate() {
        return firstPublishDate;
    }
    public void setFirstPublishDate(LocalDate firstPublishDate) {
        this.firstPublishDate = firstPublishDate;
    }
    public BigDecimal getLinkedPercent() {
        return linkedPercent;
    }
    public void setLinkedPercent(BigDecimal linkedPercent) {
        this.linkedPercent = linkedPercent;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getSearchVector() {
        return searchVector;
    }
    public void setSearchVector(String searchVector) {
        this.searchVector = searchVector;
    }

    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", isbn=" + isbn + "]";
    }
}
