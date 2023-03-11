package com.isikodon.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@EnableEurekaClient
@EnableFeignClients(basePackages = "com.isikodon.clients")
@SpringBootApplication(scanBasePackages = {
        "com.isikodon.customer"
//        "com.isikodon.amqp"
})
@PropertySources(
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
)
public class CustomerMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerMSApplication.class, args);
    }
}
