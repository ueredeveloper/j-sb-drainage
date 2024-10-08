package com.api.main.dto;

import java.util.HashSet;
import java.util.Set;

import com.api.main.models.DocumentoModel;

public class UsuarioDTO {

	private Long id;

	private String nome;

	private Long cpfCnpj;

	private Set<DocumentoModel> documentos = new HashSet<>();
	
	

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(String nome) {
		super();
		this.nome = nome;
	}
	
	
	public UsuarioDTO(String nome, Set<DocumentoModel> documentos) {
		super();
		this.nome = nome;
		this.documentos = documentos;
	}

	public UsuarioDTO(Set<DocumentoModel> documentos) {
		super();
		this.documentos = documentos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(Long cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Set<DocumentoModel> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Set<DocumentoModel> documentos) {
		this.documentos = documentos;
	}

}
