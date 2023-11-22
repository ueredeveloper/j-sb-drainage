package com.api.main.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.api.main.dto.AnexoDTO;
import com.api.main.dto.DocumentoDTO;
import com.api.main.models.AnexoModel;
import com.api.main.models.DocumentoModel;
import com.api.main.models.ProcessoModel;
import com.api.main.repositories.AnexoRepository;
import com.api.main.repositories.DocumentoRepository;
import com.api.main.repositories.ProcessoRepository;

@Service
public class DocumentoService {
	

	final DocumentoRepository docRepo;
	final AnexoRepository anRepo;
	final ProcessoRepository proRepo;

	public DocumentoService(DocumentoRepository docRepo,AnexoRepository anRepo, ProcessoRepository proRepo ) {
		this.docRepo = docRepo;
		this.anRepo = anRepo;
		this.proRepo = proRepo;
	}

	/*@Transactional
	public DocumentoModel save(DocumentoModel documentoModel) {
		return docRepo.save(documentoModel);
	}*/

	@Transactional
	public List<DocumentoModel> list(String keyword) {
		return docRepo.list(keyword);
	}

	@Transactional
	public void delete() {
		docRepo.deleteAll();
	}

	@Transactional
	public DocumentoModel deleteById(Long id) {
		DocumentoModel deletedDocument = docRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado documento com o id: " + id));
		docRepo.deleteById(id);
		return deletedDocument;
	}

	@Transactional
	public Optional<DocumentoModel> findById(Long id) {
		return docRepo.findById(id);
	}

	@Transactional
	public DocumentoModel update(Long id, DocumentoModel updateDocumento) {
		DocumentoModel responseDocumento = docRepo.findById(id).map((DocumentoModel record) -> {
			record.setDocNumero(updateDocumento.getDocNumero());
			record.setDocProcesso(updateDocumento.getDocProcesso());
			record.setDocSEI(updateDocumento.getDocSEI());
			record.setDocTipo(updateDocumento.getDocTipo());
			return docRepo.save(record);
		}).orElse(null);

		if (responseDocumento == null) {
			throw new NoSuchElementException("Não foi encontrado documento com o id: " + id);
		}

		return responseDocumento;
	}

	@Transactional
	public DocumentoModel save(DocumentoModel docMod, DocumentoDTO docDTO) {
	    DocumentoModel savedDocumento = null;

	    // Check if docProcesso is present in the DTO
	    if (docDTO.getDocProcesso() != null && docDTO.getDocProcesso().getProcNumero() != null) {
	        ProcessoModel procAnexo = new ProcessoModel();
	        procAnexo.setProcNumero(docDTO.getDocProcesso().getProcNumero());

	        // Save the ProcessoModel
	        procAnexo = proRepo.save(procAnexo);

	        // Set the saved ProcessoModel in DocumentoModel
	        docMod.setDocProcesso(procAnexo);
	        savedDocumento = docRepo.save(docMod);

	        // If there's an Anexo, handle saving the AnexoModel
	        if (docDTO.getDocAnexo() != null) {
	            AnexoModel anModel = new AnexoModel();
	            AnexoDTO anDTO = docDTO.getDocAnexo();
	            BeanUtils.copyProperties(anDTO, anModel);

	            // Set the previously saved ProcessoModel in AnexoModel
	            anModel.setAnPrincipal(procAnexo);
	            anRepo.save(anModel);
	        }
	    } else {
	        // Save only the DocumentoModel without a related ProcessoModel
	        savedDocumento = docRepo.save(docMod);
	    }

	    return savedDocumento;
	}




}