package com.springframework.security.config;

import com.springframework.security.handler.AppLogoutSuccessHandler;
import com.springframework.security.handler.MyAuthenticationFailureHandler;
import com.springframework.security.handler.MyAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    private final MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    private final AppLogoutSuccessHandler appLogoutSuccessHandler;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        //跨域配置
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("*")); //允许任何来源，http://localhost:10492/
        corsConfiguration.setAllowedMethods(List.of("*")); //允许任何请求方法，post、get、put、delete
        corsConfiguration.setAllowedHeaders(List.of("*")); //允许任何的请求头 (jwt)
        //注册跨域配置
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration); //  /api/user,  /api/user/12082
        return urlBasedCorsConfigurationSource;
    }


    @Bean //安全过滤器链Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, CorsConfigurationSource corsConfigurationSource) throws Exception {
        //在spring security框架开发时，创建SecurityFilterChain这个Bean，不是直接new DefaultSecurityFilterChain
        //return new DefaultSecurityFilterChain();
        return httpSecurity
                //配置我们自己的登录页
                .formLogin(formLogin -> {
                    // 框架默认接收登录提交请求的地址是 /login，但是我们把它给弄丢了，需要捡回来
                    formLogin.loginProcessingUrl("/user/login") //登录的账号密码往哪个地址提交
                            .successHandler(myAuthenticationSuccessHandler) //登录成功后执行该handler
                            .failureHandler(myAuthenticationFailureHandler);// 登录失败后执行该handler

                })
                .logout(logout -> {
                    logout.logoutUrl("/user/logout") //退出请求提交到哪个地址
                            .logoutSuccessHandler(appLogoutSuccessHandler);
                })
                //把所有接口都会进行登录状态检查的默认行为，再加回来
                .authorizeHttpRequests((authorizeHttpRequests) -> {
                    authorizeHttpRequests
                            .anyRequest().authenticated(); //除了上面的特殊情况外，其他任何对后端接口的请求，都需要认证（登录）后才能访问
                })
                .csrf(AbstractHttpConfigurer::disable)
                .cors((corsConfiguration) -> corsConfiguration.configurationSource(corsConfigurationSource))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    //前端：前后端不分离  +   前后端分离
    //前后端不分离: JSP（几乎完全过时了）、模版技术Thymeleaf、FreeMarker、Velocity (这几个也几乎过时了)
    //前后端分离：比较流行的，代表技术：Vue.js、React.js

}
