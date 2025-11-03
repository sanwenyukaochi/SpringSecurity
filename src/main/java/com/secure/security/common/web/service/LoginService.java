package com.secure.security.common.web.service;

import cn.hutool.core.lang.Snowflake;
import com.secure.security.common.web.constant.ResponseCodeConstants;
import com.secure.security.common.web.dto.request.SignupRequest;
import com.secure.security.common.web.exception.BaseException;
import com.secure.security.domain.model.entity.User;
import com.secure.security.domain.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final Snowflake snowflake;

    public void signupForEmail(@Valid SignupRequest signupRequest) {

        // 用户名重复检测
        Optional.of(signupRequest.username())
                .filter(username -> !userRepository.existsByUsername(username))
                .orElseThrow(() -> new BaseException(ResponseCodeConstants.USER_ALREADY_EXISTS, "用户名已存在", HttpStatus.CONFLICT));

        // 邮箱重复检测
        Optional.ofNullable(signupRequest.email())
                .filter(email -> !userRepository.existsByEmail(email))
                .orElseThrow(() -> new BaseException(ResponseCodeConstants.EMAIL_ALREADY_EXISTS, "邮箱已被注册", HttpStatus.CONFLICT));

        if (signupRequest.code() == null || !signupRequest.code().equals("000000")) {
            throw new BaseException(ResponseCodeConstants.SMS_CODE_ERROR, "无效的验证码", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setId(snowflake.nextId());
        user.setUsername(signupRequest.username());
        user.setPassword(passwordEncoder.encode(signupRequest.password()));
        user.setEmail(signupRequest.email());
        user.setPhone("");
        user.setTwoFactorSecret(null);
        userRepository.save(user);
    }


}
