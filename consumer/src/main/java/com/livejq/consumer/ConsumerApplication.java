package com.livejq.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class ConsumerApplication {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    /**
     * 实例化RestTemplate
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate rest() {
        return new RestTemplate();
    }

    /**
     * Rest服务端使用RestTemplate发起http请求,然后得到数据返回给前端
     * @param name
     * @return
     */
    @GetMapping(value = "/getName")
    @ResponseBody
    public String getMessage01(@RequestParam String name){

        // 在注册中心注册了服务后，可直接以服务名访问所需的服务
        String data = restTemplate.getForObject("http://producer01/hi?name=" + name, String.class);
        return data;
    }

    /**
     * @param applicationName
     * @return
     */
    @GetMapping(value = "/getList")
    @ResponseBody
    public String getMessage02(@RequestParam String applicationName){

        String data = restTemplate.getForObject("http://producer01/service-instances/" + applicationName, String.class);
        return data;
    }
}
