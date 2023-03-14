package com.api.main.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="Documento")
public class DocumentoModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private UUID id;
  @Column(nullable=false, unique=true, length=10)
  private String docSei;

  public String getDocSei() {
    return docSei;
}
public void setDocSei(String docSei) {
  this.docSei = docSei;
}
}