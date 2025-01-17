package com.project.bookreview.service;

import com.project.bookreview.dto.BookDto;
import com.project.bookreview.dto.BookResponse;

public interface BookService {

    BookDto createBook(BookDto bookDto);
    BookResponse getAllBook(int pageNo, int pageSize);
    BookDto getBookById(long id);
    BookDto updateBook(long id, BookDto bookDto);
    void deleteBook(long id);


}
