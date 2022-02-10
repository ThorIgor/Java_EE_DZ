package com.example.demo.controler;

import com.example.demo.model.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    ArrayList<BookDto> list = new ArrayList<BookDto>();

    @GetMapping("/create")
    public String bookFormGet() {
        return "book-create";
    }

    @PostMapping("/create")
    public String saveBook(BookDto bookDto) {
        list.add(bookDto);
        return "redirect:/book/list";
    }

    @GetMapping("/list")
    public String bookListGet(Model model) {
        model.addAttribute("list", list);
        return "book-list";
    }
}
