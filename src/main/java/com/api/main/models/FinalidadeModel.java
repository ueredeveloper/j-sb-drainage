package com.api.main.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "finalidade")
@Inheritance(strategy = InheritanceType.JOINED)
public class FinalidadeModel {
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long finId;

	public Long getFinId() {
		return finId;
	}

	public void setFinId(Long finId) {
		this.finId = finId;
	}

}
