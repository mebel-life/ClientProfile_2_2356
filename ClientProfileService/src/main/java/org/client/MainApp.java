package org.client;

import org.client.controller.AddressController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
