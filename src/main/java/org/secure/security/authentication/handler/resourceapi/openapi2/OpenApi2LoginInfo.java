package org.secure.security.authentication.handler.resourceapi.openapi2;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OpenApi2LoginInfo {

    private String appId;
    private String appSecret;
    private String merchantName; // 下游商户名称

    @Override
    public String toString() {
        return "OpenApi2LoginInfo{" +
                "appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", merchantName='" + merchantName + '\'' +
                '}';
    }
}
