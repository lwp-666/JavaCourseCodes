package com.topic1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: luwei
 * @Date: 2022/2/16
 * @description:
 */
@Configuration
public class BeanConfig {

    @Bean(name = "aCar")
    public Car car() {
        return new Car();
    }

    @Bean
    public Driver1 driver() {
        //1.在Java中进行bean配置
        return new Driver1(car());
    }
}
