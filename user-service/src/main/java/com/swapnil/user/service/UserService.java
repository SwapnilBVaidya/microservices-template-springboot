package com.swapnil.user.service;

import com.swapnil.user.UserDto;
import com.swapnil.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired private UserDao userDao;

    public UserDto findById(Long id){
        return userDao.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }

    public UserDto createUser(UserDto userDto){
        return userDao.createUser(userDto);
    }
}
