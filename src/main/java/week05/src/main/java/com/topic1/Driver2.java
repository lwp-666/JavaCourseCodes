package com.topic1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: luwei
 * @Date: 2022/2/16
 * @description:
 */
@Component
public class Driver2 {
    //2.自动装配
@Autowired
    private Car car;

private void drive() {
    car.run();
}

}
