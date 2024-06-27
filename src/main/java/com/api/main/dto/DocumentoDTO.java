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

	private Long docId;
	// @NotBlank
	private String docNumero;
	// @NotBlank
	private ProcessoModel docProcesso;

	// @NotBlank
	private String docSei;

	// @JsonInclude(Include.NON_NULL)
	private DocumentoTipoModel docTipo;

	private EnderecoModel docEndereco;

	private Set<UsuarioModel> usuarios = new HashSet<>();

	public DocumentoDTO() {
		super();
	}

	public DocumentoDTO(String docNumero) {
		super();
		this.docNumero = docNumero;
	}

	public DocumentoDTO(Long docId, String docNumero, ProcessoModel docProcesso, String docSei,
			DocumentoTipoModel docTipo, EnderecoModel docEndereco, Set<UsuarioModel> usuarios) {
		super();
		this.docId = docId;
		this.docNumero = docNumero;
		this.docProcesso = docProcesso;
		this.docSei = docSei;
		this.docTipo = docTipo;
		this.docEndereco = docEndereco;
		this.usuarios = usuarios;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public String getDocNumero() {
		return docNumero;
	}

	public void setDocNumero(String docNumero) {
		this.docNumero = docNumero;
	}

	public ProcessoModel getDocProcesso() {
		return docProcesso;
	}

	public void setDocProcesso(ProcessoModel docProcesso) {
		this.docProcesso = docProcesso;
	}

	public String getDocSei() {
		return docSei;
	}

	public void setDocSei(String docSei) {
		this.docSei = docSei;
	}

	public DocumentoTipoModel getDocTipo() {
		return docTipo;
	}

	public void setDocTipo(DocumentoTipoModel docTipo) {
		this.docTipo = docTipo;
	}

	public EnderecoModel getDocEndereco() {
		return docEndereco;
	}

	public void setDocEndereco(EnderecoModel docEndereco) {
		this.docEndereco = docEndereco;
	}

	public Set<UsuarioModel> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<UsuarioModel> usuarios) {
		this.usuarios = usuarios;
	}

}