package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "template")
public class TemplateModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true, unique = false, length = 250)
	private String descricao;

	@Column(nullable = true, unique = false)
	@Lob
	private String html;
	@Column(nullable = true, unique = false, length = 250)
	private String pasta;
	@Column(nullable = true, unique = false, length = 250)
	private String nome;
	@Column(nullable = true, unique = false)
	@Lob
	private String conteudo;
	
	public TemplateModel() {
		super();
	}

	public TemplateModel(Long id, String descricao, String html, String pasta, String nome, String conteudo) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.html = html;
		this.pasta = pasta;
		this.nome = nome;
		this.conteudo = conteudo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getPasta() {
		return pasta;
	}

	public void setPasta(String pasta) {
		this.pasta = pasta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	

	
}