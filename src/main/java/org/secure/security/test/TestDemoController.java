package org.secure.security.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.secure.security.authentication.handler.resourceapi.openapi2.OpenApi2LoginInfo;
import org.secure.security.common.web.model.Result;
import org.secure.security.authentication.handler.login.UserLoginInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open-api")
@RequiredArgsConstructor
public class TestDemoController {

    private final ObjectMapper objectMapper;

    @GetMapping("/business-1")
    public Result<?> getA(Authentication authentication) throws JsonProcessingException {
        UserLoginInfo userLoginInfo = (UserLoginInfo) authentication
                .getPrincipal();
        System.out.println("自家用户登录信息：" + objectMapper.writeValueAsString(userLoginInfo));
        return Result.builder()
                .code(Result.SUCCESS_CODE)
                .data(userLoginInfo)
                .message("${test.message.a:测试国际化消息 A}")
                .build();
    }

    @GetMapping("/business-2")
    public Result<?> getB() throws JsonProcessingException {
        OpenApi2LoginInfo userLoginInfo = (OpenApi2LoginInfo) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        System.out.println("三方API登录信息：" + objectMapper.writeValueAsString(userLoginInfo));
        return Result.builder()
                .code(Result.SUCCESS_CODE)
                .data(userLoginInfo)
                .message("SUCCESS B")
                .build();
    }

    @GetMapping("/business-3")
    public Result<?> getC() throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        System.out.println("登录信息：" + objectMapper.writeValueAsString(authentication));
        return Result.builder()
                .code(Result.SUCCESS_CODE)
                .data("模拟访问成功的响应数据")
                .message("匿名接口，所有人可公开访问")
                .build();
    }

    @GetMapping("/business-4")
    public Result<?> getD() {
        return Result.builder()
                .code(Result.SUCCESS_CODE)
                .data("模拟 未知 api")
                .message("default api ...")
                .build();
    }


}
