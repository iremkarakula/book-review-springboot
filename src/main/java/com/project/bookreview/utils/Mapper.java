package com.project.bookreview.utils;

import com.project.bookreview.dto.BookDto;
import com.project.bookreview.dto.BookResponse;
import com.project.bookreview.dto.ReviewDto;
import com.project.bookreview.entity.Book;
import com.project.bookreview.entity.Review;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.List;

@UtilityClass
public class Mapper {

    public Book toBookEntity(BookDto bookDto){
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setCategory(bookDto.getCategory());
        book.setPrice(bookDto.getPrice());
        book.setPublishedDate(bookDto.getPublishedDate());
        book.setPageCount(bookDto.getPageCount());
        book.setLanguage(bookDto.getLanguage());
        return book;
    }

    public BookDto toBookDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setCategory(book.getCategory());
        bookDto.setPrice(book.getPrice());
        bookDto.setPublishedDate(book.getPublishedDate());
        bookDto.setPageCount(book.getPageCount());
        bookDto.setLanguage(book.getLanguage());
        return bookDto;
    }

    public BookResponse toBookResponse(Page<Book> books){

        List<Book> bookList = books.getContent();
        List<BookDto> content = bookList.stream().map(Mapper::toBookDto).toList();

        BookResponse bookResponse = new BookResponse();
        bookResponse.setContent(content);
        bookResponse.setPageNo(books.getNumber());
        bookResponse.setPageSize(books.getSize());
        bookResponse.setTotalPages(books.getTotalPages());
        bookResponse.setTotalElements(books.getTotalElements());
        bookResponse.setLast(books.isLast());

        return bookResponse;

    }

    public Review toReviewEntity(ReviewDto reviewDto){

        Review review = new Review();
        review.setTitle(reviewDto.getTitle());
        review.setText(reviewDto.getText());
        review.setStars(reviewDto.getStars());

        return review;
    }

    public ReviewDto toReviewDto(Review review){

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setTitle(review.getTitle());
        reviewDto.setText(review.getText());
        reviewDto.setStars(review.getStars());

        return reviewDto;
    }
}
