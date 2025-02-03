package com.api.main.dto;

public class DTUsuarioDTO {

	Long us_id;
	String us_nome;
	Long us_cpf_cnpj;
	Long us_doc_id;
	Long doc_end;
	Long doc_sei;
	Long proc_sei;
	Long end_id;
	String end_logradouro;

	public DTUsuarioDTO() {
		super();
	}

	public Long getUs_id() {
		return us_id;
	}

	public void setUs_id(Long us_id) {
		this.us_id = us_id;
	}

	public String getUs_nome() {
		return us_nome;
	}

	public void setUs_nome(String us_nome) {
		this.us_nome = us_nome;
	}

	public Long getUs_cpf_cnpj() {
		return us_cpf_cnpj;
	}

	public void setUs_cpf_cnpj(Long us_cpf_cnpj) {
		this.us_cpf_cnpj = us_cpf_cnpj;
	}

	public Long getUs_doc_id() {
		return us_doc_id;
	}

	public void setUs_doc_id(Long us_doc_id) {
		this.us_doc_id = us_doc_id;
	}

	public Long getDoc_end() {
		return doc_end;
	}

	public void setDoc_end(Long doc_end) {
		this.doc_end = doc_end;
	}

	public Long getDoc_sei() {
		return doc_sei;
	}

	public void setDoc_sei(Long doc_sei) {
		this.doc_sei = doc_sei;
	}

	public Long getProc_sei() {
		return proc_sei;
	}

	public void setProc_sei(Long proc_sei) {
		this.proc_sei = proc_sei;
	}

	public Long getEnd_id() {
		return end_id;
	}

	public void setEnd_id(Long end_id) {
		this.end_id = end_id;
	}

	public String getEnd_logradouro() {
		return end_logradouro;
	}

	public void setEnd_logradouro(String end_logradouro) {
		this.end_logradouro = end_logradouro;
	}

	

}
