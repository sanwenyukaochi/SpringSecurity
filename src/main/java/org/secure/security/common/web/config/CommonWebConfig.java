package org.secure.security.common.web.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommonWebConfig {

    @Bean
    public Snowflake snowflake() {
        return IdUtil.getSnowflake(1, 1);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS.mappedFeature(), true);
        return objectMapper;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
