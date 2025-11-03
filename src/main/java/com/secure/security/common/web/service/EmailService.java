package com.secure.security.common.web.service;

import com.secure.security.common.web.constant.ResponseCodeConstants;
import com.secure.security.common.web.dto.request.SendEmailRequest;
import com.secure.security.common.web.exception.BaseException;
import com.secure.security.domain.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final UserRepository userRepository;

    private final JavaMailSender mailSender;

    private final MailProperties mailProperties;

    public void sendEmail(@Valid SendEmailRequest sendEmailRequest) {

        // 用户名重复检测
        Optional.of(sendEmailRequest.username())
                .filter(username -> !userRepository.existsByUsername(username))
                .orElseThrow(() -> new BaseException(ResponseCodeConstants.USER_ALREADY_EXISTS, "用户名已存在", HttpStatus.CONFLICT));
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailProperties.getUsername());
        message.setTo(sendEmailRequest.email());
        message.setSubject("邮箱注册验证码");
        message.setText("000000");
        mailSender.send(message);
    }

}
