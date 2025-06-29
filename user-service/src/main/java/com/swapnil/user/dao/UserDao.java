package com.swapnil.user.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapnil.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDao {
    @Autowired private UserRepository userRepository;
    @Autowired private ObjectMapper objectMapper;

    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(userEntity -> objectMapper.convertValue(userEntity, UserDto.class));
    }

    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = userRepository.save(objectMapper.convertValue(userDto, UserEntity.class));
        return objectMapper.convertValue(userEntity, UserDto.class);
    }
}
