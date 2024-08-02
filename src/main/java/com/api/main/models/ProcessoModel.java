package com.api.main.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "processo")
public class ProcessoModel {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(nullable = true, unique = false, length = 40)
	String numero;

	@ManyToOne
	@JoinColumn(name = "anexo_id", referencedColumnName = "id")
	// Ao pequisar processo não vir anexo com os processos relacionados dentro,
	// gerando loop de json.
	@JsonBackReference
	private AnexoModel anexo;

	@JsonIgnore
	@OneToMany(mappedBy = "processo")
	private Set<DocumentoModel> documentos = new HashSet<>();

	public ProcessoModel() {
		super();
	}

	public ProcessoModel(Long id, String numero, AnexoModel anexo, Set<DocumentoModel> documentos) {
		super();
		this.id = id;
		this.numero = numero;
		this.anexo = anexo;
		this.documentos = documentos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public AnexoModel getAnexo() {
		return anexo;
	}

	public void setAnexo(AnexoModel anexo) {
		this.anexo = anexo;
	}

	public Set<DocumentoModel> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Set<DocumentoModel> documentos) {
		this.documentos = documentos;
	}
	
	

}
