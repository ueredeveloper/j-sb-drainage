package com.api.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.UnidadeHidrograficaDTO;
import com.api.main.models.UnidadeHidrograficaModel;
import com.api.main.services.UnidadeHidrograficaService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/hydrographic-unit")
public class UnidadeHidrograficaController {

	@Autowired
	private UnidadeHidrograficaService service;

	@GetMapping("/find-by-point")
	public ResponseEntity<List<UnidadeHidrograficaModel>> findByPoint(@RequestParam Double lat,
			@RequestParam Double lng) {
		List<UnidadeHidrograficaModel> resultList = service.findByPoint(lat, lng);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@GetMapping("/list-all")
	public ResponseEntity<List<UnidadeHidrograficaDTO>> listAll() {
		// Call the service to fetch the list
		List<UnidadeHidrograficaDTO> resultList = service.listAll();

		// Return the result with HTTP status OK
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

}
