package com.api.main.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.api.main.dto.DocumentoDTO;
import com.api.main.models.DocumentoModel;
import com.api.main.services.DocumentoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/documento")
public class DocumentoController {

	final DocumentoService docServ;

	public DocumentoController(DocumentoService docServ) {
		this.docServ = docServ;
	}

	@PostMapping
	public ResponseEntity<Object> save (@RequestBody @Valid DocumentoDTO docDTO) {
		DocumentoModel docMod = new DocumentoModel();

		BeanUtils.copyProperties(docDTO, docMod);
		return ResponseEntity.status(HttpStatus.CREATED).body(docServ.save(docMod));
	}

	@GetMapping
	public ResponseEntity<List<DocumentoModel>> listAll() {
		return ResponseEntity.status(HttpStatus.CREATED).body(docServ.listAll());
	}
	@DeleteMapping
	public ResponseEntity<String> deleteAll (){
		docServ.deleteAll();
		return ResponseEntity.ok("Todos documentos deletados!!!");
	}
}
