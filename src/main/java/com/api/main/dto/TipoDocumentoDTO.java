package com.api.main.dto;

//import javax.validation.constraints.NotBlank;

public class TipoDocumentoDTO {

  private long tp_id;

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