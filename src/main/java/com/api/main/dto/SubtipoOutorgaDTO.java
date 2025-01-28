package com.api.main.dto;


public class SubtipoOutorgaDTO {
	
	private Long id;
	private String descricao;
	
	public SubtipoOutorgaDTO() {
		super();
	}
	
	public SubtipoOutorgaDTO(Long id, String descricao) {
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

	@Override
	public String toString() {
		return "SubtipoOutorgaDTO [id=" + id + ", descricao=" + descricao + "]";
	}
	
	

}
