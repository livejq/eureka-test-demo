package com.livejq.consumer;

import com.livejq.consumer.loadbalance.SayHelloConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mayn
 * @date 2019/6/19
 **/
@RestController
public class UserApplication {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @Value("${server.port}")
    private String port;

    private AtomicInteger index = new AtomicInteger(0);

    @RequestMapping("/hi")
    public String hi(@RequestParam(value="name", defaultValue="livejq") String name) {
        String greeting = this.restTemplate.getForObject("http://producer/greeting", String.class);

        return String.format("%s, %s!", greeting, name);
    }

//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private LoadBalancerClient loadBalancer;
//
//    private AtomicInteger index = new AtomicInteger(0);
//    @RequestMapping(value = "/compute", method = RequestMethod.GET)
//    public void test(@RequestParam Integer a, @RequestParam Integer b) {// 将原来的ip:port的形式，改成注册到Eureka Server上的应用名即可
//        System.out.println("====================================================");
//        ServiceInstance instance = loadBalancer.choose("compute-service");
//        URI uri = URI.create(String.format("http://%s:%s/add?a=%s&b=%s", instance.getHost(), instance.getPort(), a, b));
//
//        String result = restTemplate.getForObject(uri, String.class);
//        System.out.println("返回结果:" + result);
//    }

// --------------------------------------------------

//    /**
//     * Rest服务端使用RestTemplate发起http请求,然后得到数据返回给前端
//     * @param  name
//     * @return java.lang.String
//     **/
//    @GetMapping("/name")
//    @ResponseBody
//    public String getMessage01(@RequestParam String name){
//
//        // 在注册中心注册了服务后，可直接以服务名访问所需的服务
//        String data = restTemplate.getForObject("http://producer/hi?name=" + name, String.class);
//        return data;
//    }
//
//    /**
//     *
//     * @param  applicationName
//     * @return java.lang.String
//     **/
//    @GetMapping("/applicationName")
//    @ResponseBody
//    public String getMessage02(@RequestParam String applicationName){
//
//        String data = restTemplate.getForObject("http://producer/service-instances/" + applicationName, String.class);
//        return data;
//    }
}
