package org.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class MSUpdate
{
    public static void main( String[] args )
    {
        SpringApplication.run(MSUpdate.class, args);
    }

}
