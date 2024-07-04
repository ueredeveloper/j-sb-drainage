package com.api.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.models.TemplateModel;
import com.api.main.services.TemplateService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/template")
public class TemplateController {

	@Autowired
	private TemplateService service;

	@PostMapping("/create")
	public TemplateModel create(@RequestBody TemplateModel requestedObject) {
		return service.save(requestedObject);
	}

	@GetMapping("/list-all")
	public ResponseEntity<List<TemplateModel>> findAll() {
		List<TemplateModel> resultList = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

}