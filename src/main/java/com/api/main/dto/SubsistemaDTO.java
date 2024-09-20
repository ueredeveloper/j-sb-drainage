package com.api.main.dto;

public class SubsistemaDTO {
	// v1.12.2
	
	private Long id;

	private String descricao;
	
	

	public SubsistemaDTO() {
		super();
	}
	
	public SubsistemaDTO(Long id, String descricao) {
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