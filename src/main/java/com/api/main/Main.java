package com.api.main;

import com.api.main.models.ProcessoModel;
import com.api.main.models.DocumentoTipoModel;
import com.api.main.repositories.ProcessoRepository;
import com.api.main.repositories.DocumentoTipoRepository;

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
  public CommandLineRunner insertTipoDocumento(DocumentoTipoRepository tdr) {

    return (args) -> {

      tdr.save(new DocumentoTipoModel("Requerimento"));
      tdr.save(new DocumentoTipoModel("Ofício"));
      tdr.save(new DocumentoTipoModel("Despacho"));
 
    };

    
  }
  
  @Bean
  public CommandLineRunner insertProcesso(ProcessoRepository procRepo) {

    return (args) -> {

    procRepo.save(new ProcessoModel("197.123.456/2013"));
      procRepo.save(new ProcessoModel("197.456.789/2015"));
      procRepo.save(new ProcessoModel("197.789.456/2018"));
 
    };
  };
}
