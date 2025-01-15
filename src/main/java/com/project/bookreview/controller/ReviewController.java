package com.project.bookreview.controller;

import com.project.bookreview.dto.ReviewDto;
import com.project.bookreview.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReviewController {

    @Autowired
    private final ReviewService reviewService;

    @GetMapping("/book/{bookId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(name = "bookId") long bookId,
                                                   @PathVariable(name = "reviewId") long reviewId){
        return new ResponseEntity<>(reviewService.getReviewById(bookId, reviewId),HttpStatus.OK);
    }


    @GetMapping("/book/{bookId}/reviews")
    public ResponseEntity<List<ReviewDto>> getReviewsByBookId(@PathVariable(name = "bookId") long bookId){
        return new ResponseEntity<>(reviewService.getReviewsByBookId(bookId), HttpStatus.OK);
    }

    @PostMapping("/book/{bookId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(name = "bookId") long bookId,
                                                  @RequestBody ReviewDto reviewDto){

        return new ResponseEntity<>(reviewService.createReview(bookId, reviewDto ), HttpStatus.OK);
    }

    @PutMapping("/book/{bookId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(name = "bookId") long bookId,
                                                  @PathVariable(name = "reviewId") long reviewId,
                                                  @RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.updateReview(bookId,reviewId, reviewDto), HttpStatus.OK);
    }

    @DeleteMapping("/book/{bookId}/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable(name = "bookId") long bookId,
                                               @PathVariable(name = "reviewId") long reviewId){
        reviewService.deleteReview(bookId, reviewId);
        return new ResponseEntity<>("Review Silindi", HttpStatus.OK);
    }

}
