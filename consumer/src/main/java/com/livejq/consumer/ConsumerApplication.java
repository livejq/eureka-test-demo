package com.livejq.consumer;

import com.livejq.consumer.loadbalance.SayHelloConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@EnableEurekaClient
@SpringBootApplication
@RibbonClient(name = "consumer", configuration = SayHelloConfiguration.class)
public class ConsumerApplication {

    @Bean
    @LoadBalanced   // 添加负载均衡
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
