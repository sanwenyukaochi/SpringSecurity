package com.spring.security.common.web.service;

import com.spring.security.authentication.handler.auth.UserLoginInfo;
import com.spring.security.common.web.constant.ResponseCodeConstants;
import com.spring.security.common.web.exception.BaseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import com.spring.security.domain.model.entity.User;
import com.spring.security.domain.repository.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    @Override
    @NonNull
    public UserLoginInfo loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return objectMapper.convertValue(getUserByUsername(username), UserLoginInfo.class);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new BaseException(ResponseCodeConstants.USER_NOT_FOUND, "用户不存在", HttpStatus.NOT_FOUND));
    }

    public User getUserByPhone(String phoneNumber) {
        return userRepository.findByPhone(phoneNumber).orElseThrow(() -> new BaseException(ResponseCodeConstants.USER_PHONE_NOT_FOUND, "手机号不存在", HttpStatus.NOT_FOUND));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new BaseException(ResponseCodeConstants.USER_EMAIL_NOT_FOUND, "邮箱不存在", HttpStatus.NOT_FOUND));
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new BaseException(ResponseCodeConstants.USER_NOT_FOUND, "用户不存在", HttpStatus.NOT_FOUND));
    }
}
