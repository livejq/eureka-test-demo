package com.livejq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 描述: 处理相关的请求
 * Created by livejq
 * 2019-06-17 20:23
 **/
@RestController
public class SayHelloApplication {

    private static Logger log = LoggerFactory.getLogger(SayHelloApplication.class);

    @Autowired
    private Environment environment;

    @RequestMapping(value = "/greeting")
    public String greet() {
        log.info("Access /greeting");

        String serverPort = environment.getProperty("local.server.port");

        List<String> greetings = Arrays.asList("你好!", "我好!", "大家好!");
        Random rand = new Random();

        int randomNum = rand.nextInt(greetings.size());
        return greetings.get(randomNum) + "，端口号：" + serverPort;
    }

    @RequestMapping(value = "/")
    public String home() {
        log.info("Access /");
        String serverPort = environment.getProperty("local.server.port");

        return "Hi!" + "，端口号：" + serverPort;
    }

    public static void main(String[] args) {
        SpringApplication.run(SayHelloApplication.class, args);
    }




//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @Value("${server.port}")
//    private String port;
//
//    @Value("${spring.application.name}")
//    private String service_name;
//
//    private final Logger logger = LoggerFactory.getLogger(ComputeController.class);
//
//    /**
//     *
//     * @param a
//     * @param b
//     * @return msg
//     */
//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public String add(@RequestParam Integer a, @RequestParam Integer b) {
//        AtomicInteger index = new AtomicInteger(0);
//        Integer result = a + b;
//        String msg =  "/add, " + service_name + ":" + port + "result:" + result + ", index=" + index.incrementAndGet() + ", time="+ LocalDateTime.now();
//
//        return msg;
//    }

// --------------------------------------------------------

//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @Value("${server.port}")
//    private String port;
//
//    @GetMapping("/hi")
//    public String home(@RequestParam String name) {
//        return "欢迎，" + name + ",很高兴为您服务，端口：" + port;
//    }
//
//    /**
//     * 通过 discoveryClient 服务发现接口，通过传入服务ID，获取任意已注册的client实例的相关信息
//     * @param applicationName
//     * @return
//     */
//    @RequestMapping("/service-instances/{applicationName}")
//    public List<ServiceInstance> serviceInstancesByApplicationName(
//            @PathVariable String applicationName) {
//        return this.discoveryClient.getInstances(applicationName);
//    }
}
