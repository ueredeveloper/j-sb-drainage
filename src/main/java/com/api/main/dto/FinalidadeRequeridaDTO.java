package com.api.main.dto;

public class FinalidadeRequeridaDTO {
	
	
	private Long id;
	private String finalidade;
	private String subfinalidade;
	private Double quantidade;
	private Double consumo;

	public FinalidadeRequeridaDTO() {
		super();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	public String getSubfinalidade() {
		return subfinalidade;
	}

	public void setSubfinalidade(String subfinalidade) {
		this.subfinalidade = subfinalidade;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getConsumo() {
		return consumo;
	}

	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}

}
