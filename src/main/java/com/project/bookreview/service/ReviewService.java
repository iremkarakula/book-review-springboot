package com.project.bookreview.service;

import com.project.bookreview.dto.ReviewDto;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewDto> getReviews(long bookId, Optional<Long> userId);

    ReviewDto createReview(long bookId, ReviewDto reviewDto);

    ReviewDto updateReview(long bookId, long reviewId, ReviewDto reviewDto);

    void deleteReview(long bookId, long reviewId);

}
