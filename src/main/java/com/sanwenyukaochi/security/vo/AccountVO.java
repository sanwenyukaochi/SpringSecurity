package com.sanwenyukaochi.security.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String userName;
    private String nickName;
    private String phone;
    private String avatar;
    // TODO 缺租组织(角色)
    // TODO 缺套餐
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updatedAt;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdAt;
}
