package com.example.demo;

import com.example.demo.model.BookDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
    @Autowired
    ObjectMapper objectMapper;

    @LocalServerPort
    void setPort(int port){
        RestAssured.port = port;
    }

    @BeforeEach
    public void clearBookList() {
        RestAssured.when().get("/clear");
    }

    @Test
    public void shouldCreateBook() {
        BookDto book = new BookDto("math", "000", "drin");
        List<BookDto> list = Collections.singletonList(book);
        List<BookDto> createList = RestAssured
                .given()
                .contentType("application/json")
                .body(book)
                .when()
                .post("/create")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .jsonPath()
                .getList("$", BookDto.class);
        assertEquals(createList, list);
    }

    @Test
    public void shouldReturnBooks() {
        BookDto book = new BookDto("math", "000", "drin");
        List<BookDto> list = Collections.singletonList(book);
        RestAssured
                .given()
                .contentType("application/json")
                .body(book)
                .when()
                .post("/create");
        List<BookDto> bookList = RestAssured
                .when()
                .get("/getAllBooks")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .jsonPath()
                .getList("$", BookDto.class);
        assertEquals(bookList, list);
    }

    @Test
    public void shouldReturnBookByParam() {
        BookDto book1 = new BookDto("math", "000", "drin");
        BookDto book2 = new BookDto("biology", "111", "makar");
        RestAssured
                .given()
                .contentType("application/json")
                .body(book1)
                .when()
                .post("/create");
        RestAssured
                .given()
                .contentType("application/json")
                .body(book2)
                .when()
                .post("/create");
        List<BookDto> list = RestAssured
                .given()
                .param("findStr","math")
                .when()
                .get("/search")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .jsonPath()
                .getList("$", BookDto.class);
        assertEquals(Collections.singletonList(book1), list);
    }
}
