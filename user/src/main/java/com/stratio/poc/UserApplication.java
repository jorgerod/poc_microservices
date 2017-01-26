package com.stratio.poc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.stratio.poc.common.dto.User;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrix
public class UserApplication {
    
    @Value("${infoprofile}")
    private String profileName;

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    public Map<String, User> cacheUsers() {
        Map<String, User> ret = new HashMap<>();
        ret.put("1", new User("1", "Pepe", profileName));
        ret.put("2", new User("2", "Paco", profileName));
        ret.put("3", new User("3", "Antonio", profileName));

        return ret;
    }
    
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
