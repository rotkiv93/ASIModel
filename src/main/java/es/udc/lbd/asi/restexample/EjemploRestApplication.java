package es.udc.lbd.asi.restexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
public class EjemploRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EjemploRestApplication.class, args);
    }
}
