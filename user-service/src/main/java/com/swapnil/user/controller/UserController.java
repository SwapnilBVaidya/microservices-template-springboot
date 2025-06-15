package com.swapnil.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("userId") String userId){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userId);
        userResponse.setName("John Cena");
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
