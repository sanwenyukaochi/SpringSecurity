package com.secure.security.authentication.handler.auth.github.controller;

import com.secure.security.authentication.handler.auth.github.dto.GitHubOAuthConfigResponse;
import com.secure.security.domain.model.dto.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/public/login/github")
public class GitHubLoginConfigController {

    @Value("${spring.security.oauth2.client.registration.github.client-id:}")
    private String clientId;

    @GetMapping("/config")
    public Result<GitHubOAuthConfigResponse> config() {
        // 使用当前应用地址生成回调页面路径，复用已存在的 github-callback.html
        String redirectUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/github-callback.html")
                .build()
                .toUriString();

        GitHubOAuthConfigResponse data = new GitHubOAuthConfigResponse(clientId, redirectUri);
        return Result.success(Result.SUCCESS_CODE, data);
    }
}