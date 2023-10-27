package com.api.main.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "documento_tipo")
public class DocumentoTipoModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long dt_id;

  @Column(nullable = true, unique = false, length = 40)
  private String dt_descricao;

  @JsonIgnore
  @OneToMany(mappedBy = "doc_tipo")
  private List<DocumentoModel> documentos = new ArrayList<DocumentoModel>();

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  // default constructor
  public DocumentoTipoModel() {
  }

  // descrição constructor
  public DocumentoTipoModel(String dt_descricao) {
    this.dt_descricao = dt_descricao;
  }

  public Long getDt_id() {
    return dt_id;
  }

  public void setDt_id(Long dt_id) {
    this.dt_id = dt_id;
  }

  public String getDt_descricao() {
    return dt_descricao;
  }

  public void setDt_descricao(String dt_descricao) {
    this.dt_descricao = dt_descricao;
  }

  public List<DocumentoModel> getDocumentos() {
    return documentos;
  }

  public void setDocumentos(List<DocumentoModel> documentos) {
    this.documentos = documentos;
  }

}