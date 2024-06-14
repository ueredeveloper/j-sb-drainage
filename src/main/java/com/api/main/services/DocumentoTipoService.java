package com.api.main.services;

import java.util.List;

import javax.transaction.Transactional;

import com.api.main.models.DocumentoTipoModel;
import com.api.main.repositories.DocumentoTipoRepository;

import org.springframework.stereotype.Service;

@Service
public class DocumentoTipoService {
	
	

	final DocumentoTipoRepository tdRepo;

	public DocumentoTipoService(DocumentoTipoRepository tdRepo) {
		this.tdRepo = tdRepo;
	}

	@Transactional
	public DocumentoTipoModel save(DocumentoTipoModel tdm) {
		return tdRepo.save(tdm);
	}

	@Transactional
	public List<DocumentoTipoModel> listAll() {
		return tdRepo.findAll();
	}
	@Transactional
	public void deleteAll () {
		tdRepo.deleteAll();
	}
	

}