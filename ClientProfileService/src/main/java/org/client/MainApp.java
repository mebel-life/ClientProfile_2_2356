package org.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * ClientProfileService!
 *
 */
@SpringBootApplication
@EnableWebMvc
public class MainApp
{
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
