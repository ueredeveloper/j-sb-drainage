package com.api.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cdimascio.dotenv.Dotenv;

@RestController
@SpringBootApplication
public class Main {

	
	public static void main(String... args) {
		// Captura variáveis de ambiente no arquivo .env
		Dotenv dotenv = Dotenv.configure()
                .directory(System.getProperty("user.dir"))
                .load();
				// Make environment variables available
				dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
				);
				
		SpringApplication.run(Main.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "JAVA - SPRINGBOOT - DRAINAGE!";
	}

}
