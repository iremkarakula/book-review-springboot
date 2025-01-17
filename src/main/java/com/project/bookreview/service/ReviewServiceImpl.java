package com.project.bookreview.service;

import com.project.bookreview.dto.ReviewDto;
import com.project.bookreview.entity.Book;
import com.project.bookreview.entity.Review;
import com.project.bookreview.entity.User;
import com.project.bookreview.exceptions.BookException;
import com.project.bookreview.exceptions.ReviewException;
import com.project.bookreview.repository.BookRepository;
import com.project.bookreview.repository.ReviewRepository;
import com.project.bookreview.repository.UserRepository;
import com.project.bookreview.utils.Mapper;
import com.project.bookreview.utils.Validation;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final BookRepository bookRepository;

    private UserRepository userRepository;

    private Validation validation;
    private Mapper mapper;


    @Override
    public List<ReviewDto> getReviews(long bookId, Optional<Long> userId) {

        List<Review> list;

       if(userId.isPresent()){
           list = reviewRepository.findByBookIdAndUserId(bookId, userId);
       } else {
           list = reviewRepository.findByBookId(bookId);
       }

        return list.stream().map(mapper::toReviewDto).collect(Collectors.toList());
    }


    @Transactional
    @Override
    public ReviewDto createReview(long bookId, ReviewDto reviewDto) {

        Book book = bookRepository.findById(bookId).orElseThrow(()->new BookException("Kitap bulunamadı", HttpStatus.NOT_FOUND));
        User user = userRepository.findById(reviewDto.getUserId()).orElseThrow(()->new UsernameNotFoundException("Kullanıcı bulunamadı"));

            Review review = new Review();

            review.setTitle(reviewDto.getTitle());
            review.setText(reviewDto.getText());
            review.setStars(reviewDto.getStars());
            review.setUser(user);
            review.setBook(book);

            return mapper.toReviewDto(reviewRepository.save(review));
    }


    @Transactional
    @Override
    public ReviewDto updateReview(long bookId, long reviewId, ReviewDto reviewDto) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(()->new ReviewException("Review bulunamadı", HttpStatus.NOT_FOUND));

        validation.validateReviewBelongsToBook(bookId, reviewId);

        review.setTitle(reviewDto.getTitle());
        review.setText(reviewDto.getText());
        review.setStars(reviewDto.getStars());

        return mapper.toReviewDto(reviewRepository.save(review));
    }

    @Transactional
    @Override
    public void deleteReview(long bookId, long reviewId) {

        validation.validateReviewBelongsToBook(bookId, reviewId);

        reviewRepository.deleteById(reviewId);
    }
}
