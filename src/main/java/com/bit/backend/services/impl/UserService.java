package com.bit.backend.services.impl;

import com.bit.backend.dtos.CredentialsDto;
import com.bit.backend.dtos.SignUpDto;
import com.bit.backend.dtos.UserDto;
import com.bit.backend.entities.UserEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.UserMapper;
import com.bit.backend.repositories.UserRepository;
import com.bit.backend.services.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceI {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto login(CredentialsDto credentialsDto) {
        logger.debug("Entering login...");
        UserEntity userEntity = userRepository.findByLogin(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknown UserEntity", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), userEntity.getPassword())) {
            return userMapper.toUserDto(userEntity);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserDto register(SignUpDto signUpDto) {
        Optional<UserEntity> existing = userRepository.findByLogin(signUpDto.login());
        if (existing.isPresent()) {
            throw new AppException("UserEntity Already Exists", HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = userMapper.signUpToUser(signUpDto);
        userEntity.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
        UserEntity savedUserEntity = userRepository.save(userEntity);
        if (savedUserEntity.getCreatedBy() == null) {
            savedUserEntity.setCreatedBy(savedUserEntity.getId());
            savedUserEntity = userRepository.save(savedUserEntity);
        }
        return userMapper.toUserDto(savedUserEntity);
    }

    @Override
    public List<Integer> getAuthIds(long userId) {
        List<Integer> authIds = userRepository.findAuthIdsByUserId(userId);
        return authIds != null ? authIds : Collections.emptyList();
    }
}
