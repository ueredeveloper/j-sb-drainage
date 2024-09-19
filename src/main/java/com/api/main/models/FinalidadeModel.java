<<<<<<< HEAD
package com.api.main.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import javax.persistence.Table;

=======
package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

>>>>>>> v1.11.1
@Entity
@Table(name = "finalidade")
@Inheritance(strategy = InheritanceType.JOINED)
public class FinalidadeModel {

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
	@Column(nullable = true, unique = false)
	private Double total;

	@ManyToOne
	@JoinColumn(name = "interferencia", referencedColumnName = "id", nullable = false)
	private InterferenciaModel interferencia;

	@ManyToOne
	@JoinColumn(name = "tipoFinalidade", referencedColumnName = "id", nullable = false)
	private TipoFinalidadeModel tipoFinalidade;

	public FinalidadeModel() {
		super();
	}

	public FinalidadeModel(Long id, String finalidade, String subfinalidade, Double quantidade, Double consumo,
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

	public FinalidadeModel(String finalidade, String subfinalidade, Double quantidade, Double consumo,
			InterferenciaModel interferencia, TipoFinalidadeModel tipoFinalidade) {
		super();
		this.finalidade = finalidade;
		this.subfinalidade = subfinalidade;
		this.quantidade = quantidade;
		this.consumo = consumo;
		this.interferencia = interferencia;
		this.tipoFinalidade = tipoFinalidade;
	}
	
	

	public FinalidadeModel(Long id, String finalidade, String subfinalidade, Double quantidade, Double consumo,
			Double total, TipoFinalidadeModel tipoFinalidade) {
		super();
		this.id = id;
		this.finalidade = finalidade;
		this.subfinalidade = subfinalidade;
		this.quantidade = quantidade;
		this.consumo = consumo;
		this.total = total;
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
