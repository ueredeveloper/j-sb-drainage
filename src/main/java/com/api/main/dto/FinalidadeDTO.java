package com.api.main.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.api.main.models.InterferenciaModel;
import com.api.main.models.TipoFinalidadeModel;

public class FinalidadeDTO {

	private Long id;
	private String finalidade;
	private String subfinalidade;
	private Double quantidade;
	private Double consumo;
	private Double total;
	
	private InterferenciaModel interferencia;

	private TipoFinalidadeModel tipoFinalidade;


	public FinalidadeDTO() {
		super();
	}

	public FinalidadeDTO(Long id, String finalidade, String subfinalidade, Double quantidade, Double consumo,
			InterferenciaModel interferencia, TipoFinalidadeModel tipoFinalidade) {
		super();
		this.id = id;
		this.finalidade = finalidade;
		this.subfinalidade = subfinalidade;
		this.quantidade = quantidade;
		this.consumo = consumo;
		this.interferencia = interferencia;
		this.tipoFinalidade = tipoFinalidade;
	}

	public FinalidadeDTO(String finalidade, String subfinalidade, Double quantidade, Double consumo,
			InterferenciaModel interferencia, TipoFinalidadeModel tipoFinalidade) {
		super();
		this.finalidade = finalidade;
		this.subfinalidade = subfinalidade;
		this.quantidade = quantidade;
		this.consumo = consumo;
		this.interferencia = interferencia;
		this.tipoFinalidade = tipoFinalidade;
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

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public InterferenciaModel getInterferencia() {
		return interferencia;
	}

	public void setInterferencia(InterferenciaModel interferencia) {
		this.interferencia = interferencia;
	}

	public TipoFinalidadeModel getTipoFinalidade() {
		return tipoFinalidade;
	}

	public void setTipoFinalidade(TipoFinalidadeModel tipoFinalidade) {
		this.tipoFinalidade = tipoFinalidade;
	}

}
