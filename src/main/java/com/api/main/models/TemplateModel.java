package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "template")
public class TemplateModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = true, unique = false, length = 250)
	private String descricao;
	@Column(nullable = true, unique = false, length = 250)
	private String diretorio;
	@Column(nullable = true, unique = false, length = 250)
	private String arquivo;
	@Column(nullable = true, unique = false)
	@Lob
	private String conteudo;

	@ManyToOne
	@JoinColumn(name = "tipoDocumento")
	private DocumentoTipoModel tipoDocumento;

	@ManyToOne
	@JoinColumn(name = "tipoOutorga")
	private TipoOutorgaModel tipoOutorga;

	@ManyToOne
	@JoinColumn(name = "subtipoOutorga")
	private SubtipoOutorgaModel subtipoOutorga;

	public TemplateModel() {
		super();
	}

	public TemplateModel(Long id, String descricao, String diretorio, String arquivo, String conteudo) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.diretorio = diretorio;
		this.arquivo = arquivo;
		this.conteudo = conteudo;
	}

	public TemplateModel(String descricao, String diretorio, String arquivo, String conteudo,
			DocumentoTipoModel tipoDocumento, TipoOutorgaModel tipoOutorga, SubtipoOutorgaModel subtipoOutorga) {
		super();
		this.descricao = descricao;
		this.diretorio = diretorio;
		this.arquivo = arquivo;
		this.conteudo = conteudo;
		this.tipoDocumento = tipoDocumento;
		this.tipoOutorga = tipoOutorga;
		this.subtipoOutorga = subtipoOutorga;
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

	public String getDiretorio() {
		return diretorio;
	}

	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public DocumentoTipoModel getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(DocumentoTipoModel tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public TipoOutorgaModel getTipoOutorga() {
		return tipoOutorga;
	}

	public void setTipoOutorga(TipoOutorgaModel tipoOutorga) {
		this.tipoOutorga = tipoOutorga;
	}

	public SubtipoOutorgaModel getSubtipoOutorga() {
		return subtipoOutorga;
	}

	public void setSubtipoOutorga(SubtipoOutorgaModel subtipoOutorga) {
		this.subtipoOutorga = subtipoOutorga;
	}

}