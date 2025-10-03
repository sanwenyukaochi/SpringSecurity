package com.springframework.security.handler;

import cn.hutool.json.JSONUtil;
import com.springframework.security.constant.Constant;
import com.springframework.security.model.Result;
import com.springframework.security.service.impl.UserDetailsImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AppLogoutSuccessHandler implements LogoutSuccessHandler {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        //退出成功，需要把redis中登录的token删除一下
        redisTemplate.opsForHash().delete(Constant.REDIS_TOKEN_KEY, String.valueOf(userDetails.getId()));

        //采用构建器模式，链式编程创建一个R对象
        Result<Object> result = Result.builder().code(200).msg("退出成功").data(authentication).build();

        //hutool工具包，把R对象转成json字符串
        String json = JSONUtil.toJsonStr(result);

        //把json写出去，写出到浏览器客户端
        response.getWriter().write(json);
    }
}
