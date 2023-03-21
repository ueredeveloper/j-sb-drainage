package com.api.main.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.Column;
import java.io.Serializable;

@Entity
@Table(name = "documentos")
public class DocumentoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long doc_id;

	@Column(nullable = true, unique = false, length = 40)
	private String doc_numeracao;

	@Column(nullable = true, unique = false, length = 40)
	private String doc_processo;

	@Column(nullable = true, unique = false, length = 40)
	private String doc_numeracao_sei;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doc_td_fk")
	private TipoDocumentoModel doc_td_fk;

	public long getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(long doc_id) {
		this.doc_id = doc_id;
	}

	public String getDoc_numeracao_sei() {
		return doc_numeracao_sei;
	}

	public void setDoc_numeracao_sei(String doc_numeracao_sei) {
		this.doc_numeracao_sei = doc_numeracao_sei;
	}

	public String getDoc_numeracao() {
		return doc_numeracao;
	}

	public void setDoc_numeracao(String doc_numeracao) {
		this.doc_numeracao = doc_numeracao;
	}

	public String getDoc_processo() {
		return doc_processo;
	}

	public void setDoc_processo(String doc_processo) {
		this.doc_processo = doc_processo;
	}

	public TipoDocumentoModel getDoc_td_fk() {
		return doc_td_fk;
	}

	public void setDoc_td_fk(TipoDocumentoModel doc_td_fk) {
		this.doc_td_fk = doc_td_fk;
	}

}