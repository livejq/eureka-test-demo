package com.livejq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述: 处理相关的请求
 * Created by livejq
 * 2019-06-17 20:23
 **/
@RestController
public class ServiceInstanceRestController {

    // 注入依赖
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;

    @GetMapping("/hi")
    public String home(@RequestParam String name) {
        return "欢迎，" + name + ",很高兴为您服务，端口：" + port;
    }

    /**
     * 通过 discoveryClient 服务发现接口，通过传入服务ID，获取任意已注册的client实例的相关信息
     * @param applicationName
     * @return
     */
    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
}
