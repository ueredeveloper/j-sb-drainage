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

	
  final DocumentoTipoService dtServ;

  public DocumentoTipoController(DocumentoTipoService dtServ) {
    this.dtServ = dtServ;
  }

  @PostMapping
  public ResponseEntity<Object> save(@RequestBody @Valid DocumentoTipoDTO dtDTO) {
    DocumentoTipoModel dtMod = new DocumentoTipoModel();

    BeanUtils.copyProperties(dtDTO, dtMod);
    return ResponseEntity.status(HttpStatus.CREATED).body(dtServ.save(dtMod));
  }

  @GetMapping
  public ResponseEntity<List<DocumentoTipoModel>> listAll() {
    return ResponseEntity.status(HttpStatus.CREATED).body(dtServ.listAll());
  }

  @DeleteMapping
  public ResponseEntity<String> deleteAll() {
    dtServ.deleteAll();
    return ResponseEntity.ok("Todos os tipos de documento deletados!!!");
  }
}
