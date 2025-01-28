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
@Table(name = "lancamento_pluviais_sync")
public class LancamentoPluviais {

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

	@Column(name = "int_id", nullable = false, unique = true)
	private Long intId;

	@Column(name = "int_latitude")
	private Double intLatitude;

	@Column(name = "int_longitude")
	private Double intLongitude;

	@Column(name = "int_shape", columnDefinition = "geometry(POINT, 4674)")
	private Geometry intShape;

	@Column(name = "int_num_ato")
	private String intNumAto;

	@Column(name = "int_data_vencimento")
	private LocalDate intDataVencimento;

	@Column(name = "int_data_publicacao")
	private LocalDate intDataPublicacao;

	@Column(name = "int_emp_id")
	private Long intEmpId;

	@Column(name = "int_uh")
	private Long intUh;

	@Column(name = "int_ti")
	private Long intTi;

	@Column(name = "int_to")
	private Long intTo;

	@Column(name = "int_ta")
	private Long intTa;

	@Column(name = "int_situacao_id")
	private Long intSituacaoId;

	@Column(name = "int_int_antigo")
	private Long intIntAntigo;

	@Column(name = "int_nome")
	private String intNome;

	@Column(name = "int_verificado")
	private String intVerificado;

	@Column(name = "int_processo")
	private String intProcesso;

	@Column(name = "int_observacao")
	private String intObservacao;

	@Column(name = "ti_id")
	private Long tiId;

	@Column(name = "ti_descricao")
	private String tiDescricao;

	@Column(name = "sp_id")
	private Long spId;

	@Column(name = "sp_descricao")
	private String spDescricao;

	@Column(name = "ta_id")
	private Long taId;

	@Column(name = "ta_descricao")
	private String taDescricao;

	@Column(name = "uh_id")
	private Long uhId;

	@Column(name = "uh_nome")
	private String uhNome;

	@Column(name = "bh_id")
	private Long bhId;

	@Column(name = "bh_nome")
	private String bhNome;

	@Column(name = "end_endereco")
	private String endEndereco;

	@Column(name = "end_cidade")
	private String endCidade;

	@Column(name = "to_id")
	private Long toId;

	@Column(name = "to_descricao")
	private String toDescricao;

}
