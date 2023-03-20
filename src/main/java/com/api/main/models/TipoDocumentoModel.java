package com.api.main.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Column;
import java.io.Serializable;

@Entity
@Table(name = "TipoDocumento")
public class TipoDocumentoModel implements Serializable {
  private static final long serialVersionUID = 1L;
  // default constructor
  public TipoDocumentoModel () {
    
  }
  // descrição constructor
  public TipoDocumentoModel (String tp_descricao) {
    this.tp_descricao = tp_descricao;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long tp_id;

  @Column(nullable = true, unique = false, length = 40)
  private String tp_descricao;

  public long getTp_id() {
    return tp_id;
  }

  public void setTp_id(long tp_id) {
    this.tp_id = tp_id;
  }

  public String getTp_descricao() {
    return tp_descricao;
  }

  public void setTp_descricao(String tp_descricao) {
    this.tp_descricao = tp_descricao;
  }

}