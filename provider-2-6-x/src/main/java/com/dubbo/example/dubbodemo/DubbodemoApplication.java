package com.dubbo.example.dubbodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"application-bean.xml"})
public class DubbodemoApplication {

    public static void main(String[] args) throws Exception{

        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.err.println(String.format("UncaughtException in Thread(%s): %s", t.getName(), e.getMessage()));
            e.printStackTrace();
        });

        SpringApplication.run(DubbodemoApplication.class, args);

        System.in.read();
    }

}
