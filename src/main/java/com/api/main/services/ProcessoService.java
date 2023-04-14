package com.api.main.services;

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

}
