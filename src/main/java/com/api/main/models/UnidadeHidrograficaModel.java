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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "unidade_hidrografica")
public class UnidadeHidrograficaModel {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true, unique = false, length = 40)
	private String descricao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "unidadeHidrografica", fetch = FetchType.EAGER)
	private Set<InterferenciaModel> interferencias = new HashSet<>();
	
	

	

}