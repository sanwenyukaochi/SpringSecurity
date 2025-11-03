package com.secure.security.common.web.controller;

import com.secure.security.common.web.dto.request.SendEmailRequest;
import com.secure.security.common.web.dto.request.SignupRequest;
import com.secure.security.common.web.service.EmailService;
import com.secure.security.common.web.service.LoginService;
import com.secure.security.domain.model.dto.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class LoginController {
    
    private final LoginService loginService;
    
    private final EmailService emailService;
    
    @PostMapping("/signup/application/email")
    public Result<String> signupForEmail(@RequestBody @Valid SignupRequest signupRequest) {
        loginService.signupForEmail(signupRequest);
        return Result.success("注册成功");
    }

    @PostMapping("/signup/application/sendEmail")
    public Result<String> sendEmail(@RequestBody @Valid SendEmailRequest sendEmailRequest) {
        emailService.sendEmail(sendEmailRequest);
        return Result.success("验证码发送成功");
    }
}
