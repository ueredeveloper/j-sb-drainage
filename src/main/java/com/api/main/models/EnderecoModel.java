package com.api.main.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@Column(nullable = true, unique = false, length = 80)
	private String endBairro;

	@Column(nullable = true, unique = false, length = 10)
	private String endCep;
	
	@Column(nullable = true, unique = false, length = 2)
	private String endEstado;

	@JsonIgnore
	@OneToMany(mappedBy = "docEndereco")
	private List<DocumentoModel> endDocumentos = new ArrayList<DocumentoModel>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "interEndereco", fetch = FetchType.EAGER)
	//private List<InterferenciaModel> endInterferencias = new ArrayList<InterferenciaModel>();
	private Set<InterferenciaModel> endInterferencias = new HashSet<>();

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

	public EnderecoModel(Long endId, String endLogradouro, String endCidade, String endCep,
			List<DocumentoModel> endDocumentos) {
		super();
		this.endId = endId;
		this.endLogradouro = endLogradouro;
		this.endCidade = endCidade;
		this.endCep = endCep;
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

	public String getEndBairro() {
		return endBairro;
	}

	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}

	public String getEndCep() {
		return endCep;
	}

	public void setEndCep(String endCep) {
		this.endCep = endCep;
	}

	public String getEndEstado() {
		return endEstado;
	}

	public void setEndEstado(String endEstado) {
		this.endEstado = endEstado;
	}

	public List<DocumentoModel> getEndDocumentos() {
		return endDocumentos;
	}

	public void setEndDocumentos(List<DocumentoModel> endDocumentos) {
		this.endDocumentos = endDocumentos;
	}

	public Set<InterferenciaModel> getEndInterferencias() {
		return endInterferencias;
	}

	public void setEndInterferencias(Set<InterferenciaModel> endInterferencias) {
		this.endInterferencias = endInterferencias;
	}

	

}
