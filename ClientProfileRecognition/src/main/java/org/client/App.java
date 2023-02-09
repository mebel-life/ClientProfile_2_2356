package org.client;

import net.sourceforge.tess4j.TesseractException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class App {
    public static void main(String[] args) throws TesseractException {
        SpringApplication.run(App.class, args);
    }
}
