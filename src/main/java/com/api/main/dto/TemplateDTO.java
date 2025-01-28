package com.api.main.dto;

import com.api.main.models.DocumentoTipoModel;
import com.api.main.models.SubtipoOutorgaModel;
import com.api.main.models.TipoOutorgaModel;


public class TemplateDTO {

	private Long id;
	private String nome;
	private String descricao;
	private String diretorio;
	private String arquivo;
	private String conteudo;

	private DocumentoTipoModel tipoDocumento;
	private TipoOutorgaModel tipoOutorga;
	private SubtipoOutorgaModel subtipoOutorga;

	public TemplateDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
