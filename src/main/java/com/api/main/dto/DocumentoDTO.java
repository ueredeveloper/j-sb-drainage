package com.api.main.dto;

import lombok.Getter;
import lombok.Setter;

//import javax.validation.constraints.NotBlank;

public class DocumentoDTO {
  // @NotBlank
  private String doc_number;
  // @NotBlank
  private String doc_process;

  // @NotBlank
  private String doc_sei;

  // @NotBlank
  private String doc_type;

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