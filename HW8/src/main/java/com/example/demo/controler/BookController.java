package com.example.demo.controler;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    UserRepo userRepo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String bookFormGet(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "book_main";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }

    @RequestMapping(value = "/save_user", method = RequestMethod.POST)
    public String saveUser(@RequestParam(name = "login") String login,
                         @RequestParam(name = "password") String password) {
        userRepo.save(User.builder().login(login).password(password).build());
        return "redirect:/login";
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

    @RequestMapping(value = "/wishlist", method = RequestMethod.GET)
    public String showHtml(Model model, Authentication authentication){
        User user = userRepo.findByLogin(authentication.getName()).get();
        List<Book> wishList = user.getBooks();
        model.addAttribute("books", wishList);
        return "book_wish";
    }

    @RequestMapping(value = "/add_to_wishlist/{id}", method = RequestMethod.GET)
    public String addDeleteWishList(Model model, @PathVariable(name="id") long id, Authentication authentication){
        User user = userRepo.findByLogin(authentication.getName()).get();
        List<Book> wishList = user.getBooks();
        if (wishList.stream().anyMatch(book -> book.getId()==id)) {
            wishList = wishList.stream().dropWhile(book -> book.getId()==id).collect(Collectors.toList());
        } else {
            wishList.add(bookService.findBookById(id));
        }
        user.setBooks(wishList);
        userRepo.save(user);
        return "redirect:/";
    }
}
