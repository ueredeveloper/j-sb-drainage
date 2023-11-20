package com.api.main.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
	private ProcessoSecudarioModel docProcesso;

	@Column(nullable = true, unique = false, length = 40)
	private String docSEI;

	// está retirando o tipo de documento relacionado, trazendo só o id. Ex:
	// documento: {doc_tipo:{dt_id: 1}} => documento: {doc_tipo: 1}
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "dt_id", scope = DocumentoTipoModel.class)
	@ManyToOne
	@JoinColumn(name = "docTipo")
	private DocumentoTipoModel docTipo;

	public DocumentoModel() {
		super();
	}
	
	public DocumentoModel(String docNumero) {
		super();
		this.docNumero = docNumero;
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

	public ProcessoSecudarioModel getDocProcesso() {
		return docProcesso;
	}

	public void setDocProcesso(ProcessoSecudarioModel docProcesso) {
		this.docProcesso = docProcesso;
	}

	public String getDocSEI() {
		return docSEI;
	}

	public void setDocSEI(String docSEI) {
		this.docSEI = docSEI;
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

}