package com.api.main.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.FinalidadeAutorizadaModel;
import com.api.main.repositories.FinalidadeAutorizadaRepository;

@Service
public class FinalidadeAutorizadaService {
	

	@Autowired
	FinalidadeAutorizadaRepository finRepo;

	public FinalidadeAutorizadaService(FinalidadeAutorizadaRepository finRepo) {
		super();
		this.finRepo = finRepo;
	}

	@Transactional
	public List<FinalidadeAutorizadaModel> list(String keyword) {
		return finRepo.list(keyword);
	}

	@Transactional
	public FinalidadeAutorizadaModel save(FinalidadeAutorizadaModel finMod) {
		return finRepo.save(finMod);

	}

}
