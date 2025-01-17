package com.project.bookreview.controller;

import com.project.bookreview.dto.ReviewDto;
import com.project.bookreview.dto.UpdateUserRequest;
import com.project.bookreview.dto.UserResponseDto;
import com.project.bookreview.entity.User;
import com.project.bookreview.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable long id){
        return userService.getUser(id);
    }

    @GetMapping
    public List<UserResponseDto> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{userId}/reviews")
    public List<ReviewDto> getReviews(@PathVariable long userId){
        return userService.getReviews(userId);
    }

    @PutMapping("/{userId}")
    public UserResponseDto updateUser(@PathVariable long userId, @RequestBody UpdateUserRequest updateUserRequest){
        return userService.updateUser(userId, updateUserRequest);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId){
        userService.delete(userId);
        return new ResponseEntity<>("Hesap silindi", HttpStatus.OK);
    }


}
