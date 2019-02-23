package com.home_projects.imarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class ImarketApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImarketApplication.class, args);
    }

   /* @Bean
    @Autowired
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);
    }*/
}
