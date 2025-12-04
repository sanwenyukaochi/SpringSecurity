package com.java.graphql.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class GraphQLController {

    @QueryMapping
    public Mono<String> sayHello(){
        return Mono.just("Hello World");
    }

    @QueryMapping
    public Mono<String> sayHelloTo(@Argument String name){
        return Mono.fromSupplier(() -> "Hello" + name);
    }
}
