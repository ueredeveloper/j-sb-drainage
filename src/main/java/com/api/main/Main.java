package com.api.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import com.api.utils.EnvLoader;

@SpringBootApplication
public class Main {
	public static void main(String... args) {

		//EnvLoader.loadEnvFile(".env");
		
		System.out.println("remove application.properties do .gitignore! ");

		SpringApplication.run(Main.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "Hello World!";
	}

}
