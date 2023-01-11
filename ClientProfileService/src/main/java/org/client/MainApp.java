package org.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ClientProfileService!
 *
 */
@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class MainApp
{
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
