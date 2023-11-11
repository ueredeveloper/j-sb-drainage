package com.api.main.services;

import java.util.List;
import java.util.NoSuchElementException;

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

	/*
	 * @Transactional public List<Object> listAll() { List<DocumentoModel>
	 * documentos = docRepo.findAll(); List<Object> customDocumentos = new
	 * ArrayList<>();
	 * 
	 * for (DocumentoModel documento : documentos) { Object docTipo =
	 * documento.getDoc_tipo(); DocumentoTipoModel docTipoModel =
	 * (DocumentoTipoModel) docTipo;
	 * 
	 * DocumentoDTO customDTO = new DocumentoDTO( documento.getDoc_id(),
	 * documento.getDoc_numero(), documento.getDoc_processo(),
	 * documento.getDoc_sei(), new DocumentoTipoDTO(docTipoModel.getDt_id(),
	 * docTipoModel.getDt_descricao()));
	 * 
	 * customDocumentos.add(customDTO); }
	 * 
	 * return customDocumentos; }
	 */
	@Transactional
	public List<DocumentoModel> searchDocuments (String keyword){
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

}