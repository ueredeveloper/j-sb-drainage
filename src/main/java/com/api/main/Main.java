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
import com.api.main.models.EstadoModel;
import com.api.main.models.InterferenciaTipoModel;
import com.api.main.repositories.DocumentoRepository;
import com.api.main.repositories.DocumentoTipoRepository;
import com.api.main.repositories.EnderecoRepository;
import com.api.main.repositories.EstadoRepository;
import com.api.main.repositories.InterferenciaTipoRepository;

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
	public CommandLineRunner insertDocumentosTipos(DocumentoTipoRepository r) {

		return (args) -> {

			r.save(new DocumentoTipoModel("Requerimento"));
			r.save(new DocumentoTipoModel("Ofício"));
			r.save(new DocumentoTipoModel("Despacho"));

		};
	}
	
	@Bean
	public CommandLineRunner insertEstados(EstadoRepository r) {

		return (args) -> {

			r.save(new EstadoModel("DF"));
			r.save(new EstadoModel("SP"));
			r.save(new EstadoModel("BA"));

		};
	}

	@Bean
	public CommandLineRunner insertTipoInterferencia(InterferenciaTipoRepository r) {

		return (args) -> {

			r.save(new InterferenciaTipoModel("Superficial"));
			r.save(new InterferenciaTipoModel("Subterrânea"));
			r.save(new InterferenciaTipoModel("Lançamento"));

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
	public CommandLineRunner insertEndereco(EnderecoRepository r) {
		return (args) -> {

			r.save(new EnderecoModel("Rua Novaes Terceiro, Casa 12"));
			r.save(new EnderecoModel("Avenida Principal, Bloco A"));
			r.save(new EnderecoModel("Rua das Flores, Apartamento 5"));

		};
	}

	@Bean
	public CommandLineRunner insertDocumentos(DocumentoRepository r) {

		return (args) -> {

			r.save(new DocumentoModel("12/2015", new EnderecoModel(1L)));
			r.save(new DocumentoModel("13/2015"));

		};

	}
}
