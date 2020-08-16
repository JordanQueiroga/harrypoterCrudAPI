package com.harrypoter.crudAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ApiHarryPotterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiHarryPotterApplication.class, args);
    }

}
