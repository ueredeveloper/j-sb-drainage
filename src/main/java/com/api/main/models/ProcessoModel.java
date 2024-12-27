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

@Entity
@Table(name = "processo")
public class ProcessoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(nullable = true, unique = true, length = 40)
	String numero;

	@ManyToOne
	@JoinColumn(name = "anexo", referencedColumnName = "id")
	private AnexoModel anexo;
	
	@ManyToOne
	@JoinColumn(name = "usuario", referencedColumnName = "id")
	private UsuarioModel usuario;

	@OneToMany(mappedBy = "processo")
	private Set<DocumentoModel> documentos = new HashSet<>();

	public ProcessoModel() {
		super();
	}
	
	public ProcessoModel(Long id) {
		super();
		this.id = id;
	}
	
	
	public ProcessoModel(Long id, String numero) {
		super();
		this.id = id;
		this.numero = numero;
	}

	public ProcessoModel(Long id, String numero, AnexoModel anexo) {
		super();
		this.id = id;
		this.numero = numero;
		this.anexo = anexo;
	}

	public ProcessoModel(Long id, String numero, AnexoModel anexo, Set<DocumentoModel> documentos) {
		super();
		this.id = id;
		this.numero = numero;
		this.anexo = anexo;
		this.documentos = documentos;
	}

	public ProcessoModel(String numero) {
		super();
		this.numero = numero;
	}

	public ProcessoModel(String numero, AnexoModel anexo) {
		super();
		this.numero = numero;
		this.anexo = anexo;
	}
	
	public ProcessoModel(Long id, String numero, AnexoModel anexo, UsuarioModel usuario) {
		super();
		this.id = id;
		this.numero = numero;
		this.anexo = anexo;
		this.usuario = usuario;
	}
	
	

	public ProcessoModel(Long id, String numero, UsuarioModel usuario) {
		super();
		this.id = id;
		this.numero = numero;
		this.usuario = usuario;
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

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public Set<DocumentoModel> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Set<DocumentoModel> documentos) {
		this.documentos = documentos;
	}

	

}
