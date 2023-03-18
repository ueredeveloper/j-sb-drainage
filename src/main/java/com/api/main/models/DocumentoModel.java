package com.api.main.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "Documento")
public class DocumentoModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private UUID doc_id;

  @Column(nullable = true, unique = false, length = 40)
  private String doc_number;

  @Column(nullable = true, unique = false, length = 40)
  private String doc_process;

  @Column(nullable = true, unique = false, length = 40)
  private String doc_sei;

  @Column(nullable = true, unique = false, length = 40)
  private String doc_type;

  public UUID getDoc_id() {
    return doc_id;
  }

  public void setDoc_id(UUID doc_id) {
    this.doc_id = doc_id;
  }

  public String getDoc_number() {
    return doc_number;
  }

  public void setDoc_number(String doc_number) {
    this.doc_number = doc_number;
  }

  public String getDoc_process() {
    return doc_process;
  }

  public void setDoc_process(String doc_process) {
    this.doc_process = doc_process;
  }

  public String getDoc_sei() {
    return doc_sei;
  }

  public void setDoc_sei(String doc_sei) {
    this.doc_sei = doc_sei;
  }

  public String getDoc_type() {
    return doc_type;
  }

  public void setDoc_type(String doc_type) {
    this.doc_type = doc_type;
  }

}