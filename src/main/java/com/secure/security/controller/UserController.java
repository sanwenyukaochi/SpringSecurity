package com.secure.security.controller;

import com.secure.security.model.dto.Result;
import com.secure.security.security.service.UserPrincipal;
import com.secure.security.service.UserService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public Result<?> getUserInfo(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Result.success(Map.of("userInfo", userPrincipal));
    }
}
