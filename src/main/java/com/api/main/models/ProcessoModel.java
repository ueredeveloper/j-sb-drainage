package com.api.main.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "processo")
public class ProcessoModel {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long proc_id;

  @Column(nullable = true, unique = false, length = 40)
  private String proc_numero;

  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "proc_id", scope = ProcessoModel.class)
  @ManyToOne
  @JoinColumn(name = "proc_processo_principal")
  private ProcessoModel proc_processo_principal;

  @JsonIgnore
  @OneToMany(mappedBy = "proc_processo_principal")
  private List<ProcessoModel> processos = new ArrayList<>();

  public ProcessoModel() {

  }

  public ProcessoModel(String proc_numero) {
    this.proc_numero = proc_numero;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Long getProc_id() {
    return proc_id;
  }

  public void setProc_id(Long proc_id) {
    this.proc_id = proc_id;
  }

  public String getProc_numero() {
    return proc_numero;
  }

  public void setProc_numero(String proc_numero) {
    this.proc_numero = proc_numero;
  }

  public ProcessoModel getProc_processo_principal() {
    return proc_processo_principal;
  }

  public void setProc_processo_principal(ProcessoModel proc_processo_principal) {
    this.proc_processo_principal = proc_processo_principal;
  }

  public List<ProcessoModel> getProcessos() {
    return processos;
  }

  public void setProcessos(List<ProcessoModel> processos) {
    this.processos = processos;
  }

}
