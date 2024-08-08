package com.api.main.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.DocumentoDTO;
import com.api.main.models.DocumentoModel;
import com.api.main.services.DocumentoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/document")
public class DocumentoController {

	
	
	@Autowired
	private DocumentoService documentoService;

	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid DocumentoDTO objDTO) {
		DocumentoModel objMod = new DocumentoModel();
		BeanUtils.copyProperties(objDTO, objMod);
		
		System.out.println("interferencias size ------------- " + objMod.getEndereco().getInterferencias().size());
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(documentoService.save(objDTO, objMod));

	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody DocumentoModel updateDocumento) {
		DocumentoModel updated = documentoService.update(id, updateDocumento);
		if (updated != null) {
			return ResponseEntity.ok().body(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/list")
	public ResponseEntity<List<DocumentoModel>> listByKeyword(@RequestParam(required = false) String keyword) {
		List<DocumentoModel> resultList = documentoService.listByKeyword(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteProcesso(@RequestParam(required = false) Long id) {
		if (id != null) {
			// Delete a specific object by ID
			DocumentoModel deleteResponse = documentoService.deleteById(id);
			if (deleteResponse != null) {
				return ResponseEntity.ok(deleteResponse);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			// Delete all objects
			documentoService.delete();
			return ResponseEntity.ok("Todos os objetos deletados!!!");
		}
	}
}
