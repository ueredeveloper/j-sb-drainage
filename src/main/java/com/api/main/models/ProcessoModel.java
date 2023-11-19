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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "processo")
public class ProcessoModel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long procId;

	@Column(nullable = true, unique = false, length = 40)
	private String procNumero;

	@ManyToOne
	@JoinColumn(name = "procPrincipal")
	private ProcessoPrincipalModel procPrincipal;

	@JsonIgnore
	@OneToMany(mappedBy = "docProcesso")
	private List<DocumentoModel> procDocumentos = new ArrayList<>();

	public ProcessoModel() {
		super();
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

	public ProcessoPrincipalModel getProcPrincipal() {
		return procPrincipal;
	}

	public void setProcPrincipal(ProcessoPrincipalModel procPrincipal) {
		this.procPrincipal = procPrincipal;
	}

	public List<DocumentoModel> getProcDocumentos() {
		return procDocumentos;
	}

	public void setProcDocumentos(List<DocumentoModel> procDocumentos) {
		this.procDocumentos = procDocumentos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
