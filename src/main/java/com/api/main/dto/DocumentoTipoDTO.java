package com.api.main.dto;

import java.util.List;

import com.api.main.models.DocumentoModel;

//import javax.validation.constraints.NotBlank;

public class DocumentoTipoDTO {

  private Long dt_id;

  private String dt_descricao;

  private List<DocumentoModel> documentos;

  // constructors
  /*
  public DocumentoTipoDTO() { 
  }*/
  
  public DocumentoTipoDTO(Long dt_id, String dt_descricao) {
    super();
    this.dt_id=dt_id;
    this.dt_descricao=dt_descricao;
  }
  
  // getters and setters
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