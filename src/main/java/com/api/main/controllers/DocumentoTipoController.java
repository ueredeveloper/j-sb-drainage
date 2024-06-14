package com.api.main.controllers;

import java.util.List;

import javax.validation.Valid;

import com.api.main.dto.DocumentoTipoDTO;
import com.api.main.models.DocumentoTipoModel;
import com.api.main.services.DocumentoTipoService;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/document-type")
public class DocumentoTipoController {
	

	
  final DocumentoTipoService docTipoService;

  public DocumentoTipoController(DocumentoTipoService docTipoService) {
    this.docTipoService = docTipoService;
  }

  @PostMapping
  public ResponseEntity<Object> save(@RequestBody @Valid DocumentoTipoDTO dtDTO) {
    DocumentoTipoModel docTipoMod = new DocumentoTipoModel();

    BeanUtils.copyProperties(dtDTO, docTipoMod);
    return ResponseEntity.status(HttpStatus.CREATED).body(docTipoService.save(docTipoMod));
  }

  @GetMapping
  public ResponseEntity<List<DocumentoTipoModel>> listAll() {
    return ResponseEntity.status(HttpStatus.CREATED).body(docTipoService.listAll());
  }

  @DeleteMapping
  public ResponseEntity<String> deleteAll() {
    docTipoService.deleteAll();
    return ResponseEntity.ok("Todos os tipos de documento deletados!!!");
  }
}
