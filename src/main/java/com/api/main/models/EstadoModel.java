package com.api.main.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "estado")
public class EstadoModel {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(nullable = true, unique = false, length = 40)
	String descricao;

	@OneToMany(mappedBy = "estado", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<EnderecoModel> enderecos = new HashSet<>();

	public EstadoModel() {
		super();
	}
	

	public EstadoModel(String descricao) {
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

	public Set<EnderecoModel> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<EnderecoModel> enderecos) {
		this.enderecos = enderecos;
	}
	
	
	

}
