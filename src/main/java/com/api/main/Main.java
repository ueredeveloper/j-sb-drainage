package com.api.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.models.DocumentoModel;
import com.api.main.models.DocumentoTipoModel;
import com.api.main.models.EnderecoModel;
import com.api.main.repositories.DocumentoRepository;
import com.api.main.repositories.DocumentoTipoRepository;
import com.api.main.repositories.EnderecoRepository;

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

	/*
	 * @Bean public CommandLineRunner insertAnexos(AnexoRepository procRepo) {
	 * 
	 * return (args) -> {
	 * 
	 * procRepo.save(new AnexoModel("197.123.456/2013")); procRepo.save(new
	 * AnexoModel("197.456.789/2015"));
	 * 
	 * }; };
	 */

	/*
	 * @Bean public CommandLineRunner isertProcessos(ProcessoRepository repo) {
	 * 
	 * return (args) -> {
	 * 
	 * repo.save(new ProcessoModel("123/2015")); repo.save(new
	 * ProcessoModel("456/2015"));
	 * 
	 * }; }
	 */

	@Bean
	public CommandLineRunner insertEndereco(EnderecoRepository repo) {
		return (args) -> {

			repo.save(new EnderecoModel("Rua Novaes Terceiro, Casa 12"));
			repo.save(new EnderecoModel("Avenida Principal, Bloco A"));
			repo.save(new EnderecoModel("Rua das Flores, Apartamento 5"));
			repo.save(new EnderecoModel("Praça Central, Lote 30"));
			repo.save(new EnderecoModel("Alameda dos Sonhos, Casa 8"));
		};
	}

	@Bean
	public CommandLineRunner insertDocumentos(DocumentoRepository dr) {

		return (args) -> {

			dr.save(new DocumentoModel("12/2015", new EnderecoModel(1L)));
			dr.save(new DocumentoModel("13/2015"));

		};

	}
}
