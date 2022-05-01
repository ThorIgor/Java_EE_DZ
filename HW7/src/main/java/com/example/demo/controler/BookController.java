package com.example.demo.controler;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String bookFormGet(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "book_main";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ArrayList<Book>> saveBook(
            @RequestBody final Book book) {
        bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllBooks());
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> findBooks(
            @RequestParam final String findStr) {
        if (findStr.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllBooks());
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findBooks(findStr));
    }

    @RequestMapping(value = "/getAllBooks", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllBooks());
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public String showHtmlBook(Model model, @PathVariable(name="id") long id){
        Book book = bookService.findBookById(id);
        if (book != null) {
            model.addAttribute("book", book);
            return "book";
        }
        return "wrong_book_id";
    }
}
