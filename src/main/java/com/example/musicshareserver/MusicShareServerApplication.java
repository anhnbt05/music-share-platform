package com.example.musicshareserver;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MusicShareServerApplication {
	public static void main(String[] args) {
        SpringApplication.run(MusicShareServerApplication.class, args);
	}
}
