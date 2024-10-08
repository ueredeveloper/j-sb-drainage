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
@Table(name = "tipo_poco")
public class TipoPocoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true, unique = false, length = 40)
	private String descricao;

	@OneToMany(mappedBy = "tipoPoco", fetch = FetchType.EAGER)
	private Set<SubterraneaModel> interferencias = new HashSet<>();

	public TipoPocoModel() {
		super();
	}
	
	public TipoPocoModel(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public TipoPocoModel(String descricao) {
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

}
