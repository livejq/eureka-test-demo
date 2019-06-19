package com.livejq.consumer.loadbalance;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration注解不能放在@SpringBootApplication所在的包下 如果放在此包下，默认全部负载均衡使用此策略
 * @author mayn
 * @date 2019/6/19
 **/
@Configuration
public class TestConfiguration {
    @Bean
    public IRule ribbonRule() {
        //return new RandomRule(); // 设置负载均衡的规则为随机
        return new RoundRobinRule(); // 默认的轮询策略
    }
}
