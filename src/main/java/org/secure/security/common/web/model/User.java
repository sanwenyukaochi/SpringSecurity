package org.secure.security.common.web.model;

import lombok.Getter;
import lombok.Setter;

// 详细的用户信息数据，一般会有十几个字段。这里简单写几个
@Setter
@Getter
public class User {

    private Long userId;
    private String roleId;
    private String username;
    private String password;
    private String phone;

}
