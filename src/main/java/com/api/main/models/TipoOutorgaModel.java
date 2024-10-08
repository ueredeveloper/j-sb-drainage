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
@Table(name = "tipo_outorga")
public class TipoOutorgaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true, unique = false, length = 40)
	private String descricao;

	@OneToMany(mappedBy = "tipoOutorga", fetch = FetchType.EAGER)
	private Set<InterferenciaModel> interferencias = new HashSet<>();
	
	@OneToMany(mappedBy = "tipoOutorga", fetch = FetchType.EAGER)
	private Set<TemplateModel> templates = new HashSet<>();
	


	public TipoOutorgaModel() {
		super();
	}

	public TipoOutorgaModel(String descricao) {
		super();
		this.descricao = descricao;
	}

	public TipoOutorgaModel(Long id, String descricao) {
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

	public Set<InterferenciaModel> getInterferencias() {
		return interferencias;
	}

	public void setInterferencias(Set<InterferenciaModel> interferencias) {
		this.interferencias = interferencias;
	}

}
