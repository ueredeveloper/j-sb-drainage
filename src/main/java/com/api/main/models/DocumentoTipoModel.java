package com.api.main.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "documento_tipo")
public class DocumentoTipoModel implements Serializable {
	// v1.12.2
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true, unique = false, length = 40)
	private String descricao;

	@JsonIgnore
	@OneToMany(mappedBy = "tipo")
	private Set<DocumentoModel> documentos = new HashSet<>();

	// constructors
	public DocumentoTipoModel() {
		super();
	}

	public DocumentoTipoModel(String descricao) {
		super();
		this.descricao = descricao;
	}

	public DocumentoTipoModel(Long id) {
		super();
		this.id = id;
	}

	public DocumentoTipoModel(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public DocumentoTipoModel(Long id, String descricao, Set<DocumentoModel> documentos) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.documentos = documentos;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}