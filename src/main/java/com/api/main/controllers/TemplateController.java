package com.api.main.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.TemplateDTO;
import com.api.main.models.TemplateModel;
import com.api.main.services.TemplateService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/template")
public class TemplateController {

	@Autowired
	private TemplateService service;

	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid TemplateDTO objectDTO) {
		TemplateModel objectMod = new TemplateModel();
		BeanUtils.copyProperties(objectDTO, objectMod);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(objectDTO, objectMod));

	}

	@GetMapping("/list-templates-by-params")

	public ResponseEntity<Set<TemplateModel>> listByKeyword(@RequestParam(required = false) String tipoDocumento,
			@RequestParam(required = false) String tipoOutorga, @RequestParam(required = false) String subtipoOutorga) {
		Set<TemplateModel> resultList = service.listTemplatesByParams(tipoDocumento, tipoOutorga, subtipoOutorga);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@GetMapping("/list-by-keyword")
	public ResponseEntity<Set<TemplateModel>> listByKeyword(@RequestParam(required = false) String keyword) {
		Set<TemplateModel> resultList = service.listByKeyword(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

}