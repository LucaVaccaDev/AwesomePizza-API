package com.portal.awesome_pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:application.properties")
@SpringBootApplication(scanBasePackages = "com.portal.awesome_pizza")
public class AwesomePizzaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwesomePizzaApplication.class, args);
    }


}
