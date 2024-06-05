package com.api.main.models;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "finalidade")
@Inheritance(strategy = InheritanceType.JOINED)
public class FinalidadeModel {
	
	String finalidade;
	String subfinalidade;
	

}
