package com.sanwenyukaochi.security.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String userName;
    private String nickName;
    private String phone;
    private String avatar;
    // TODO 缺租组织(角色)
    // TODO 缺套餐
    private TenantVO  tenant;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updatedAt;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdAt;

    @Getter
    @AllArgsConstructor
    public static class TenantVO {
        @JsonSerialize(using = ToStringSerializer.class)
        private Long tenantId;
        private String tenantName;
    }
}
