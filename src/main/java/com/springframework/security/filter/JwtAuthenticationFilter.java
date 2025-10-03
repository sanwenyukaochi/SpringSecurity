package com.springframework.security.filter;

import cn.hutool.json.JSONUtil;
import com.springframework.security.constant.Constant;
import com.springframework.security.model.Result;
import com.springframework.security.service.impl.UserDetailsImpl;
import com.springframework.security.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/api/auth/login") || requestURI.equals("/api/auth/captcha")) {
            filterChain.doFilter(request, response);
        } else {
            try {
                String jwt = parseJwt(request);
                if (!StringUtils.hasText(jwt)) {
                    Result<Object> result = Result.builder().code(901).msg("请求Token为空").build();
                    response.getWriter().write(JSONUtil.toJsonStr(result));
                }
                if (jwtUtil.validateJwtToken(jwt)) {
                    String username = jwtUtil.getUserNameFromJwtToken(jwt);
                    String userDetailsJsonString = stringRedisTemplate.opsForValue().get(Constant.REDIS_TOKEN_KEY + username);
                    UserDetailsImpl userDetails = JSONUtil.toBean(userDetailsJsonString, UserDetailsImpl.class);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    stringRedisTemplate.expire(Constant.REDIS_TOKEN_KEY + username, Constant.CACHE_EXPIRE_TIME, TimeUnit.MINUTES);
                }
            } catch (Exception e) {
                log.error("无法设置用户身份验证：{}", e.getMessage());
            }
            filterChain.doFilter(request, response);
        }
    }

    private String parseJwt(HttpServletRequest request) {
        return jwtUtil.getJwtFromHeader(request);
    }
}
