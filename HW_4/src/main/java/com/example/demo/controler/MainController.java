package com.example.demo.controler;

import com.example.demo.model.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class MainController {

    @GetMapping("/")
    public String bookFormGet() {
        return "book-main";
    }


}
