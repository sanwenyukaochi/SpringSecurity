package org.secure.security.authentication.handler.login.github.controller;

import org.secure.security.common.web.model.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public/login/github")
public class GithubLoginConfigController {

    @Value("${spring.security.oauth2.client.registration.github.client-id:}")
    private String clientId;

    @GetMapping("/config")
    public Result<Map<String, String>> config() {
        // 使用当前应用地址生成回调页面路径，复用已存在的 github-callback.html
        String redirectUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/github-callback.html")
                .build()
                .toUriString();

        Map<String, String> data = new HashMap<>();
        data.put("clientId", clientId);
        data.put("redirectUri", redirectUri);
        return Result.success("SUCCESS", data);
    }
}