package com.topic1;

/**
 * @author: luwei
 * @Date: 2022/2/16
 * @description:
 */
public class Driver1 {
    private Car car;
    public Driver1(Car car) {
        this.car = car;
    }

    private void drive() {
        car.run();
    }

    public void setCar(Car car) {
        this.car = car;
    }
}

