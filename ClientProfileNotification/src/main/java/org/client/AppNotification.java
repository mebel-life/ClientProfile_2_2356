package org.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableRedisRepositories
public class AppNotification
{
    public static void main( String[] args )
    {
        SpringApplication.run(AppNotification.class,args);
    }
}