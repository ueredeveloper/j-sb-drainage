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
	Long procId;

	@Column(nullable = true, unique = false, length = 40)
	String procNumero;

	@ManyToOne
	@JoinColumn(name = "anexo_id", referencedColumnName = "id")
	// Ao pequisar processo não vir anexo com os processos relacionados dentro,
	// gerando loop de json.
	@JsonBackReference
	private AnexoModel anexo;

	@JsonIgnore
	@OneToMany(mappedBy = "docProcesso")
	private Set<DocumentoModel> anDocumentos = new HashSet<>();

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

	public Set<DocumentoModel> getAnDocumentos() {
		return anDocumentos;
	}

	public void setAnDocumentos(Set<DocumentoModel> anDocumentos) {
		this.anDocumentos = anDocumentos;
	}

}
