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

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dtId;

	@Column(nullable = true, unique = false, length = 40)
	private String dtDescricao;

	@JsonIgnore
	@OneToMany(mappedBy = "docTipo")
	private Set<DocumentoModel> dtDocumentos = new HashSet<>();

	// constructors
	public DocumentoTipoModel() {
		super();
	}

	public DocumentoTipoModel(String dtDescricao) {
		super();
		this.dtDescricao = dtDescricao;
	}

	public Long getDtId() {
		return dtId;
	}

	public void setDtId(Long dtId) {
		this.dtId = dtId;
	}

	public String getDtDescricao() {
		return dtDescricao;
	}

	public void setDtDescricao(String dtDescricao) {
		this.dtDescricao = dtDescricao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<DocumentoModel> getDtDocumentos() {
		return dtDocumentos;
	}

	public void setDtDocumentos(Set<DocumentoModel> dtDocumentos) {
		this.dtDocumentos = dtDocumentos;
	}

}