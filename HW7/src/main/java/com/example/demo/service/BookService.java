package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;


@Service
@AllArgsConstructor
public class BookService {

    private BookRepo bookRepo;

    @Transactional
    public Book createBook(Book book) {
        return bookRepo.save(book);
    }

    @Transactional
    public ArrayList<Book> findBooks(String search){
        return (ArrayList<Book>) bookRepo.findBookByTitleContainsOrIsbnContains(search, search);
    }

    @Transactional
    public ArrayList<Book> findAllBooks(){
        return (ArrayList<Book>) bookRepo.findAll();
    }

    @Transactional
    public Book findBookById(long id){
        return bookRepo.findById(id).get();
    }

}
