package com.api.main.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.main.models.DocumentoModel;
import com.api.main.models.AnexoModel;
import com.api.main.repositories.DocumentoRepository;

@Service
public class DocumentoService {
	

	final DocumentoRepository repository;

	public DocumentoService(DocumentoRepository repository) {
		this.repository = repository;
	}

	
	@Transactional
	public DocumentoModel save(DocumentoModel documentoModel) {
		return repository.save(documentoModel);
	}

	/*
	@Transactional
	public List<DocumentoModel> list() {
		return repository.findAll();
	}

	@Transactional
	public List<DocumentoModel> search(String keyword) {
		return repository.search(keyword);
	}*/
	@Transactional
	public List<DocumentoModel> list(String keyword) {
		return repository.list(keyword);
	}

	@Transactional
	public void delete() {
		repository.deleteAll();
	}

	@Transactional
	public DocumentoModel deleteById(Long id) {
		DocumentoModel deletedDocument = repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado documento com o id: " + id));

		repository.deleteById(id);
		return deletedDocument;
	}

	public Optional<DocumentoModel> findById(Long id) {
		return repository.findById(id);
	}

	public DocumentoModel update(Long id, DocumentoModel updateDocumento) {
		DocumentoModel responseDocumento = repository.findById(id).map((DocumentoModel record) -> {
			record.setDocNumero(updateDocumento.getDocNumero());
			record.setDocProcesso(updateDocumento.getDocProcesso());
			record.setDocSEI(updateDocumento.getDocSEI());
			record.setDocTipo(updateDocumento.getDocTipo());
			return repository.save(record);
		}).orElse(null);

		if (responseDocumento == null) {
			throw new NoSuchElementException("Não foi encontrado documento com o id: " + id);
		}

		return responseDocumento;
	}

}