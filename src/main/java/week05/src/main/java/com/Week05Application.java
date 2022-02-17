package com;

import com.demo.config.KlassConfig;
import com.demo.properties.KlassProperties;
import com.demo.properties.SchoolProperties;
import com.demo.properties.StudentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week05Application implements CommandLineRunner {

    @Autowired
    private StudentProperties studentProperties;
    @Autowired
    private KlassProperties klassProperties;
    @Autowired
    private SchoolProperties schoolProperties;

    public static void main(String[] args) {
        SpringApplication.run(Week05Application.class);
    }

    @Override
    public void run(String... args) {
        System.out.println(studentProperties);
        klassProperties.dong();
        schoolProperties.ding();
    }

}
