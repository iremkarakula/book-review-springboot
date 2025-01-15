package com.project.bookreview.service;

import com.project.bookreview.dto.ReviewDto;
import com.project.bookreview.entity.Book;
import com.project.bookreview.entity.Review;
import com.project.bookreview.exceptions.BookException;
import com.project.bookreview.exceptions.ReviewException;
import com.project.bookreview.repository.BookRepository;
import com.project.bookreview.repository.ReviewRepository;
import com.project.bookreview.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private final ReviewRepository reviewRepository;

    @Autowired
    private final BookRepository bookRepository;

    @Override
    public ReviewDto getReviewById(long bookId, long reviewId) {
        Review review =reviewRepository.findById(reviewId).orElseThrow(()-> new ReviewException("Review bulunamadı", HttpStatus.NOT_FOUND));
        Book book = bookRepository.findById(bookId).orElseThrow(()->new BookException("Kitap bulunamadı", HttpStatus.NOT_FOUND));

        if(review.getBook().getId() != bookId){
            throw new ReviewException("Review bu kullanıcıya ait değil", HttpStatus.NOT_FOUND);
        }
        return Mapper.toReviewDto(review);
    }

    @Override
    public ReviewDto createReview(long bookId, ReviewDto reviewDto) {
        Review review = Mapper.toReviewEntity(reviewDto);
        Book book = bookRepository.findById(bookId).orElseThrow(()->new BookException("Kitap bulunamadı", HttpStatus.NOT_FOUND));

        review.setBook(book);
        Review saved = reviewRepository.save(review);

        return Mapper.toReviewDto(saved);
    }

    @Override
    public List<ReviewDto> getReviewsByBookId(long bookId) {
        List<Review> reviewList = reviewRepository.findByBookId(bookId);
        return reviewList.stream().map(Mapper::toReviewDto).toList();
    }

    @Override
    public ReviewDto updateReview(long bookId, long reviewId, ReviewDto reviewDto) {
        Review review =reviewRepository.findById(reviewId).orElseThrow(()-> new ReviewException("Review bulunamadı", HttpStatus.NOT_FOUND));
        Book book = bookRepository.findById(bookId).orElseThrow(()->new BookException("Kitap bulunamadı", HttpStatus.NOT_FOUND));

        if(review.getBook().getId() != bookId){
            throw new ReviewException("Review bu kullanıcıya ait değil", HttpStatus.NOT_FOUND);
        }

        review.setTitle(reviewDto.getTitle());
        review.setText(reviewDto.getText());
        review.setStars(reviewDto.getStars());
        reviewRepository.save(review);
        return Mapper.toReviewDto(review);
    }

    @Override
    public void deleteReview(long bookId, long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(()->new ReviewException("Review bulunamadı", HttpStatus.NOT_FOUND));
        Book book = bookRepository.findById(bookId).orElseThrow(()->new BookException("Kitap bulunamadı", HttpStatus.NOT_FOUND));

        if(review.getBook().getId() != book.getId()){
            throw new ReviewException("Review bu kullanıcıya ait değil", HttpStatus.NOT_FOUND);
        }
        reviewRepository.deleteById(reviewId);
    }
}
