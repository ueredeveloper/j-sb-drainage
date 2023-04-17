package com.api.main.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tipos_documentos")
public class TipoDocumentoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	// default constructor
	public TipoDocumentoModel() {

	}

	// descrição constructor
	public TipoDocumentoModel(String td_descricao) {
		this.td_descricao = td_descricao;
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long td_id;

	@Column(nullable = true, unique = false, length = 40)
	private String td_descricao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "doc_tipo")
	private List<DocumentoModel> documentos = new ArrayList<DocumentoModel>();

	public Long getTd_id() {
		return td_id;
	}

	public void setTd_id(Long td_id) {
		this.td_id = td_id;
	}

	public String getTd_descricao() {
		return td_descricao;
	}

	public void setTd_descricao(String td_descricao) {
		this.td_descricao = td_descricao;
	}
	
	public List<DocumentoModel> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoModel> documentos) {
		this.documentos = documentos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}