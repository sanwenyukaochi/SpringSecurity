package org.secure.security.authentication.handler.login.github.service;

import lombok.RequiredArgsConstructor;
import org.secure.security.authentication.handler.login.github.dto.AccessTokenResponse;
import org.secure.security.authentication.handler.login.github.dto.GithubEmail;
import org.secure.security.authentication.handler.login.github.dto.GithubUserProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GithubOAuthService {

    @Value("${spring.security.oauth2.client.registration.github.client-id:}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.github.client-secret:}")
    private String clientSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    public String exchangeCodeForToken(String code) {
        String url = "https://github.com/login/oauth/access_token";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = Map.of(
                "client_id", clientId,
                "client_secret", clientSecret,
                "code", code
        );
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<AccessTokenResponse> resp = restTemplate.exchange(url, HttpMethod.POST, entity, AccessTokenResponse.class);
        if (resp.getStatusCode().is2xxSuccessful() && resp.getBody() != null && resp.getBody().getAccessToken() != null) {
            return resp.getBody().getAccessToken();
        }
        throw new IllegalStateException("GitHub access_token 获取失败");
    }

    public GithubUserProfile fetchUserProfile(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<GithubUserProfile> resp = restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, entity, GithubUserProfile.class);
        if (resp.getStatusCode().is2xxSuccessful() && resp.getBody() != null) {
            GithubUserProfile profile = resp.getBody();
            if (profile.getEmail() == null || profile.getEmail().isBlank()) {
                // 额外获取邮箱
                ResponseEntity<GithubEmail[]> emailResp = restTemplate.exchange("https://api.github.com/user/emails", HttpMethod.GET, entity, GithubEmail[].class);
                if (emailResp.getStatusCode().is2xxSuccessful() && emailResp.getBody() != null) {
                    for (GithubEmail e : emailResp.getBody()) {
                        if (Boolean.TRUE.equals(e.getPrimary())) {
                            profile.setEmail(e.getEmail());
                            break;
                        }
                    }
                }
            }
            return profile;
        }
        throw new IllegalStateException("GitHub 用户信息获取失败");
    }






}