package com.api.main.dto;

import java.util.HashSet;
import java.util.Set;

import com.api.main.models.DocumentoModel;
import com.api.main.models.EstadoModel;
import com.api.main.models.InterferenciaModel;


public class EnderecoDTO {

	private Long id;

	private String logradouro;

	private String cidade;

	private String cep;

	private String bairro;

	private EstadoModel estado;

	private Set<DocumentoModel> documentos = new HashSet<>();

	private Set<InterferenciaModel> interferencias = new HashSet<>();

	public EnderecoDTO() {
		super();
	}
	
	public EnderecoDTO(Long id) {
		super();
		this.id = id;
	}

	public EnderecoDTO(Long id, String logradouro, String cidade, String cep, String bairro, EstadoModel estado) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.cep = cep;
		this.bairro = bairro;
		this.estado = estado;
	}
	
	public EnderecoDTO(Long id, String logradouro, String cidade, String cep, String bairro, EstadoModel estado,
			Set<InterferenciaModel> interferencias) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.cep = cep;
		this.bairro = bairro;
		this.estado = estado;
		this.interferencias = interferencias;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public EstadoModel getEstado() {
		return estado;
	}

	public void setEstado(EstadoModel estado) {
		this.estado = estado;
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

}
