package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "forma_captacao")
public class FormaCaptacaoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true, unique = false, length = 40)
	private String descricao;

	public FormaCaptacaoModel(){
		super();
	}

	public FormaCaptacaoModel(String descricao) {
		super();
		this.descricao = descricao;
	}
	
	public FormaCaptacaoModel(Long id, String descricao){
		super();
		this.id = id;
		this.descricao = descricao;
	}
	

}
