package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "subterranea")
public class SubterraneaModel extends InterferenciaModel {

	private static final long serialVersionUID = 1L;

	@Column()
	private Boolean caesb; // tem caesb () sim () não

	@Column(columnDefinition = "varchar(20)")
	private String nivelEstatico; // em metros

	@Column(columnDefinition = "varchar(20)")
	private String nivelDinamico; // em metros

	@Column(columnDefinition = "varchar(20)")
	private String profundidade; // em metros

	@Column()
	private Integer vazaoOutorgavel; // 

	@Column()
	private Integer vazaoSistema; // 

	@Column()
	private Integer vazaoTeste; // 
	
	@Column(columnDefinition = "varchar(20)")
	private String sistema;
	
	@Column(columnDefinition = "varchar(20)")
	private String subsistema;
	
	@Column(name = "cod_plan")
	private String codPlan;

	@ManyToOne
	@JoinColumn(name = "tipoPoco", nullable = true)
	private TipoPocoModel tipoPoco;
	
	

	// Adicionar tipo poco, subsistema código do subsistema

	public SubterraneaModel() {
		super();
	}

	public Boolean getCaesb() {
		return caesb;
	}

	public void setCaesb(Boolean caesb) {
		this.caesb = caesb;
	}

	public String getNivelEstatico() {
		return nivelEstatico;
	}

	public void setNivelEstatico(String nivelEstatico) {
		this.nivelEstatico = nivelEstatico;
	}

	public String getNivelDinamico() {
		return nivelDinamico;
	}

	public void setNivelDinamico(String nivelDinamico) {
		this.nivelDinamico = nivelDinamico;
	}

	public String getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(String profundidade) {
		this.profundidade = profundidade;
	}

	public Integer getVazaoOutorgavel() {
		return vazaoOutorgavel;
	}

	public void setVazaoOutorgavel(Integer vazaoOutorgavel) {
		this.vazaoOutorgavel = vazaoOutorgavel;
	}

	public Integer getVazaoSistema() {
		return vazaoSistema;
	}

	public void setVazaoSistema(Integer vazaoSistema) {
		this.vazaoSistema = vazaoSistema;
	}

	public Integer getVazaoTeste() {
		return vazaoTeste;
	}

	public void setVazaoTeste(Integer vazaoTeste) {
		this.vazaoTeste = vazaoTeste;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TipoPocoModel getTipoPoco() {
		return tipoPoco;
	}

	public void setTipoPoco(TipoPocoModel tipoPoco) {
		this.tipoPoco = tipoPoco;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getSubsistema() {
		return subsistema;
	}

	public void setSubsistema(String subsistema) {
		this.subsistema = subsistema;
	}

	public String getCodPlan() {
		return codPlan;
	}

	public void setCodPlan(String codPlan) {
		this.codPlan = codPlan;
	}
	
	
	
}
