package com.api.main.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuario")
public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = 1L;

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long usId;

	@Column(nullable = true, unique = false, length = 500)
	private String usNome;

	@Column(nullable = true, unique = false)
	private Integer usCpfCnpj;

	@ManyToMany
	@JoinTable(name = "usuario_documento", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "documento_id"))
	@JsonIgnore
	private Set<DocumentoModel> documentos = new HashSet<>();

	public Long getUsId() {
		return usId;
	}

	public void setUsId(Long usId) {
		this.usId = usId;
	}

	public String getUsNome() {
		return usNome;
	}

	public void setUsNome(String usNome) {
		this.usNome = usNome;
	}

	public Integer getUsCpfCnpj() {
		return usCpfCnpj;
	}

	public void setUsCpfCnpj(Integer usCpfCnpj) {
		this.usCpfCnpj = usCpfCnpj;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<DocumentoModel> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Set<DocumentoModel> documentos) {
		this.documentos = documentos;
	}
	

}
