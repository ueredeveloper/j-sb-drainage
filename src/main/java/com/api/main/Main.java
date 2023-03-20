package com.api.main;

import com.api.main.models.TipoDocumentoModel;
import com.api.main.repositories.TipoDocumentoRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main {
  public static void main(String... args) {
    SpringApplication.run(Main.class, args);

  }

  @GetMapping("/")
  public String index() {
    return "Hello World!";
  }

  @Bean
  public CommandLineRunner insertTipoDocumento(TipoDocumentoRepository tdr) {

    return (args) -> {

      tdr.save(new TipoDocumentoModel("Requerimento"));
      tdr.save(new TipoDocumentoModel("Ofício"));
 
    };

    
  }
}
