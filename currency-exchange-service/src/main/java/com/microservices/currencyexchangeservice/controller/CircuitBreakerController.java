package com.microservices.currencyexchangeservice.controller;


import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    @RateLimiter(name= "default")
    @GetMapping("/sample-api")
    public String sampleApi(){
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/sample-api", String.class);


        return forEntity.getBody();
    }
    public String hardcodedResponse(Exception e) {
        return "fallback-response";
    }
}
