package com.api.main.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "tipo_finalidade")
public class TipoFinalidadeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true, unique = false, length = 40)
	private String descricao;

	@OneToMany(mappedBy = "tipoFinalidade", fetch = FetchType.EAGER)
	private Set<FinalidadeModel> finalidades = new HashSet<>();
	
	@OneToMany(mappedBy = "tipoFinalidade", fetch = FetchType.EAGER)
	private Set<FinalidadeModel> demandas = new HashSet<>();

	public TipoFinalidadeModel() {
		super();
	}

	public TipoFinalidadeModel(String descricao) {
		super();
		this.descricao = descricao;
	}

	public TipoFinalidadeModel(Long id, String descricao) {
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

	public Set<FinalidadeModel> getFinalidades() {
		return finalidades;
	}

	public void setFinalidades(Set<FinalidadeModel> finalidades) {
		this.finalidades = finalidades;
	}

	public Set<FinalidadeModel> getDemandas() {
		return demandas;
	}

	public void setDemandas(Set<FinalidadeModel> demandas) {
		this.demandas = demandas;
	}
	
	

}