package com.api.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.models.AnexoModel;
import com.api.main.services.AnexoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/attachment")
public class AnexoController {

	@Autowired
	private AnexoService anexoService;

	@PostMapping("/create")
	public AnexoModel create(@RequestBody AnexoModel anexo) {
		return anexoService.save(anexo);
	}

	@GetMapping("/list-by-keyword")
	public ResponseEntity<List<AnexoModel>> listByKeyword(@RequestParam(required = false) String keyword) {
		List<AnexoModel> resultList = anexoService.listByKeyword(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@GetMapping("/list-all")
	public ResponseEntity<List<AnexoModel>> findAll() {
		List<AnexoModel> resultList = anexoService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestParam("id") Long id, @RequestBody AnexoModel object) {
		AnexoModel updated = anexoService.update(id, object);
		if (updated != null) {
			return ResponseEntity.ok().body(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
