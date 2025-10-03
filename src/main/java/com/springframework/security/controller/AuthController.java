package com.springframework.security.controller;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.json.JSONUtil;
import com.springframework.security.constant.Constant;
import com.springframework.security.dto.TokenPair;
import com.springframework.security.model.Result;
import com.springframework.security.model.ResultCode;
import com.springframework.security.request.LoginRequest;
import com.springframework.security.service.impl.UserDetailsImpl;

import com.springframework.security.util.JwtUtil;
import com.springframework.security.util.RSAUtil;
import com.springframework.security.util.SecurityUtils;
import com.wf.captcha.SpecCaptcha;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RSAUtil rsaUtil;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public Result<TokenPair> login(@RequestBody LoginRequest loginRequest) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.username(), rsaUtil.getRsa().decryptStr(loginRequest.password(), KeyType.PrivateKey));
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            stringRedisTemplate.opsForValue().set(Constant.REDIS_TOKEN_KEY + userDetails.getUsername(), JSONUtil.toJsonStr(userDetails), Constant.CACHE_EXPIRE_TIME, TimeUnit.MINUTES);
            TokenPair tokenPair = jwtUtil.generateTokenPairFromUsername(userDetails.getUsername());
            return Result.success(ResultCode.SUCCESS,tokenPair);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("用户不存在");
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("密码错误");
        } catch (InternalAuthenticationServiceException e) {
            throw new InternalAuthenticationServiceException("用户认证服务异常，请稍后再试");
        } catch (AuthenticationServiceException e) {
            throw new AuthenticationServiceException("认证失败");
        } catch (Exception e) {
            log.error("系统错误，请联系管理员,{}", e.getMessage());
            throw new RuntimeException();
        }
    }

    @GetMapping("/rsaPublicKey")
    public Result<?> getRsaPublicKey() {
        return Result.success(ResultCode.SUCCESS, rsaUtil.getPublicKey());
    }

    @PostMapping("/logout")
    public Result<Object> logout() {
        UserDetailsImpl userDetails = SecurityUtils.getCurrentLoginUser();
        stringRedisTemplate.delete(Constant.REDIS_TOKEN_KEY + userDetails.getUsername());
        return Result.success(ResultCode.SUCCESS);
    }

    @GetMapping("/captcha")
    public Result<?> generateCaptcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        // 存入redis并设置过期时间为30分钟
        redisTemplate.opsForValue().set("captcha:" + key, verCode, 30, TimeUnit.MINUTES);
        record CaptchaResponse(String key, String image) { }
        return Result.success(ResultCode.SUCCESS, new CaptchaResponse(key,specCaptcha.toBase64()));
    }
}
