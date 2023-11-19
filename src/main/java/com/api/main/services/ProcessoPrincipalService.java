package com.api.main.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.main.models.ProcessoModel;
import com.api.main.models.ProcessoPrincipalModel;
import com.api.main.models.ProcessoPrincipalModel;
import com.api.main.repositories.ProcessoPrincipalRepository;

@Service
public class ProcessoPrincipalService {
	

	final ProcessoPrincipalRepository repository;

	public ProcessoPrincipalService(ProcessoPrincipalRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public ProcessoPrincipalModel save(ProcessoPrincipalModel procMod) {
		return repository.save(procMod);
	}
	

	@Transactional
	public List<ProcessoPrincipalModel> list(String keyword) {
		return repository.list(keyword);
	}

	@Transactional
	public List<ProcessoModel> listChildrens(Long proc_processo_principal) {
		return repository.listChildrens(proc_processo_principal);
	}
	
};