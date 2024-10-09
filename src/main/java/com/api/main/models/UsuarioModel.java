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
	private Long id;

	@Column(nullable = true, unique = false, length = 500)
	private String nome;

	@Column(nullable = true, unique = false)
	private Long cpfCnpj;

	@ManyToMany
	@JoinTable(name = "usuario_documento", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "documento_id"))
	@JsonIgnore
	private Set<DocumentoModel> documentos = new HashSet<>();

	public UsuarioModel() {
		super();
	}

	public UsuarioModel(String nome) {
		super();
		this.nome = nome;
	}

	public UsuarioModel(String nome, Set<DocumentoModel> documentos) {
		super();
		this.nome = nome;
		this.documentos = documentos;
	}

	public UsuarioModel(Set<DocumentoModel> documentos) {
		super();
		this.documentos = documentos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

<<<<<<< HEAD
	

=======
>>>>>>> 5d8b78b6c2cfa630987f88c785f283c5f478bbcd
	public Long getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(Long cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Set<DocumentoModel> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Set<DocumentoModel> documentos) {
		this.documentos = documentos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
