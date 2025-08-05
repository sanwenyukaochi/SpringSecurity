package com.sanwenyukaochi.security;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import java.util.*;

public class DashScopeWebClient {

    private static final String API_URL = "https://dashscope.aliyuncs.com/compatible-mode/v1/embeddings";
    private static final String API_KEY = "sk-a8358f9902d64f29a6890fb0bb29c6e9";

    public static void main(String[] args) {
        WebClient webClient = WebClient.builder()
                .baseUrl(API_URL)
                .defaultHeader("Authorization", "Bearer " + API_KEY)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();

        Map<String, Object> body = new HashMap<>();
        body.put("model", "text-embedding-v4");
        body.put("input", Arrays.asList(
                "风急天高猿啸哀",
                "渚清沙白鸟飞回",
                "无边落木萧萧下",
                "不尽长江滚滚来"
        ));
        body.put("encoding_format", "float");

        Mono<String> responseMono = webClient.post()
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);

        String response = responseMono.block();
        System.out.println(response);
    }
}
