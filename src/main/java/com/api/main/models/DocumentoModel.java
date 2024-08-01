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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "documento")
public class DocumentoModel implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true, unique = false, length = 40)
	private String numero;
	

	@Column(nullable = true, unique = false, length = 40)
	private String numeroSei;

	@ManyToOne
	@JoinColumn(name = "processo")
	private ProcessoModel processo;


	// está retirando o tipo de documento relacionado, trazendo só o id. Ex:
	// documento: {doc_tipo:{dt_id: 1}} => documento: {doc_tipo: 1}
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "dt_id", scope = DocumentoTipoModel.class)
	@ManyToOne
	@JoinColumn(name = "tipo")
	private DocumentoTipoModel tipo;

	@ManyToOne
	@JoinColumn(name = "endereco")
	private EnderecoModel endereco;

	@ManyToMany(mappedBy = "documentos")
	private Set<UsuarioModel> usuarios = new HashSet<>();

	public DocumentoModel() {
		super();
	}

	public DocumentoModel(String numero) {
		super();
		this.numero = numero;
	}

	public DocumentoModel(String numero, EnderecoModel endereco) {
		super();
		this.numero = numero;
		this.endereco = endereco;
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

	public String getNumeroSei() {
		return numeroSei;
	}

	public void setNumeroSei(String numeroSei) {
		this.numeroSei = numeroSei;
	}

	public ProcessoModel getProcesso() {
		return processo;
	}

	public void setProcesso(ProcessoModel processo) {
		this.processo = processo;
	}

	public DocumentoTipoModel getTipo() {
		return tipo;
	}

	public void setTipo(DocumentoTipoModel tipo) {
		this.tipo = tipo;
	}

	public EnderecoModel getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoModel endereco) {
		this.endereco = endereco;
	}

	public Set<UsuarioModel> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<UsuarioModel> usuarios) {
		this.usuarios = usuarios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	

}