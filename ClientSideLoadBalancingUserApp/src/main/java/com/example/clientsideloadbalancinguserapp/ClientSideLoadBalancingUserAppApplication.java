package com.example.clientsideloadbalancinguserapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@RestController
public class ClientSideLoadBalancingUserAppApplication {

    @Bean
    @LoadBalanced
    public RestTemplate template(){
        return new RestTemplate();
    }

    @Autowired
    @Lazy
    private RestTemplate template;

    @GetMapping("/invoke")
    public String invokeChatbook(){
        String url = "http://chatbook/chatbook-application/chat";
        return template.getForObject(url, String.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientSideLoadBalancingUserAppApplication.class, args);
    }

}
