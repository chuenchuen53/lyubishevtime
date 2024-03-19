package com.example.lyubishevtime;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.lyubishevtime.mapper")
public class LyubishevtimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyubishevtimeApplication.class, args);
    }
}
