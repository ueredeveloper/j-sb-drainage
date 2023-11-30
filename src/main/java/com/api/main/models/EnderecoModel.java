package com.api.main.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "endereco")
public class EnderecoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long endId;

	@Column(nullable = true, unique = false, length = 500)
	private String endLogradouro;

	@Column(nullable = true, unique = false, length = 80)
	private String endCidade;

	@Column(nullable = true, unique = false, length = 10)
	private String endCEP;

	@JsonIgnore
	@OneToMany(mappedBy = "docEndereco")
	private List<DocumentoModel> endDocumentos = new ArrayList<DocumentoModel>();

	public EnderecoModel() {
		super();
	}

	public EnderecoModel(String endLogradouro) {
		super();
		this.endLogradouro = endLogradouro;
	}
	

	public EnderecoModel(Long endId) {
		super();
		this.endId = endId;
	}

	public EnderecoModel(Long endId, String endLogradouro, String endCidade, String endCEP,
			List<DocumentoModel> endDocumentos) {
		super();
		this.endId = endId;
		this.endLogradouro = endLogradouro;
		this.endCidade = endCidade;
		this.endCEP = endCEP;
		this.endDocumentos = endDocumentos;
	}

	public Long getEndId() {
		return endId;
	}

	public void setEndId(Long endId) {
		this.endId = endId;
	}

	public String getEndLogradouro() {
		return endLogradouro;
	}

	public void setEndLogradouro(String endLogradouro) {
		this.endLogradouro = endLogradouro;
	}

	public String getEndCidade() {
		return endCidade;
	}

	public void setEndCidade(String endCidade) {
		this.endCidade = endCidade;
	}

	public String getEndCEP() {
		return endCEP;
	}

	public void setEndCEP(String endCEP) {
		this.endCEP = endCEP;
	}

	public List<DocumentoModel> getEndDocumentos() {
		return endDocumentos;
	}

	public void setEndDocumentos(List<DocumentoModel> endDocumentos) {
		this.endDocumentos = endDocumentos;
	}

}
