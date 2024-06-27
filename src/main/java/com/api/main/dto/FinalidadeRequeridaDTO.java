package com.api.main.dto;

public class FinalidadeRequeridaDTO {
	

	
	
	private Long finId;
	private String finFinalidade;
	private String finSubfinalidade;
	private Double finQuantidade;
	private Double finConsumo;

	public FinalidadeRequeridaDTO() {
		super();

	}

	public FinalidadeRequeridaDTO(Long finId, String finFinalidade, String finSubfinalidade) {
		super();
		this.finId = finId;
		this.finFinalidade = finFinalidade;
		this.finSubfinalidade = finSubfinalidade;
	}

	public Long getFinId() {
		return finId;
	}

	public void setFinId(Long finId) {
		this.finId = finId;
	}

	public String getFinFinalidade() {
		return finFinalidade;
	}

	public void setFinFinalidade(String finFinalidade) {
		this.finFinalidade = finFinalidade;
	}

	public String getFinSubfinalidade() {
		return finSubfinalidade;
	}

	public void setFinSubfinalidade(String finSubfinalidade) {
		this.finSubfinalidade = finSubfinalidade;
	}

	public Double getFinQuantidade() {
		return finQuantidade;
	}

	public void setFinQuantidade(Double finQuantidade) {
		this.finQuantidade = finQuantidade;
	}

	public Double getFinConsumo() {
		return finConsumo;
	}

	public void setFinConsumo(Double finConsumo) {
		this.finConsumo = finConsumo;
	}

}
