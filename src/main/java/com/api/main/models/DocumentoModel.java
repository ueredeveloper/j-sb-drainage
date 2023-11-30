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
	private Long docId;

	@Column(nullable = true, unique = false, length = 40)
	private String docNumero;

	@ManyToOne
	@JoinColumn(name = "docProcesso")
	private ProcessoModel docProcesso;

	@Column(nullable = true, unique = false, length = 40)
	private String docSei;

	// está retirando o tipo de documento relacionado, trazendo só o id. Ex:
	// documento: {doc_tipo:{dt_id: 1}} => documento: {doc_tipo: 1}
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "dt_id", scope = DocumentoTipoModel.class)
	@ManyToOne
	@JoinColumn(name = "docTipo")
	private DocumentoTipoModel docTipo;
	
	@ManyToOne
	@JoinColumn(name = "docEndereco")
	private EnderecoModel docEndereco;
	

	public DocumentoModel() {
		super();
	}
	
	public DocumentoModel(String docNumero) {
		super();
		this.docNumero = docNumero;
	}
	
	

	public DocumentoModel(String docNumero, String docSei) {
		super();
		this.docNumero = docNumero;
		this.docSei = docSei;
	}

	public DocumentoModel(Long docId, String docNumero, ProcessoModel docProcesso, String docSei,
			DocumentoTipoModel docTipo, EnderecoModel docEndereco) {
		super();
		this.docId = docId;
		this.docNumero = docNumero;
		this.docProcesso = docProcesso;
		this.docSei = docSei;
		this.docTipo = docTipo;
		this.docEndereco = docEndereco;
	}
	
	

	public DocumentoModel(String docNumero, EnderecoModel docEndereco) {
		super();
		this.docNumero = docNumero;
		this.docEndereco = docEndereco;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public String getDocNumero() {
		return docNumero;
	}

	public void setDocNumero(String docNumero) {
		this.docNumero = docNumero;
	}


	public DocumentoTipoModel getDocTipo() {
		return docTipo;
	}

	public void setDocTipo(DocumentoTipoModel docTipo) {
		this.docTipo = docTipo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ProcessoModel getDocProcesso() {
		return docProcesso;
	}

	public void setDocProcesso(ProcessoModel docProcesso) {
		this.docProcesso = docProcesso;
	}

	public EnderecoModel getDocEndereco() {
		return docEndereco;
	}

	public void setDocEndereco(EnderecoModel docEndereco) {
		this.docEndereco = docEndereco;
	}

	public String getDocSei() {
		return docSei;
	}

	public void setDocSei(String docSei) {
		this.docSei = docSei;
	}

	
	
}