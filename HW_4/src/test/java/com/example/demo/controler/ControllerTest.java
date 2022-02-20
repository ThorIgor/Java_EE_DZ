package com.example.demo.controler;

import com.example.demo.model.BookDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void clearBookList() throws Exception {
        mockMvc.perform(get("/clear"));
    }

    @Test
    public void createBookTest() throws Exception {
        BookDto book = new BookDto("math","000","drin");
        mockMvc.perform(post("/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Lists.newArrayList(book))));
    }

    @Test
    void getAllBooksTest() throws Exception {
        List<BookDto> list = new ArrayList<BookDto>();
        BookDto book = new BookDto("math","000","drin");
        list.add(book);
        mockMvc.perform(post("/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());
        mockMvc.perform(get("/getAllBooks")).andExpect(status().isOk()).andExpect(
                content().json(objectMapper.writeValueAsString(list)));

    }

    @Test
    void searchTest() throws Exception {
        List<BookDto> list = new ArrayList<>();
        BookDto book1 = new BookDto("math","000","drin");
        BookDto book2 = new BookDto("biology","111","makitra");
        list.add(book1);
        mockMvc.perform(post("/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book1)))
                .andExpect(status().isOk());
        mockMvc.perform(post("/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book2)))
                .andExpect(status().isOk());
        mockMvc.perform(get("/search")
                        .param("findStr","math"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }
}
