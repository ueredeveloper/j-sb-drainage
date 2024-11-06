package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "template")
public class TemplateModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = true, unique = false, length = 250)
	private String nome;
	@Column(nullable = true, unique = false, length = 250)
	private String descricao;
	@Column(nullable = true, unique = false, length = 250)
	private String diretorio;
	@Column(nullable = true, unique = false, length = 250)
	private String arquivo;
	@Column(nullable = true, unique = false)
	@Lob
	private String conteudo;


	public TemplateModel() {
		super();
	}
	
	
	public TemplateModel(Long id, String nome, String descricao, String diretorio, String arquivo, String conteudo) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.diretorio = diretorio;
		this.arquivo = arquivo;
		this.conteudo = conteudo;
	}


	public TemplateModel(String nome, String descricao, String diretorio, String arquivo, String conteudo) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.diretorio = diretorio;
		this.arquivo = arquivo;
		this.conteudo = conteudo;
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


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getDiretorio() {
		return diretorio;
	}


	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}


	public String getArquivo() {
		return arquivo;
	}


	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}


	public String getConteudo() {
		return conteudo;
	}


	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	
	
	

}