package com.api.main.services;

import javax.transaction.Transactional;

import com.api.main.models.DocumentoModel;
import com.api.main.repositories.DocumentoRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentoService {

  final DocumentoRepository documentoRepository;

  public DocumentoService (DocumentoRepository documentoRepository){
    this.documentoRepository = documentoRepository;
  }
  @Transactional
  public DocumentoModel save(DocumentoModel documentoModel){
    return documentoRepository.save(documentoModel);
  }
  
}