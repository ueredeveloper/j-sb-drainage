package com.api.main.models;

import java.util.ArrayList;
import java.util.List;

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
	Long procId;

	@Column(nullable = true, unique = false, length = 40)
	String procNumero;

	@ManyToOne
	@JoinColumn(name = "anexo_id", referencedColumnName = "id")
	@JsonBackReference
	private AnexoModel anexo;

	@JsonIgnore
	@OneToMany(mappedBy = "docProcesso")
	private List<DocumentoModel> anDocumentos = new ArrayList<>();

	public ProcessoModel() {
		super();
	}

	public Long getProcId() {
		return procId;
	}

	public void setProcId(Long procId) {
		this.procId = procId;
	}

	public String getProcNumero() {
		return procNumero;
	}

	public void setProcNumero(String procNumero) {
		this.procNumero = procNumero;
	}

	public AnexoModel getAnexo() {
		return anexo;
	}

	public void setAnexo(AnexoModel anexo) {
		this.anexo = anexo;
	}

	public List<DocumentoModel> getAnDocumentos() {
		return anDocumentos;
	}

	public void setAnDocumentos(List<DocumentoModel> anDocumentos) {
		this.anDocumentos = anDocumentos;
	}

}
