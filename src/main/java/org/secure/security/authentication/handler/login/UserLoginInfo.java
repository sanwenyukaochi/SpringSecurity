package org.secure.security.authentication.handler.login;

import lombok.*;

/**
 * 用户信息登陆后的信息，会序列化到Jwt的payload
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginInfo {

    private String sessionId; // 会话id，全局唯一
    private Long userId;
    private String username;
    private String password;
    private String phone;
    private String nickname; // 昵称
    private String roleId;

    private Long expiredTime; // 过期时间
}
