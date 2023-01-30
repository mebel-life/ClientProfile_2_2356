package org.client;

import org.client.repositories.IndividualRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Profile Loader microservice main class
 */
@SpringBootApplication
public class App {
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
