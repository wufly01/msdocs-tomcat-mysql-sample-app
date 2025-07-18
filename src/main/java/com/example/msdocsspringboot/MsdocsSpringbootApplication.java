package com.example.msdocsspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.msdocsspringboot.mapper")
public class MsdocsSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsdocsSpringbootApplication.class, args);
    }

}
