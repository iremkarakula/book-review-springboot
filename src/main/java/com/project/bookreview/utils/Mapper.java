package com.project.bookreview.utils;

import com.project.bookreview.dto.BookDto;
import com.project.bookreview.dto.BookResponse;
import com.project.bookreview.dto.ReviewDto;
import com.project.bookreview.dto.UserResponseDto;
import com.project.bookreview.entity.Book;
import com.project.bookreview.entity.Review;
import com.project.bookreview.entity.User;
import com.project.bookreview.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class Mapper {

    private BookRepository bookRepository;



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
        List<BookDto> content = bookList.stream().map(this::toBookDto).toList();

        BookResponse bookResponse = new BookResponse();
        bookResponse.setContent(content);
        bookResponse.setPageNo(books.getNumber());
        bookResponse.setPageSize(books.getSize());
        bookResponse.setTotalPages(books.getTotalPages());
        bookResponse.setTotalElements(books.getTotalElements());
        bookResponse.setLast(books.isLast());

        return bookResponse;

    }



    public ReviewDto toReviewDto(Review review){

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setTitle(review.getTitle());
        reviewDto.setText(review.getText());
        reviewDto.setStars(review.getStars());

        return reviewDto;
    }

    public UserResponseDto toUserResponseDto(User user){

        UserResponseDto userDto = new UserResponseDto();
        userDto.setEmail(user.getEmail());
        userDto.setFullName(user.getFullName());
        userDto.setReviews(user.getReviews());

        return userDto;
    }
}
