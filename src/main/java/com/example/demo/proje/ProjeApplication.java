package com.example.demo.proje; // Senin ana paket ismin neyse o kalacak

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling; // Bunu ekliyoruz

@SpringBootApplication
@EnableScheduling // Fantezi lig zamanlamaları (cron jobs) için bu şart!
public class ProjeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjeApplication.class, args);
    }
}
