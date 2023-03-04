package org.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.kafka.annotation.EnableKafka;

@EnableJms
@EnableKafka
@SpringBootApplication
public class AppLoader {

    public static void main(String[] args) {
        SpringApplication.run(AppLoader.class, args);
    }
}
