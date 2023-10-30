package com.api.main.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.api.main.dto.CustomDocTipoSerializer;

//import javax.validation.constraints.NotBlank;
@JsonSerialize(using = CustomDocTipoSerializer.class)
public class DocumentoDTO {

  private Long doc_id;
  // @NotBlank
  private String doc_numero;
  // @NotBlank
  private String doc_processo;

  // @NotBlank
  private String doc_sei;

  //@JsonInclude(Include.NON_NULL)
  private DocumentoTipoDTO doc_tipo;

  // constructors
  public DocumentoDTO() {
    super();
  }

  public DocumentoDTO(Long doc_id, String doc_numero, String doc_processo, String doc_sei,
      DocumentoTipoDTO doc_tipo) {
    super();
    this.doc_id = doc_id;
    this.doc_numero = doc_numero;
    this.doc_processo = doc_processo;
    this.doc_sei = doc_sei;
    this.doc_tipo = doc_tipo;
  }

  public Long getDoc_id() {
    return doc_id;
  }

  public void setDoc_id(Long doc_id) {
    this.doc_id = doc_id;
  }

  public String getDoc_numero() {
    return doc_numero;
  }

  public void setDoc_numero(String doc_numero) {
    this.doc_numero = doc_numero;
  }

  public String getDoc_processo() {
    return doc_processo;
  }

  public void setDoc_processo(String doc_processo) {
    this.doc_processo = doc_processo;
  }

  public String getDoc_sei() {
    return doc_sei;
  }

  public void setDoc_sei(String doc_sei) {
    this.doc_sei = doc_sei;
  }

  public DocumentoTipoDTO getDoc_tipo() {
    return doc_tipo;
  }

  public void setDoc_tipo(DocumentoTipoDTO doc_tipo) {
    this.doc_tipo = doc_tipo;
  }

}