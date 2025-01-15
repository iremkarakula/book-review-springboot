package com.project.bookreview.repository;


import com.project.bookreview.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

   List<Review> findByBookId(long id);
}
