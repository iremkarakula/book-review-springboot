package com.project.bookreview.utils;

import com.project.bookreview.entity.Review;
import com.project.bookreview.exceptions.ReviewException;
import com.project.bookreview.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@AllArgsConstructor
public class Validation {

    private ReviewRepository reviewRepository;

    public void validateReviewBelongsToBook(long bookId, long reviewId){
        Optional<Review> review = reviewRepository.findById(reviewId);
        if(review.isEmpty()){
            throw new ReviewException("Review bulunamadı", HttpStatus.NOT_FOUND);
        }
        if(review.get().getBook().getId() != bookId){
            throw new ReviewException("Review, bu kitaba ait değil", HttpStatus.NOT_FOUND);
        }

    }
}
