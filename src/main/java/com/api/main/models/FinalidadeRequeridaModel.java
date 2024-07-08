package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "finalidade_requerida")
public class FinalidadeRequeridaModel extends FinalidadeModel {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long finId;
	@Column(nullable = true, unique = false, length = 80)
	private String finFinalidade;
	@Column(nullable = true, unique = false, length = 80)
	private String finSubfinalidade;
	@Column(nullable = true, unique = false)
	private Double finQuantidade;
	@Column(nullable = true, unique = false)
	private Double finConsumo;

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
