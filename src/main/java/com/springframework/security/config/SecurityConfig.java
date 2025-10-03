package com.springframework.security.config;

import com.springframework.security.filter.CaptchaFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CaptchaFilter captchaFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean //安全过滤器链Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //在spring security框架开发时，创建SecurityFilterChain这个Bean，不是直接new DefaultSecurityFilterChain
        //return new DefaultSecurityFilterChain();
        return httpSecurity
                //配置我们自己的登录页
                .formLogin(new Customizer<FormLoginConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(FormLoginConfigurer<HttpSecurity> formLogin) {
                        // 框架默认接收登录提交请求的地址是 /login，但是我们把它给弄丢了，需要捡回来
                        formLogin.loginProcessingUrl("/user/login") //登录的账号密码往哪个地址提交
                                .loginPage("/toLogin") //定制登录页（Thymeleaf页面）
                                .successForwardUrl("/welcome");
                    }
                })
                //把所有接口都会进行登录状态检查的默认行为，再加回来
                .authorizeHttpRequests( (authorizeHttpRequests) -> {
                    authorizeHttpRequests
                            .requestMatchers("/toLogin", "/common/captcha").permitAll() //特殊情况的设置，permitAll允许不登录就可以访问
                            .anyRequest().authenticated(); //除了上面的特殊情况外，其他任何对后端接口的请求，都需要认证（登录）后才能访问
                })
                //验证码filter加在接收登录账号密码的filter之前
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    //前端：前后端不分离  +   前后端分离
    //前后端不分离: JSP（几乎完全过时了）、模版技术Thymeleaf、FreeMarker、Velocity (这几个也几乎过时了)
    //前后端分离：比较流行的，代表技术：Vue.js、React.js

}
