package com.api.main.dto;

public class TemplateDTO {

	private Long id;
	private String descricao;
	private String html;

	public TemplateDTO() {
		super();
	}
	
	public TemplateDTO(Long id) {
		super();
		this.id = id;
	}

	public TemplateDTO(String html) {
		super();
		this.html = html;
	}
	
	public TemplateDTO(String descricao, String html) {
		super();
		this.descricao = descricao;
		this.html = html;
	}

	public TemplateDTO(Long id, String descricao, String html) {
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

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
