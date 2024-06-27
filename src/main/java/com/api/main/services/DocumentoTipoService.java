package com.api.main.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.DocumentoTipoModel;
import com.api.main.repositories.DocumentoTipoRepository;

@Service
public class DocumentoTipoService {

	@Autowired
	private DocumentoTipoRepository documentoTipoService;

	@Transactional
	public DocumentoTipoModel save(DocumentoTipoModel tdm) {
		return documentoTipoService.save(tdm);
	}

	@Transactional
	public List<DocumentoTipoModel> listAll() {
		return documentoTipoService.findAll();
	}

	@Transactional
	public void deleteAll() {
		documentoTipoService.deleteAll();
	}

}