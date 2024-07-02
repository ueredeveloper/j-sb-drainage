package com.api.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.models.EstadoModel;
import com.api.main.services.EstadoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/state")
public class EstadoController {
	@Autowired
	EstadoService estadoService;

	@GetMapping("/find-all")
	public ResponseEntity<List<EstadoModel>> findAll() {
		List<EstadoModel> resultList = estadoService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

}
