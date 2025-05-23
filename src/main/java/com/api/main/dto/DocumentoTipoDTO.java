package com.api.main.dto;

import java.util.HashSet;
import java.util.Set;

import com.api.main.models.DocumentoModel;
import com.api.main.models.TemplateModel;


public class DocumentoTipoDTO {

	private Long id;

	private String descricao;

	private Set<DocumentoModel> documentos = new HashSet<>();

	private Set<TemplateModel> templates = new HashSet<>();

	public DocumentoTipoDTO() {
		super();
	}

	public DocumentoTipoDTO(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
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

	public Set<DocumentoModel> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Set<DocumentoModel> documentos) {
		this.documentos = documentos;
	}

	public Set<TemplateModel> getTemplates() {
		return templates;
	}

	public void setTemplates(Set<TemplateModel> templates) {
		this.templates = templates;
	}
	

}