package com.api.main.models;

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
@Table(name = "processo")
public class ProcessoModel {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long procId;
	@Column(nullable = true, unique = false, length = 40)
	String procNumero;

	@JsonIgnore
	@OneToMany(mappedBy = "anPrincipal")
	private List<AnexoModel> procAnexos = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "docProcesso")
	private List<DocumentoModel> anDocumentos = new ArrayList<>();
	
	public ProcessoModel() {
		super();
	}

	public ProcessoModel(Long procId, String procNumero, List<AnexoModel> procAnexos) {
		super();
		this.procId = procId;
		this.procNumero = procNumero;
		this.procAnexos = procAnexos;
	}

	public ProcessoModel(String procNumero) {
		super();
		this.procNumero = procNumero;
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

	public List<AnexoModel> getProcAnexos() {
		return procAnexos;
	}

	public void setProcAnexos(List<AnexoModel> procAnexos) {
		this.procAnexos = procAnexos;
	}

	public List<DocumentoModel> getAnDocumentos() {
		return anDocumentos;
	}

	public void setAnDocumentos(List<DocumentoModel> anDocumentos) {
		this.anDocumentos = anDocumentos;
	}

}
