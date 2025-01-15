package com.project.bookreview.controller;

import com.project.bookreview.dto.BookDto;
import com.project.bookreview.dto.BookResponse;
import com.project.bookreview.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    @Autowired
    private final BookService bookService;



    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable(name = "id") long id){
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BookResponse> getAllBooks(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return new ResponseEntity<>(bookService.getAllBook(pageNo, pageSize), HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.createBook(bookDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable(name = "id") long id, @RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.updateBook(id, bookDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(name = "id") long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>("Kitap Silindi", HttpStatus.OK);
    }


}
