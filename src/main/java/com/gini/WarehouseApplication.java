package com.gini;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.io.PrintStream;

@EnableWebSecurity(debug = true)
@SpringBootApplication
public class WarehouseApplication{

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);

    }
}
