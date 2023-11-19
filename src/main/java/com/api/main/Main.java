package com.api.main;

import com.api.main.models.ProcessoModel;
import com.api.main.models.ProcessoPrincipalModel;
import com.api.main.models.DocumentoModel;
import com.api.main.models.DocumentoTipoModel;
import com.api.main.repositories.ProcessoRepository;
import com.api.main.repositories.DocumentoRepository;
import com.api.main.repositories.DocumentoTipoRepository;
import com.api.main.repositories.ProcessoPrincipalRepository;

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
	public CommandLineRunner insertDocumentosTipos(DocumentoTipoRepository tdr) {

		return (args) -> {

			tdr.save(new DocumentoTipoModel("Requerimento"));
			tdr.save(new DocumentoTipoModel("Ofício"));
			tdr.save(new DocumentoTipoModel("Despacho"));

		};
	}

	@Bean
	public CommandLineRunner insertProcessos(ProcessoRepository procRepo) {

		return (args) -> {

			procRepo.save(new ProcessoModel("197.123.456/2013"));
			procRepo.save(new ProcessoModel("197.456.789/2015"));
			procRepo.save(new ProcessoModel("197.789.456/2018"));

		};
	};

	@Bean
	public CommandLineRunner insertDocumentos(DocumentoRepository dr) {

		return (args) -> {

			dr.save(new DocumentoModel("12/2015"));
			dr.save(new DocumentoModel("13/2015"));
			dr.save(new DocumentoModel("14/2015"));

		};

	}
	
	@Bean
	public CommandLineRunner isertProcessosPrincipais(ProcessoPrincipalRepository repo) {

		return (args) -> {

			repo.save(new ProcessoPrincipalModel("800/2015"));
			repo.save(new ProcessoPrincipalModel("800/2015"));

		};

	}
}
