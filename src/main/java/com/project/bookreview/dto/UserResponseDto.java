package com.project.bookreview.dto;

import com.project.bookreview.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String fullName;

    private String email;

    private List<Review> reviews;
}
