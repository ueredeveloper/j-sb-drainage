package com.api.main.models.sync;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name = "barragem_sync")
public class BarragemSync {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "us_id")
	private Long usId;

	@Column(name = "us_nome")
	private String usNome;

	@Column(name = "us_cpf_cnpj")
	private String usCpfCnpj;

	@Column(name = "us_email")
	private String usEmail;

	@Column(name = "us_cep")
	private String usCep;

	@Column(name = "us_endereco")
	private String usEndereco;

	@Column(name = "us_caixa_postal")
	private String usCaixaPostal;

	@Column(name = "us_bairro")
	private String usBairro;

	@Column(name = "us_telefone_1")
	private String usTelefone1;

	@Column(name = "us_telefone_2")
	private String usTelefone2;

	@Column(name = "emp_id")
	private Long empId;

	@Column(name = "emp_endereco")
	private String empEndereco;

	@Column(name = "sp_id")
	private Long spId;

	@Column(name = "sp_descricao")
	private String spDescricao;

	@Column(name = "to_id")
	private Long toId;

	@Column(name = "to_descricao")
	private String toDescricao;

	@Column(name = "int_id", nullable = false, unique = true)
	private Long intId;

	@Column(name = "int_latitude")
	private Double intLatitude;

	@Column(name = "int_longitude")
	private Double intLongitude;

	@Column(name = "int_shape", columnDefinition = "geometry(POINT, 4674)")
	private Geometry intShape;

	@Column(name = "int_data_publicacao")
	private LocalDate intDataPublicacao;

	@Column(name = "int_data_vencimento")
	private LocalDate intDataVencimento;

	@Column(name = "int_processo")
	private String intProcesso;

	@Column(name = "int_num_ato")
	private String intNumAto;

	@Column(name = "uh_id")
	private Long uhId;

	@Column(name = "uh_nome")
	private String uhNome;

	@Column(name = "bh_id")
	private Long bhId;
	@Column(name = "bh_nome")
	private String bhNome;

	@Column(name = "ti_id")
	private Long tiId;
	@Column(name = "ti_descricao")
	private String tiDescricao;

	@Column(name = "data_fim")
	private LocalDate dataFim;

	@Column(name = "volume_hm3")
	private Double volumeHm3;

	@Column(name = "area_inundada_ha")
	private Double areaInundadaHa;

	@Column(name = "altura_m")
	private Double alturaM;

	@Column(name = "largura_crista_m")
	private Double larguraCristaM;

	@Column(name = "comprimento_crista_m")
	private Double comprimentoCristaM;

	@Column(name = "vazao_remanescente_ls")
	private Double vazaoRemanescenteLs;

	@Column(name = "area_contribuicao")
	private Double areaContribuicao;

	@Column(name = "vazao_orgao_extravasor_ls")
	private Double vazaoOrgaoExtravasorLs;

	@Column(name = "tem_eclusa")
	private Boolean temEclusa;

	@Column(name = "data_ult_inspecao")
	private LocalDate dataUltInspecao;

	@Column(name = "tem_plano_acao_emergencia")
	private Boolean temPlanoAcaoEmergencia;

	@Column(name = "tem_plano_seguranca")
	private Boolean temPlanoSeguranca;

	@Column(name = "tem_revisao_periodica")
	private Boolean temRevisaoPeriodica;

	@Column(name = "tem_proj_executivo")
	private Boolean temProjExecutivo;

	@Column(name = "tem_proj_construido")
	private Boolean temProjConstruido;

	@Column(name = "tem_proj_basico")
	private Boolean temProjBasico;

	@Column(name = "tem_proj_conceitual")
	private Boolean temProjConceitual;

	@Column(name = "data_inicio")
	private LocalDate dataInicio;

	@Column(name = "crit_vazao_org_extravasor")
	private String critVazaoOrgExtravasor;

	@Column(name = "fase_vida_barragem")
	private String faseVidaBarragem;

	@Column(name = "regulada_pnsb")
	private String reguladaPnsb;

	@Column(name = "subt_out")
	private String subtOut;

	@Column(name = "rio_barragem")
	private String rioBarragem;

	@Column(name = "id_dominio_barragem")
	private String idDominioBarragem;

	@Column(name = "nome_barragem")
	private String nomeBarragem;

	@Column(name = "nome_barragem2")
	private String nomeBarragem2;

	@Column(name = "tipo_material")
	private String tipoMaterial;

	@Column(name = "tipo_estrutural")
	private String tipoEstrutural;

	@Column(name = "observacao")
	private String observacao;

	@Column(name = "mecanismo_controle_extravasor")
	private String mecanismoControleExtravasor;

	@Column(name = "class_barr")
	private String classBarr;

	@Column(name = "cod_snibs")
	private String codSnibs;

	@Column(name = "tipo_inspecao")
	private String tipoInspecao;

	@Column(name = "nivel_perigo")
	private String nivelPerigo;

	@Column(name = "categoria_risco")
	private String categoriaRisco;

	@Column(name = "dano_potencial")
	private String danoPotencial;

	@Column(name = "classe_residuo_barragem")
	private String classeResiduoBarragem;

}
