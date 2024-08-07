package com.api.main.dto;

import java.util.HashSet;
import java.util.Set;

import com.api.main.models.DocumentoTipoModel;
import com.api.main.models.EnderecoModel;
import com.api.main.models.ProcessoModel;
import com.api.main.models.UsuarioModel;

//import javax.validation.constraints.NotBlank;
//@JsonSerialize(using = CustomDocTipoSerializer.class)
public class DocumentoDTO {

	
	private Long id;
	// @NotBlank
	private String numero;
	// @NotBlank
	private ProcessoModel processo;

	// @NotBlank
	private String numeroSei;

	// @JsonInclude(Include.NON_NULL)
	private DocumentoTipoModel tipo;

	private EnderecoModel endereco;

	private Set<UsuarioModel> usuarios = new HashSet<>();

	public DocumentoDTO() {
		super();
	}
	
	public DocumentoDTO(String numero, String numeroSei, EnderecoModel endereco) {
		super();
		this.numero = numero;
		this.numeroSei = numeroSei;
		this.endereco = endereco;
	}

	public DocumentoDTO(String numero, ProcessoModel processo, String numeroSei, DocumentoTipoModel tipo,
			EnderecoModel endereco, Set<UsuarioModel> usuarios) {
		super();
		this.numero = numero;
		this.processo = processo;
		this.numeroSei = numeroSei;
		this.tipo = tipo;
		this.endereco = endereco;
		this.usuarios = usuarios;
	}

	public DocumentoDTO(Long id, String numero, ProcessoModel processo, String numeroSei, DocumentoTipoModel tipo,
			EnderecoModel endereco, Set<UsuarioModel> usuarios) {
		super();
		this.id = id;
		this.numero = numero;
		this.processo = processo;
		this.numeroSei = numeroSei;
		this.tipo = tipo;
		this.endereco = endereco;
		this.usuarios = usuarios;
	}
	
	public DocumentoDTO(String numero, String numeroSei, DocumentoTipoModel tipo, EnderecoModel endereco,
			Set<UsuarioModel> usuarios) {
		super();
		this.numero = numero;
		this.numeroSei = numeroSei;
		this.tipo = tipo;
		this.endereco = endereco;
		this.usuarios = usuarios;
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

	public ProcessoModel getProcesso() {
		return processo;
	}

	public void setProcesso(ProcessoModel processo) {
		this.processo = processo;
	}

	public String getNumeroSei() {
		return numeroSei;
	}

	public void setNumeroSei(String numeroSei) {
		this.numeroSei = numeroSei;
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

}