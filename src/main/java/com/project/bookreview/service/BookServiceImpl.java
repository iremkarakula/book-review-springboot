package com.project.bookreview.service;

import com.project.bookreview.dto.BookDto;
import com.project.bookreview.dto.BookResponse;
import com.project.bookreview.entity.Book;
import com.project.bookreview.exceptions.BookException;
import com.project.bookreview.repository.BookRepository;
import com.project.bookreview.utils.Mapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;



@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService{


    @Autowired
    private final BookRepository bookRepository;



    @Override
    public BookResponse getAllBook(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Book> books = bookRepository.findAll(pageable);

        return Mapper.toBookResponse(books);
    }


    @Override
    public BookDto getBookById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new BookException("Kitap bulunamadı", HttpStatus.NOT_FOUND));
        return Mapper.toBookDto(book);
    }


    @Transactional
    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = Mapper.toBookEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return Mapper.toBookDto(savedBook);
    }


    @Transactional
    @Override
    public BookDto updateBook(long id, BookDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new BookException("Kitap bulunamadı", HttpStatus.NOT_FOUND));

        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setCategory(bookDto.getCategory());
        book.setPrice(bookDto.getPrice());
        book.setLanguage(bookDto.getLanguage());
        book.setPageCount(bookDto.getPageCount());
        book.setPublishedDate(bookDto.getPublishedDate());

        Book savedBook = bookRepository.save(book);
        return Mapper.toBookDto(savedBook);
    }

    @Transactional
    @Override
    public void deleteBook(long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new BookException("Kitap bulunamadı", HttpStatus.NOT_FOUND));
        bookRepository.deleteById(id);
    }
}
