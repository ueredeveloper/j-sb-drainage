package com.api.main.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcOperations;
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
	
	


}
