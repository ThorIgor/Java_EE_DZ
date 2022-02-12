package com.example.demo.controler;

import com.example.demo.model.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/book")
public class BookController {
    ArrayList<BookDto> list = new ArrayList<BookDto>();

    @GetMapping("/main")
    public String bookFormGet(Model model) {
        model.addAttribute("list", list);
        return "book-main";
    }

    @PostMapping("/main")
    public String saveBook(BookDto bookDto, Model model) {
        list.add(bookDto);
        return "redirect:/book/main";
    }
}
