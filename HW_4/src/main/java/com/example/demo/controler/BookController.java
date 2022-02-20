package com.example.demo.controler;

import com.example.demo.model.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {
    ArrayList<BookDto> list = new ArrayList<BookDto>();

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ArrayList<BookDto>> saveBook(
            @RequestBody final BookDto bookDto) {
        list.add(bookDto);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<BookDto>> findBooks(
            @RequestParam final String findStr) {
        List<BookDto> findList = list.stream().filter(book -> book.getTitle().contains(findStr) || book.getIsbn().contains(findStr)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(findList);
    }


    @RequestMapping(value = "/getAllBooks", method = RequestMethod.GET)
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public void clear() {
        list.clear();
    }
}
