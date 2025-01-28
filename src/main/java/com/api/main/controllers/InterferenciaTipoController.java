package com.api.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.models.TipoInterferenciaModel;
import com.api.main.services.TipoInterferenciaService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/interference-type")
public class InterferenciaTipoController {

	
	@Autowired
	private TipoInterferenciaService tipoInterferenciaService;

	@GetMapping("/list-all")
	public ResponseEntity<List<TipoInterferenciaModel>> listAll() {
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoInterferenciaService.listAll());
	}

}