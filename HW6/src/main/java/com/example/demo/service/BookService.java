package com.example.demo.service;

import com.example.demo.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final EntityManager entityManager;

    @Transactional
    public Book createBook(Book book) {
        return entityManager.merge(book);
    }

    @Transactional
    public ArrayList<Book> findBooks(String search){
        return (ArrayList<Book>) entityManager.createQuery("SELECT u FROM Book u WHERE u.author LIKE :query OR u.title LIKE :query OR u.isbn LIKE :query", Book.class)
                .setParameter("query", '%' + search + '%')
                .getResultList();
    }


    @Transactional
    public ArrayList<Book> findAllBooks(){
        ArrayList<Book> select_u_from_book_u = (ArrayList<Book>) entityManager.createQuery("SELECT u FROM Book u", Book.class).getResultList();
        return select_u_from_book_u;
    }

    @Transactional
    public Book findBookById(long id){
        return entityManager.find(Book.class, id);
    }

}
