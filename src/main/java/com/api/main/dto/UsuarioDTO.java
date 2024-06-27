package com.api.main.dto;

import java.util.HashSet;
import java.util.Set;

import com.api.main.models.DocumentoModel;

public class UsuarioDTO {

	
	private Long usId;

	private String usNome;

	private Integer usCpfCnpj;

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

	public Set<DocumentoModel> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Set<DocumentoModel> documentos) {
		this.documentos = documentos;
	}

}
