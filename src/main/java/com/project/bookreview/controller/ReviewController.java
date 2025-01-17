package com.project.bookreview.controller;

import com.project.bookreview.dto.ReviewDto;
import com.project.bookreview.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/book/{bookId}/review")
public class ReviewController {

    @Autowired
    private final ReviewService reviewService;




    @GetMapping
    public ResponseEntity<List<ReviewDto>> getReviews(
            @PathVariable(name = "bookId") long bookId,
            @RequestParam(name = "userId", required = false) Optional<Long> userId) {

        return new ResponseEntity<>(reviewService.getReviews(bookId, userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@PathVariable(name = "bookId") long bookId,
                                                  @RequestBody ReviewDto reviewDto){

        return new ResponseEntity<>(reviewService.createReview(bookId, reviewDto ), HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(name = "bookId") long bookId,
                                                  @PathVariable(name = "reviewId") long reviewId,
                                                  @RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.updateReview(bookId, reviewId, reviewDto), HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable(name = "bookId") long bookId,
                                               @PathVariable(name = "reviewId") long reviewId){
        reviewService.deleteReview(bookId, reviewId);
        return new ResponseEntity<>("Review Silindi", HttpStatus.OK);
    }

}
