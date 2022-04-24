package com.example.demo;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    @Sql("/BookService/clean.sql")
    void createBookTest() {
        bookService.createBook(new Book());
        assertThat(bookService.findAllBooks()).hasSize(1);
    }

    @Test
    @Sql("/BookService/clean.sql")
    @Sql("/BookService/insert.sql")
    void findBooksTest() {
        assertThat(bookService.findBooks("BadTitle")).hasSize(2);
    }


    @Test
    @Sql("/BookService/clean.sql")
    @Sql("/BookService/insert.sql")
    void findAllBooksTest() {
        assertThat(bookService.findAllBooks()).hasSize(5);
    }

    @Test
    @Sql("/BookService/insert.sql")
    void findBookByIdTest() {
        assertThat(bookService.findBookById(1)).returns(1L, Book::getId).returns("Title1", Book::getTitle);
    }
}
