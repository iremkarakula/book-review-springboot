package com.project.bookreview.service;

import com.project.bookreview.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto getReviewById(long bookId, long reviewId);
    List<ReviewDto> getReviewsByBookId(long bookId);
    ReviewDto createReview(long bookId, ReviewDto reviewDto);
    ReviewDto updateReview(long bookId, long reviewId, ReviewDto reviewDto);
    void deleteReview(long bookId, long reviewId);

}
