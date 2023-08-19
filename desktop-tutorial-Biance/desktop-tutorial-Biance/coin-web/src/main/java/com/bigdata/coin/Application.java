package com.bigdata.coin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    /**
     * 执行入口.
     *
     * @param args 入参
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("coin-web is started!");
    }
}