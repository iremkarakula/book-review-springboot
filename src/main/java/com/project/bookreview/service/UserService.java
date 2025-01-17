package com.project.bookreview.service;


import com.project.bookreview.dto.ReviewDto;
import com.project.bookreview.dto.UpdateUserRequest;
import com.project.bookreview.dto.UserResponseDto;

import java.util.List;

public interface UserService {

        UserResponseDto getUser(long id);

        List<UserResponseDto> getAll();

        List<ReviewDto> getReviews(long userId);

        UserResponseDto updateUser(long userId, UpdateUserRequest updateUserRequest);

        void delete(long userId);
}
