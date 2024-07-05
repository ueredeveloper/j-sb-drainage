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

	public TemplateModel() {
		super();
	}

	public TemplateModel(Long id) {
		super();
		this.id = id;
	}

	public TemplateModel(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public TemplateModel(String descricao, String html) {
		super();
		this.descricao = descricao;
		this.html = html;
	}

	public TemplateModel(Long id, String descricao, String html) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.html = html;
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

}