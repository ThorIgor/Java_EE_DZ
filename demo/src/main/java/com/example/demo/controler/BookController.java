package com.example.demo.controler;

import com.example.demo.model.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {
    @GetMapping("/create")
    public String bookFormGet(Model model) {
        BookDto bookDto = new BookDto();
        model.addAttribute("bookDto", bookDto);
        return "book-create";
    }

    @PostMapping("/create")
    public String saveBook(BookDto bookDto, Model model) {
        model.addAttribute("newBook", bookDto);
        return "book-create";
    }
}
