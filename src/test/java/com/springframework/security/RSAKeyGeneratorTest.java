package com.springframework.security;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.springframework.security.util.RSAUtil;
import jakarta.annotation.Resource;
import jakarta.annotation.Resources;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

//@SpringBootTest
public class RSAKeyGeneratorTest {

    private static final String KEY_DIR = "src/main/resources/static/keys/";
    private static final String PRIVATE_KEY_FILE = "rsa_private.key";
    private static final String PUBLIC_KEY_FILE = "rsa_public.key";

    @Test
    public void generateRSAKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();


        RSA rsa = new RSA(keyPair.getPrivate(), keyPair.getPublic());
        // 确保目录存在
        FileUtil.mkdir(KEY_DIR);

        // 写入私钥和公钥文件
        FileUtil.writeUtf8String(rsa.getPrivateKeyBase64(), new File(KEY_DIR + PRIVATE_KEY_FILE));
        FileUtil.writeUtf8String(rsa.getPublicKeyBase64(), new File(KEY_DIR + PUBLIC_KEY_FILE));

        System.out.println("✅ RSA 密钥对已生成并保存到：" + KEY_DIR);
        System.out.println("私钥文件：" + PRIVATE_KEY_FILE);
        System.out.println("公钥文件：" + PUBLIC_KEY_FILE);
    }


}
