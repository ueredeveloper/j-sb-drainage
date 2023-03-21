package com.api.main.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	private long td_id;

	@Column(nullable = true, unique = false, length = 40)
	private String td_descricao;

	@OneToMany(mappedBy = "doc_td_fk", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DocumentoModel> documentos = new ArrayList<>();

	public long getTd_id() {
		return td_id;
	}

	public void setTd_id(long td_id) {
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

}