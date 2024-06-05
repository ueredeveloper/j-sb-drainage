package com.api.main.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.FinalidadeModel;
import com.api.main.repositories.FinalidadeRepository;

@Service
public class FinalidadeService {

	@Autowired
	FinalidadeRepository finRepo;

	public FinalidadeService(FinalidadeRepository finRepo) {
		super();
		this.finRepo = finRepo;
	}

	@Transactional
	public List<FinalidadeModel> list(String keyword) {
		return finRepo.list(keyword);
	}

	@Transactional
	public FinalidadeModel save(FinalidadeModel finMod) {
		return finRepo.save(finMod);

	}

	@Transactional
	public FinalidadeModel deleteById(Long id) {
		FinalidadeModel deletedDocument = finRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado documento com o id: " + id));
		finRepo.deleteById(id);
		return deletedDocument;
	}

	@Transactional
	public void delete() {
		finRepo.deleteAll();
	}

}
