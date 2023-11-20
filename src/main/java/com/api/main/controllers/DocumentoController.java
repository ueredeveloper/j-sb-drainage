package com.api.main.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.DocumentoDTO;
import com.api.main.models.DocumentoModel;
import com.api.main.models.ProcessoSecudarioModel;
import com.api.main.repositories.ProcessoSecudarioRepository;
import com.api.main.services.DocumentoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/document")
public class DocumentoController {

	
	final DocumentoService service;
	final ProcessoSecudarioRepository procRepo;

	public DocumentoController(DocumentoService service, ProcessoSecudarioRepository procRepo) {
		this.service = service;
		this.procRepo = procRepo;
	}
	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid DocumentoDTO docDTO) {
		DocumentoModel docMod = new DocumentoModel();
		BeanUtils.copyProperties(docDTO, docMod);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(docMod));
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody DocumentoModel updateDocumento) {
		DocumentoModel updated = service.update(id, updateDocumento);
		if (updated != null) {
			return ResponseEntity.ok().body(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<DocumentoModel>> list(@RequestParam(required = false) String keyword) {
		List<DocumentoModel> resultList = service.list(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
	
	/*// Buscar todos os resultados
	@GetMapping("/list")
	public ResponseEntity<List<DocumentoModel>> listAll() {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.list());
	}

	// Buscar por parâmetro
	@GetMapping("/search")
	public ResponseEntity<List<DocumentoModel>> searchDocuments(@RequestParam String keyword) {
		List<DocumentoModel> searchResults = service.search(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(searchResults);
	}*/

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteProcesso(@RequestParam(required = false) Long id) {
		if (id != null) {
			// Delete a specific object by ID
			DocumentoModel deleteResponse = service.deleteById(id);
			if (deleteResponse != null) {
				return ResponseEntity.ok(deleteResponse);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			// Delete all objects
			service.delete();
			return ResponseEntity.ok("Todos os objetos deletados!!!");
		}
	}
}
