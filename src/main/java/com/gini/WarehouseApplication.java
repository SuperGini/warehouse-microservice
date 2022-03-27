package com.gini;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

@SpringBootApplication
public class WarehouseApplication{

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);

    }
}
