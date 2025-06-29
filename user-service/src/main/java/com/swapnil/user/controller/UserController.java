package com.swapnil.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapnil.user.UserDto;
import com.swapnil.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired private UserService userService;
    @Autowired private ObjectMapper objectMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("userId") Long userId){
        UserResponse userResponse = objectMapper.convertValue(userService.findById(userId), UserResponse.class);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
        UserDto userDto = userService.createUser(objectMapper.convertValue(request, UserDto.class));
        UserResponse userResponse = objectMapper.convertValue(userDto, UserResponse.class);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
