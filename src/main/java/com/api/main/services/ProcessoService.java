package com.api.main.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.main.models.ProcessoModel;
import com.api.main.repositories.ProcessoRepository;

@Service
public class ProcessoService {
	
	final ProcessoRepository procRepo;
	

	public ProcessoService(ProcessoRepository procRepo) {
		this.procRepo = procRepo;
	}

	@Transactional
	public ProcessoModel save(ProcessoModel procMod) {
		return procRepo.save(procMod);
	}

	@Transactional
	public List<ProcessoModel> listAll () {
		return  procRepo.findAll();
	}
	
	@Transactional
	public List<ProcessoModel> findProcessosSecundarios (Long proc_principal_fk){
		return procRepo.findProcessosSecundarios(proc_principal_fk);
	}
	
	@Transactional
	public void deleteProcessos (){
		procRepo.deleteAll();
	}
	


}
