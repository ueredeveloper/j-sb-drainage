package com.api.main.models.sync;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.locationtech.jts.geom.Geometry;



@Entity
@Table(name = "superficial_sync")
public class SuperficialSync {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "us_id")
	private Long usId;

	@Column(name = "us_nome", length = 250)
	private String usNome;

	@Column(name = "us_cpf_cnpj", length = 250)
	private String usCpfCnpj;

	@Column(name = "emp_id")
	private Long empId;

	@Column(name = "emp_endereco", length = 500)
	private String empEndereco;

	@Column(name = "int_processo", length = 250)
	private String intProcesso;

	@Column(name = "int_id", nullable = false, unique = true)
	private Long intId;

	@Column(name = "int_num_ato", length = 250)
	private String intNumAto;

	@Column(name = "int_latitude")
	private Double intLatitude;

	@Column(name = "int_longitude")
	private Double intLongitude;

	@Column(name = "int_shape", columnDefinition = "geometry(POINT, 4674)")
	private Geometry intShape;
	
	@Column(name = "ti_id")
	private Long tiId;

	@Column(name = "ti_descricao", length = 250)
	private String tiDescricao;

	@Column(name = "sp_id")
	private Long spId;

	@Column(name = "sp_descricao", length = 250)
	private String spDescricao;

	@Column(name = "bh_id")
	private Long bhId;

	@Column(name = "bh_nome", length = 250)
	private String bhNome;

	@Column(name = "uh_id")
	private Long uhId;

	@Column(name = "uh_nome", length = 250)
	private String uhNome;

	@Column(name = "fin_finalidade", columnDefinition = "json")
	private String finFinalidade;

	@Column(name = "dt_demanda", columnDefinition = "json")
	private String dtDemanda;

	@Column(name = "us_email", length = 250)
	private String usEmail;

	@Column(name = "us_cep", length = 250)
	private String usCep;

	@Column(name = "us_endereco", length = 250)
	private String usEndereco;

	@Column(name = "us_caixa_postal", length = 250)
	private String usCaixaPostal;

	@Column(name = "us_bairro", length = 250)
	private String usBairro;

	@Column(name = "us_telefone_1", length = 250)
	private String usTelefone1;

	@Column(name = "int_data_publicacao", length = 250)
	private String intDataPublicacao;

	@Column(name = "int_data_vencimento", length = 250)
	private String intDataVencimento;

	@Column(name = "sup_area_irrigada", length = 250)
	private String supAreaIrrigada;

	@Column(name = "sup_area_propriedade", length = 250)
	private String supAreaPropriedade;

	@Column(name = "to_id")
	private Long toId;

	@Column(name = "to_descricao", length = 250)
	private String toDescricao;

	@Column(name = "uh_codigo")
	private Long uhCodigo;

}