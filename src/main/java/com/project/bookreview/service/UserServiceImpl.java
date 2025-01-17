package com.project.bookreview.service;

import com.project.bookreview.dto.ReviewDto;
import com.project.bookreview.dto.UpdateUserRequest;
import com.project.bookreview.dto.UserResponseDto;
import com.project.bookreview.entity.Review;
import com.project.bookreview.entity.User;
import com.project.bookreview.repository.ReviewRepository;
import com.project.bookreview.repository.UserRepository;
import com.project.bookreview.utils.Mapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private ReviewRepository reviewRepository;
    private Mapper mapper;

    @Override
    public UserResponseDto getUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
           return mapper.toUserResponseDto(user.get());
        }
        throw new UsernameNotFoundException("Kullanıcı bulunamadı");
    }

    @Override
    public List<UserResponseDto> getAll() {

         return userRepository.findAll().stream()
                 .map(user -> mapper.toUserResponseDto(user))
                 .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getReviews(long userId) {
        List<Review> review = reviewRepository.findByUserId(userId);
        return review.stream().map(r-> mapper.toReviewDto(r)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(long userId, UpdateUserRequest updateUserRequest) {
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("Kullanıcı bulunamadı");
        }
        User user = userOptional.get();

        user.setEmail(updateUserRequest.getEmail());
        user.setFullName(updateUserRequest.getFullName());

        userRepository.save(user);

        return mapper.toUserResponseDto(user);
    }

    @Override
    public void delete(long userId) {
        userRepository.deleteById(userId);
    }
}
