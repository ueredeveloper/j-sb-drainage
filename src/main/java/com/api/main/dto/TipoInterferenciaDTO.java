package com.api.main.dto;

public class TipoInterferenciaDTO {

	
	Long id;

	String descricao;

	public TipoInterferenciaDTO() {
		super();
	}

	public TipoInterferenciaDTO(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
