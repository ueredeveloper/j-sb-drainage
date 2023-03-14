package com.api.main.dto;

import javax.validation.constraints.NotBlank;

public class DocumentoDTO {
  
  @NotBlank
  private String docSei;

  public String getDocSei() {
    return docSei;
  }
  
  public void setDocSei(String docSei) {
    this.docSei = docSei;
  }
  
}