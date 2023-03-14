package com.api.main.controllers;

import javax.validation.Valid;

import com.api.main.dto.DocumentoDTO;
import com.api.main.models.DocumentoModel;
import com.api.main.services.DocumentoService;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins="*", maxAge=3600)
@RequestMapping("/documento")
public class DocumentoController {

  final DocumentoService documentoService;

  public DocumentoController (DocumentoService documentoService){
    this.documentoService = documentoService;
  }

  @PostMapping
  public ResponseEntity<Object> saveDocumento (@RequestBody @Valid DocumentoDTO documentoDTO){
    DocumentoModel documentoModel = new DocumentoModel();

    System.out.println(documentoModel);
    
    BeanUtils.copyProperties(documentoDTO, documentoModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(documentoService.save(documentoModel));
  }
}



  
