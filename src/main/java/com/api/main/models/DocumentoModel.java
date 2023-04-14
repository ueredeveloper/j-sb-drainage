package com.api.main.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "documentos")
public class DocumentoModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long doc_id;

	public Long getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(Long doc_id) {
		this.doc_id = doc_id;
	}

	@Column(nullable = true, unique = false, length = 40)
	private String doc_numero;

	@Column(nullable = true, unique = false, length = 40)
	private String doc_processo;

	@Column(nullable = true, unique = false, length = 40)
	private String doc_sei;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "td_id", scope = TipoDocumentoModel.class)
	@ManyToOne
	@JoinColumn(name = "doc_tipo")
	private TipoDocumentoModel doc_tipo;


	public String getDoc_numero() {
		return doc_numero;
	}

	public void setDoc_numero(String doc_numero) {
		this.doc_numero = doc_numero;
	}

	public String getDoc_processo() {
		return doc_processo;
	}

	public void setDoc_processo(String doc_processo) {
		this.doc_processo = doc_processo;
	}

	public String getDoc_sei() {
		return doc_sei;
	}

	public void setDoc_sei(String doc_sei) {
		this.doc_sei = doc_sei;
	}

	

	public TipoDocumentoModel getDoc_tipo() {
		return doc_tipo;
	}

	public void setDoc_tipo(TipoDocumentoModel doc_tipo) {
		this.doc_tipo = doc_tipo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}