package com.api.main.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.main.models.DocumentoModel;
import com.api.main.repositories.DocumentoRepository;

@Service
public class DocumentoService {

	final DocumentoRepository docRepo;

	public DocumentoService(DocumentoRepository docRepo) {
		this.docRepo = docRepo;
	}

	@Transactional
	public DocumentoModel save(DocumentoModel documentoModel) {
		return docRepo.save(documentoModel);
	}
	@Transactional
	public List<DocumentoModel> listAll () {
		return docRepo.findAll();
	}
	

}