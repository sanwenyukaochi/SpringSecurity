package com.springframework.security.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.crypto.asymmetric.RSA;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;

@Component
public class RSAUtil {
    @Value("${rsa.private-key-path}")
    private Resource privateKeyRes;

    @Value("${rsa.public-key-path}")
    private Resource publicKeyRes;

    @Getter
    private RSA rsa;

    @PostConstruct
    public void init() throws IOException {
        String privateKeyStr = IoUtil.read(privateKeyRes.getInputStream(), StandardCharsets.UTF_8);
        String publicKeyStr = IoUtil.read(publicKeyRes.getInputStream(), StandardCharsets.UTF_8);
        this.rsa = new RSA(privateKeyStr, publicKeyStr);
    }

    public PrivateKey getPrivateKey() {
        return rsa.getPrivateKey();
    }

    public PublicKey getPublicKey() {
        return rsa.getPublicKey();
    }

}
