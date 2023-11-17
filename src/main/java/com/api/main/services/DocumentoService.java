package com.api.main.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
	public List<DocumentoModel> listAll() {
		return docRepo.findAll();
	}

	@Transactional
	public List<DocumentoModel> searchDocuments(String keyword) {
		return docRepo.searchDocuments(keyword);
	}

	@Transactional
	public void deleteAll() {
		docRepo.deleteAll();
	}

	@Transactional
	public DocumentoModel deleteById(Long id) {
		DocumentoModel deletedDocument = docRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado documento com o id: " + id));

		docRepo.deleteById(id);
		return deletedDocument;
	}
	public Optional<DocumentoModel> findById(Long id) {
        return docRepo.findById(id);
    }

	public DocumentoModel updateDocumento(Long id, DocumentoModel updateDocumento) {
	    DocumentoModel responseDocumento = docRepo.findById(id)
	            .map((DocumentoModel record) -> {
	                record.setDoc_numero(updateDocumento.getDoc_numero());
	                record.setDocProcesso(updateDocumento.getDocProcesso());
	                record.setDoc_sei(updateDocumento.getDoc_sei());
	                record.setDoc_tipo(updateDocumento.getDoc_tipo());
	                return docRepo.save(record);
	            })
	            .orElse(null);
	    
	    if(responseDocumento == null) {
	    	throw new NoSuchElementException("Não foi encontrado documento com o id: " + id);
	    }
	    
	    return responseDocumento;
	}
	

}