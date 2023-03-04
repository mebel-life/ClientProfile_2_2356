package org.client;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 *
 *
 */
@SpringBootApplication
@EnableJms
public class AppAvatar
{
    public static void main( String[] args )
    {

        SpringApplication.run(AppAvatar.class, args);
    }
}
