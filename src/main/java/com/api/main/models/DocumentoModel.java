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


@Entity
@Table(name = "documento")
public class DocumentoModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long doc_id;

	@Column(nullable = true, unique = false, length = 40)
	private String doc_numero;

	@Column(nullable = true, unique = false, length = 40)
	private String doc_processo;

	@Column(nullable = true, unique = false, length = 40)
	private String doc_sei;

  // está retirando o tipo de documento relacionado, trazendo só o id. Ex: documento: {doc_tipo:{dt_id: 1}} => documento: {doc_tipo: 1}
  //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "dt_id", scope = DocumentoTipoModel.class)
	@ManyToOne
	@JoinColumn(name = "doc_tipo")
	private DocumentoTipoModel doc_tipo;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(Long doc_id) {
		this.doc_id = doc_id;
	}

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

	public DocumentoTipoModel getDoc_tipo() {
		return doc_tipo;
	}

	public void setDoc_tipo(DocumentoTipoModel doc_tipo) {
		this.doc_tipo = doc_tipo;
	}

  
  

	
}