package com.spring.security.common.web.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
@EnableConfigurationProperties(CommonWebConfig.ProxyProperties.class)
public class CommonWebConfig {

    @Bean
    public Snowflake snowflake() {
        return IdUtil.getSnowflake(1, 1);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
                // 反序列化时，JSON中有Java对象没有的字段不报错
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                // 序列化时，空对象（没有getter的类）不报错，序列化为{}
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                // 全局配置：值为null的字段不序列化，但集合/Map里的null值保留
                .defaultPropertyInclusion(
                        JsonInclude.Value.construct(
                                JsonInclude.Include.NON_NULL,  // 普通字段为null时不序列化
                                JsonInclude.Include.ALWAYS     // 集合/Map里的null值要序列化
                        )
                )
                // JSON美化格式化（有缩进换行，开发环境用，生产建议关闭）
                .enable(SerializationFeature.INDENT_OUTPUT)
                // 枚举序列化/反序列化使用toString()
                .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
                // 自动发现并注册Jackson模块（如JavaTimeModule处理LocalDateTime）
                .findAndAddModules()
                .build();
    }

    @Bean
    public RestTemplate restTemplate(ProxyProperties proxyProperties) {

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(8000);

        Optional.of(proxyProperties)
                .filter(ProxyProperties::enabled)
                .ifPresent(p -> factory.setProxy(
                        new Proxy(Proxy.Type.HTTP, new InetSocketAddress(p.host(), p.port()))
                ));

        return new RestTemplate(factory);
    }

    @ConfigurationProperties(prefix = "spring.proxy")
    public record ProxyProperties(boolean enabled, String host, int port) {
    }

}
