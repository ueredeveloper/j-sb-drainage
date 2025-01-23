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

import com.api.main.dto.HidrogeoFraturadoDTO;
import com.api.main.dto.UnidadeHidrograficaDTO;
import com.api.main.models.HidrogeoFraturado;
import com.api.main.models.UnidadeHidrograficaModel;
import com.api.main.services.HIdrogeoFraturadoService;
import com.api.main.services.UnidadeHidrograficaService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/fraturado")
public class HidrogeoFraturadoController {

	@Autowired
	private HIdrogeoFraturadoService service;

	@GetMapping("/find-by-point")
	public ResponseEntity<List<HidrogeoFraturado>> findByPoint(@RequestParam Double lat,
			@RequestParam Double lng) {
		List<HidrogeoFraturado> resultList = service.findByPoint(lat, lng);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@GetMapping("/list-all")
	public ResponseEntity<List<HidrogeoFraturadoDTO>> listAll() {
		// Call the service to fetch the list
		List<HidrogeoFraturadoDTO> resultList = service.listAll();

		// Return the result with HTTP status OK
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

}
