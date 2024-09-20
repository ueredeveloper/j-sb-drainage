package com.api.main.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "endereco")

public class EnderecoModel {

	// v1.12.2
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true, unique = false, length = 500)
	private String logradouro;

	@Column(nullable = true, unique = false, length = 80)
	private String cidade;

	@Column(nullable = true, unique = false, length = 80)
	private String bairro;

	@Column(nullable = true, unique = false, length = 10)
	private String cep;

	@JsonIgnore
	@OneToMany(mappedBy = "endereco")
	private Set<DocumentoModel> documentos = new HashSet<>();

	@OneToMany(mappedBy = "endereco", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	// @JsonManagedReference
	private Set<InterferenciaModel> interferencias = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "estado", referencedColumnName = "id")
	private EstadoModel estado;

	public EnderecoModel() {
		super();
	}

	public EnderecoModel(Long id) {
		super();
		this.id = id;
	}

	public EnderecoModel(Long id, String logradouro) {
		super();
		this.id = id;
		this.logradouro = logradouro;
	}

	public EnderecoModel(String logradouro) {
		super();
		this.logradouro = logradouro;
	}

	public EnderecoModel(String logradouro, String cidade, String bairro, String cep) {
		super();
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.bairro = bairro;
		this.cep = cep;
	}

	public EnderecoModel(String logradouro, Set<InterferenciaModel> interferencias) {
		super();
		this.logradouro = logradouro;
		this.interferencias = interferencias;
	}

	public EnderecoModel(Long id, String logradouro, String cidade, String bairro, String cep, EstadoModel estado) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.bairro = bairro;
		this.cep = cep;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Set<DocumentoModel> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Set<DocumentoModel> documentos) {
		this.documentos = documentos;
	}

	public Set<InterferenciaModel> getInterferencias() {
		return interferencias;
	}

	public void setInterferencias(Set<InterferenciaModel> interferencias) {
		this.interferencias = interferencias;
	}

	public EstadoModel getEstado() {
		return estado;
	}

	public void setEstado(EstadoModel estado) {
		this.estado = estado;
	}

}
