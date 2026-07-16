package com.h2;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbImporter {
    
    private static final String CSV_URL = "https://gist.githubusercontent.com/hhimanshu/d55d17b51e0a46a37b739d0f3d3e3c74/raw/5b9027cf7b1641546c1948caffeaa44129b7db63/books.csv";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/library";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "admin";

    public static void main(String[] args) {
        try {
            // Step 1: Download CSV data
            System.out.println("Downloading CSV data...");
            List<String[]> csvData = downloadCsvData(CSV_URL);
            System.out.println("Downloaded " + csvData.size() + " rows from CSV");

            // Step 2: Parse CSV data
            System.out.println("Parsing CSV data...");
            List<BookData> books = parseBookData(csvData);
            System.out.println("Parsed " + books.size() + " books");

            // Step 3: Insert into database
            System.out.println("Inserting data into database...");
            insertDataIntoDatabase(books);
            System.out.println("Data insertion completed successfully!");

        } catch (Exception e) {
            System.err.println("Error during import: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static List<String[]> downloadCsvData(String urlString) throws IOException {
        List<String[]> data = new ArrayList<>();
        URL url = new URL(urlString);
        
        try (CSVReader reader = new CSVReader(new InputStreamReader(url.openStream()))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                data.add(nextLine);
            }
        } catch (CsvValidationException e) {
            throw new IOException("CSV validation error", e);
        }
        
        return data;
    }

    private static List<BookData> parseBookData(List<String[]> csvData) {
        List<BookData> books = new ArrayList<>();
        
        // Skip header row
        for (int i = 1; i < csvData.size(); i++) {
            String[] row = csvData.get(i);
            
            // Check for at least 5 columns to be safe (up to description)
            if (row.length >= 5) {
                BookData book = new BookData();
                
                // row[0] is bookId - we ignore it because Postgres SERIAL handles IDs!
                book.setTitle(row[1]);
                book.setAuthor(row[2]);
                book.setRating(parseDecimal(row[3]));
                book.setDescription(row[4]);
                
                // Shift all remaining columns up by +1
                book.setLanguage(row.length > 5 ? row[5] : "English");
                book.setIsbn(row.length > 6 ? row[6] : "");
                book.setBookFormat(row.length > 7 ? row[7] : "Hardcover");
                book.setEdition(row.length > 8 ? row[8] : "1st");
                book.setPages(parseInteger(row.length > 9 ? row[9] : "0"));
                book.setPublisher(row.length > 10 ? row[10] : "");
                book.setPublishDate(parseDate(row.length > 11 ? row[11] : ""));
                book.setFirstPublishDate(parseDate(row.length > 12 ? row[12] : ""));
                book.setLinkedPercent(parseDecimal(row.length > 13 ? row[13] : "0"));
                book.setPrice(parseDecimal(row.length > 14 ? row[14] : "0"));
                
                books.add(book);
            }
        }
        
        return books;
    }

    private static void insertDataIntoDatabase(List<BookData> books) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            conn.setAutoCommit(false);
            
            // Insert authors first and get author IDs
            Map<String, Integer> authorMap = new HashMap<>();
            
            for (BookData book : books) {
                String authorName = book.getAuthor();
                if (!authorMap.containsKey(authorName)) {
                    int authorId = insertAuthor(conn, authorName);
                    authorMap.put(authorName, authorId);
                }
            }
            
            // Insert books and book-author relationships
            for (BookData book : books) {
                int bookId = insertBook(conn, book);
                int authorId = authorMap.get(book.getAuthor());
                insertBookAuthor(conn, bookId, authorId);
            }
            
            conn.commit();
            System.out.println("Transaction committed successfully!");
        }
    }

    private static int insertAuthor(Connection conn, String authorName) throws SQLException {
        String checkSql = "SELECT author_id FROM author WHERE name = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, authorName);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("author_id");
            }
        }
        
        String insertSql = "INSERT INTO author (name, bio, created_by, last_modified_by) VALUES (?, ?, 'system', 'system') RETURNING author_id";
        try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
            stmt.setString(1, authorName);
            stmt.setString(2, "Author of various books");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("author_id");
            }
        }
        
        throw new SQLException("Failed to insert author: " + authorName);
    }

    private static int insertBook(Connection conn, BookData book) throws SQLException {
        String insertSql = "INSERT INTO books (title, author, rating, description, language, isbn, book_format, edition, pages, publisher, publish_date, first_publish_date, linked_percent, price, created_by, last_modified_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'system', 'system') RETURNING book_id";
        
        try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setBigDecimal(3, book.getRating());
            stmt.setString(4, book.getDescription());
            stmt.setString(5, book.getLanguage());
            stmt.setString(6, book.getIsbn());
            stmt.setString(7, book.getBookFormat());
            stmt.setString(8, book.getEdition());
            stmt.setInt(9, book.getPages());
            stmt.setString(10, book.getPublisher());
            stmt.setDate(11, book.getPublishDate());
            stmt.setDate(12, book.getFirstPublishDate());
            stmt.setBigDecimal(13, book.getLinkedPercent());
            stmt.setBigDecimal(14, book.getPrice());
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("book_id");
            }
        }
        
        throw new SQLException("Failed to insert book: " + book.getTitle());
    }

    private static void insertBookAuthor(Connection conn, int bookId, int authorId) throws SQLException {
        String insertSql = "INSERT INTO book_author (book_id, author_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
            stmt.setInt(1, bookId);
            stmt.setInt(2, authorId);
            stmt.executeUpdate();
        }
    }

    private static java.math.BigDecimal parseDecimal(String value) {
        if (value == null || value.trim().isEmpty()) {
            return java.math.BigDecimal.ZERO;
        }
        try {
            return new java.math.BigDecimal(value.trim());
        } catch (NumberFormatException e) {
            return java.math.BigDecimal.ZERO;
        }
    }

    private static Integer parseInteger(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static Date parseDate(String value) {
        if (value == null || value.trim().isEmpty()) {
            return Date.valueOf(LocalDate.now());
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(value.trim(), formatter);
            return Date.valueOf(localDate);
        } catch (Exception e) {
            return Date.valueOf(LocalDate.now());
        }
    }

    static class BookData {
        private String title;
        private String author;
        private java.math.BigDecimal rating;
        private String description;
        private String language;
        private String isbn;
        private String bookFormat;
        private String edition;
        private Integer pages;
        private String publisher;
        private Date publishDate;
        private Date firstPublishDate;
        private java.math.BigDecimal linkedPercent;
        private java.math.BigDecimal price;

        // Getters and Setters
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }
        public java.math.BigDecimal getRating() { return rating; }
        public void setRating(java.math.BigDecimal rating) { this.rating = rating; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }
        public String getIsbn() { return isbn; }
        public void setIsbn(String isbn) { this.isbn = isbn; }
        public String getBookFormat() { return bookFormat; }
        public void setBookFormat(String bookFormat) { this.bookFormat = bookFormat; }
        public String getEdition() { return edition; }
        public void setEdition(String edition) { this.edition = edition; }
        public Integer getPages() { return pages; }
        public void setPages(Integer pages) { this.pages = pages; }
        public String getPublisher() { return publisher; }
        public void setPublisher(String publisher) { this.publisher = publisher; }
        public Date getPublishDate() { return publishDate; }
        public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }
        public Date getFirstPublishDate() { return firstPublishDate; }
        public void setFirstPublishDate(Date firstPublishDate) { this.firstPublishDate = firstPublishDate; }
        public java.math.BigDecimal getLinkedPercent() { return linkedPercent; }
        public void setLinkedPercent(java.math.BigDecimal linkedPercent) { this.linkedPercent = linkedPercent; }
        public java.math.BigDecimal getPrice() { return price; }
        public void setPrice(java.math.BigDecimal price) { this.price = price; }
    }
}
