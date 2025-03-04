package com.api.main.dto;

import java.util.HashSet;
import java.util.Set;

import com.api.main.models.DocumentoModel;
import com.api.main.models.ProcessoModel;


public class UsuarioDTO {

	private Long id;

	private String nome;

	private String cpfCnpj;

	private Set<DocumentoModel> documentos = new HashSet<>();
	
	private Set<ProcessoModel> processos = new HashSet<>();
	
	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(String nome) {
		super();
		this.nome = nome;
	}

	public UsuarioDTO(String nome, String cpfCnpj) {
		super();
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Set<DocumentoModel> getDocumentos() {
		return documentos;
	}


	


}
