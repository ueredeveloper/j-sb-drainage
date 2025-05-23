package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "local_captacao")
public class LocalCaptacaoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true, unique = false, length = 40)
	private String descricao;

	public LocalCaptacaoModel() {
		super();
	}

	public LocalCaptacaoModel(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public LocalCaptacaoModel(String descricao) {
		super();
		this.descricao = descricao;
	}

}