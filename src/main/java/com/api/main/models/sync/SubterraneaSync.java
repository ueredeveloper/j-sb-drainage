package com.api.main.models.sync;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.locationtech.jts.geom.Geometry;


@Entity
@Table(name = "subterranea_sync")
public class SubterraneaSync {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
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

	@Column(name = "emp_id")
	private Long empId;

	@Column(name = "emp_endereco")
	private String empEndereco;

	@Column(name = "int_processo")
	private String intProcesso;

	@Column(name = "int_id", nullable = false, unique = true)
	private Long intId;

	@Column(name = "int_num_ato")
	private String intNumAto;

	@Column(name = "int_latitude")
	private Double intLatitude;

	@Column(name = "int_longitude")
	private Double intLongitude;
	
	@Column(name = "int_shape", columnDefinition = "geometry(POINT, 4674)")
	private Geometry intShape;

	@Column(name = "int_data_publicacao")
	private String intDataPublicacao;

	@Column(name = "int_data_vencimento")
	private String intDataVencimento;

	@Column(name = "ti_id")
	private Long tiId;

	@Column(name = "ti_descricao")
	private String tiDescricao;

	@Column(name = "sp_id")
	private Long spId;

	@Column(name = "sp_descricao")
	private String spDescricao;

	@Column(name = "tp_id")
	private Long tpId;

	@Column(name = "tp_descricao")
	private String tpDescricao;

	@Column(name = "to_id")
	private Long toId;

	@Column(name = "to_descricao", length = 250)
	private String toDescricao;

	@Column(name = "bh_id")
	private Long bhId;

	@Column(name = "bh_nome")
	private String bhNome;

	@Column(name = "uh_id")
	private Long uhId;

	@Column(name = "uh_nome")
	private String uhNome;

	@Column(name = "hg_codigo")
	private String hgCodigo;

	@Column(name = "hg_sistema")
	private String hgSistema;

	@Column(name = "hg_subsistema")
	private String hgSubsistema;

	@Column(name = "fin_finalidade", columnDefinition = "json")
	private String finFinalidade;

	@Column(name = "dt_demanda", columnDefinition = "json")
	private String dtDemanda;

}
