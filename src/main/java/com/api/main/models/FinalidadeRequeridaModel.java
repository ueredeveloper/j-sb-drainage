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
	private Long id;
	@Column(nullable = true, unique = false, length = 80)
	private String finalidade;
	@Column(nullable = true, unique = false, length = 80)
	private String subfinalidade;
	@Column(nullable = true, unique = false)
	private Double quantidade;
	@Column(nullable = true, unique = false)
	private Double consumo;

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
