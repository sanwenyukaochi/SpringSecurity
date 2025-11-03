package com.secure.security.common.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "SignupRequest", description = "用户注册请求对象")
public record SignupRequest(

        @Schema(title = "用户名", example = "admin_user", description = "用户名必须唯一")
        @NotBlank(message = "用户名不能为空")
        @Size(min = 3, max = 30, message = "用户名长度需在3-30字符之间")
        String username,

        @Schema(title = "密码", example = "123456", description = "密码长度需在6-50字符之间")
        @NotBlank(message = "密码不能为空")
        @Size(min = 6, max = 50, message = "密码长度需在6-50字符之间")
        String password,

        @Schema(title = "邮箱", example = "admin@example.com", description = "邮箱必须填写")
        @Email(message = "邮箱格式不正确")
        String email,

        @Schema(title = "验证码", example = "839201", description = "邮箱验证码，用于验证注册合法性")
        @NotBlank(message = "验证码不能为空")
        String code
) {
}
