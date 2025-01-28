package com.api.main.dto;


public class TipoAtoDTO {

	private Long id;
	private String descricao;

	public TipoAtoDTO() {
		super();
	}

	public TipoAtoDTO(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public TipoAtoDTO(String descricao) {
		super();
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
