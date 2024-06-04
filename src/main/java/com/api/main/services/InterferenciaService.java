package com.api.main.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.main.dto.InterferenciaDTO;
import com.api.main.models.InterferenciaModel;
import com.api.main.repositories.InterferenciaRepository;

@Service
public class InterferenciaService {

	final InterferenciaRepository interRepo;

	public InterferenciaService(InterferenciaRepository interRepo) {
		super();
		this.interRepo = interRepo;
	}

	@Transactional
	public List<InterferenciaModel> list(String keyword) {
		return interRepo.list(keyword);
	}

	@Transactional
	public InterferenciaModel save(InterferenciaDTO interDTO, InterferenciaModel interMod) {
		InterferenciaModel interferenciaModel = interRepo.save(interMod);
		return interferenciaModel;
	}

}
