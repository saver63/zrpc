package com.zlz.examplespringbootprovider;

import com.zlz.zlzrpc.springboot.starter.annotation.EnableRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRpc
public class ExampleSpringbootProviderApplication {

    public static void main(String[] args) {

        SpringApplication.run(ExampleSpringbootProviderApplication.class, args);
    }

}
