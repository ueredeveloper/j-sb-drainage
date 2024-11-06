package com.api.main.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.models.AnexoModel;
import com.api.main.models.DocumentoTipoModel;
import com.api.main.models.EnderecoModel;
import com.api.main.models.EstadoModel;
import com.api.main.models.FormaCaptacaoModel;
import com.api.main.models.LocalCaptacaoModel;
import com.api.main.models.SituacaoProcessoModel;
import com.api.main.models.SubtipoOutorgaModel;
import com.api.main.models.TipoAtoModel;
import com.api.main.models.TipoFinalidadeModel;
import com.api.main.models.TipoInterferenciaModel;
import com.api.main.models.TipoOutorgaModel;
import com.api.main.models.TipoPocoModel;
import com.api.main.repositories.AnexoRepository;
import com.api.main.repositories.DocumentoTipoRepository;
import com.api.main.repositories.EnderecoRepository;
import com.api.main.repositories.EstadoRepository;
import com.api.main.repositories.FormaCaptacaoRepository;
import com.api.main.repositories.LocalCaptacaoRepository;
import com.api.main.repositories.SituacaoProcessoRepository;
import com.api.main.repositories.SubtipoOutorgaRepository;
import com.api.main.repositories.TipoAtoRepository;
import com.api.main.repositories.TipoFinalidadeRepository;
import com.api.main.repositories.TipoInterferenciaRepository;
import com.api.main.repositories.TipoOutorgaRepository;
import com.api.main.repositories.TipoPocoRepository;


@Configuration
@RestController
public class DatabaseInitializerConfig {

	
	@Bean
	public CommandLineRunner insertDocumentoTipo(DocumentoTipoRepository r) {

		return (args) -> {

			r.save(new DocumentoTipoModel(1L, "Parecer"));
			r.save(new DocumentoTipoModel(2L, "Despacho"));
			r.save(new DocumentoTipoModel(3L, "Requerimento"));
			r.save(new DocumentoTipoModel(4L, "Ofício"));

		};
	}

	@Bean
	public CommandLineRunner InsertEstado(EstadoRepository r) {

		return (args) -> {

			r.save(new EstadoModel(1L, "DF"));
			r.save(new EstadoModel(2L, "SP"));
			r.save(new EstadoModel(3L, "BA"));

		};
	}

	@Bean
	public CommandLineRunner insertTipoInterferencia(TipoInterferenciaRepository r) {

		return (args) -> {

			r.save(new TipoInterferenciaModel(1L, "Superficial"));
			r.save(new TipoInterferenciaModel(2L, "Subterrânea"));
			r.save(new TipoInterferenciaModel(3L, "Lançamento de Águas Pluviais"));
			r.save(new TipoInterferenciaModel(4L, "Canal"));
			r.save(new TipoInterferenciaModel(5L, "Caminhão Pipa"));
			r.save(new TipoInterferenciaModel(6L, "Lançamento de Efluentes"));
			r.save(new TipoInterferenciaModel(7L, "Barragem"));

		};
	}

	@Bean
	public CommandLineRunner insertAnexo(AnexoRepository procRepo) {

		return (args) -> {

			procRepo.save(new AnexoModel("197.123.456/2013"));
			procRepo.save(new AnexoModel("197.456.789/2015"));

		};
	};

	@Bean
	public CommandLineRunner insertEndereco(EnderecoRepository r) {
		return (args) -> {

			r.save(new EnderecoModel("Rua Novaes Terceiro, Casa 12", "a", "b", "c"));
			r.save(new EnderecoModel("Avenida Principal, Bloco A", "e", "f", "g"));
			r.save(new EnderecoModel("Rua das Flores, Apartamento 5", "h", "i", "j"));

		};
	}

	@Bean
	public CommandLineRunner insertTipoOutorga(TipoOutorgaRepository r) {

		/*
		 * Tipo_Outorga (1, N'Outorga') (2, N'Outorga Prévia') (3, N'Registro')
		 */

		return (args) -> {

			r.save(new TipoOutorgaModel(1L, "Outorga"));
			r.save(new TipoOutorgaModel(2L, "Outorga Prévia"));
			r.save(new TipoOutorgaModel(3L, "Registro"));

		};

	}

	@Bean
	public CommandLineRunner insertSubtipoOutorga(SubtipoOutorgaRepository r) {

		return (args) -> {

			r.save(new SubtipoOutorgaModel(1L, "Renovação"));
			r.save(new SubtipoOutorgaModel(2L, "Modificação"));
			r.save(new SubtipoOutorgaModel(3L, "Transferência"));
			r.save(new SubtipoOutorgaModel(4L, "Transferência"));
			r.save(new SubtipoOutorgaModel(5L, "Suspensão/Revogação"));
			r.save(new SubtipoOutorgaModel(6L, ""));

		};

	}

	@Bean
	public CommandLineRunner insertSituacaoProcesso(SituacaoProcessoRepository r) {
		return (args) -> {
			r.save(new SituacaoProcessoModel(1L, "Arquivado"));
			r.save(new SituacaoProcessoModel(2L, "Em Análise"));
			r.save(new SituacaoProcessoModel(3L, "Outorgado"));
			r.save(new SituacaoProcessoModel(4L, "Vencido"));
			r.save(new SituacaoProcessoModel(5L, "Arquivado (CNRH 16)"));
			r.save(new SituacaoProcessoModel(6L, "Pendência"));
			r.save(new SituacaoProcessoModel(7L, "Indeferido"));
			r.save(new SituacaoProcessoModel(8L, "Revogado"));

		};
	}

	@Bean
	CommandLineRunner insertTipoAto(TipoAtoRepository r) {
		return (args) -> {
			r.save(new TipoAtoModel(1L, "Despacho"));
			r.save(new TipoAtoModel(2L, "Portaria"));
			r.save(new TipoAtoModel(3L, "Despacho"));
			r.save(new TipoAtoModel(4L, "Registro"));
			r.save(new TipoAtoModel(5L, "Resolução"));
			r.save(new TipoAtoModel(6L, "Resolução ANA"));
			r.save(new TipoAtoModel(7L, "Portaria DNAEE"));
		};
	}

	@Bean
	CommandLineRunner insertFormaCaptacao(FormaCaptacaoRepository r) {
		return (args) -> {
			r.save(new FormaCaptacaoModel(1L, "Bombeamento"));
			r.save(new FormaCaptacaoModel(2L, "Gravidade"));
		};
	}

	@Bean
	CommandLineRunner insertLocalCaptacao(LocalCaptacaoRepository r) {
		return (args) -> {
			r.save(new LocalCaptacaoModel(1L, "Nascente"));
			r.save(new LocalCaptacaoModel(2L, "Rio"));
			r.save(new LocalCaptacaoModel(3L, "Reservatório"));
			r.save(new LocalCaptacaoModel(4L, "Canal"));
			r.save(new LocalCaptacaoModel(5L, "Lago Natural"));
		};
	}

	@Bean
	CommandLineRunner insertTipoPoco(TipoPocoRepository r) {
		return (args) -> {
			r.save(new TipoPocoModel(1L, "Manual"));
			r.save(new TipoPocoModel(2L, "Tubular Raso"));
			r.save(new TipoPocoModel(3L, "Tubular Profundo"));

		};
	}

	@Bean
	CommandLineRunner insertTipoFinalidade(TipoFinalidadeRepository r) {
		return (args) -> {
			r.save(new TipoFinalidadeModel(1L, "Requerida"));
			r.save(new TipoFinalidadeModel(2L, "Autorizada"));

		};
	}

}