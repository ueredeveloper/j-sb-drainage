package com.api.main.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.FinalidadeRequeridaModel;
import com.api.main.repositories.FinalidadeRequeridaRepository;

@Service
public class FinalidadeRequeridaService {
	
	
	
	
	
	@Autowired
	FinalidadeRequeridaRepository finRepo;

	public FinalidadeRequeridaService(FinalidadeRequeridaRepository finRepo) {
		super();
		this.finRepo = finRepo;
	}
	
	@Transactional
	public List<FinalidadeRequeridaModel> list(String keyword) {
		return finRepo.list(keyword);
	}

	@Transactional
	public FinalidadeRequeridaModel save(FinalidadeRequeridaModel finMod) {
		return finRepo.save(finMod);
	
	}
	

}
