package com.api.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.DominioDTO;
import com.api.main.services.DominioService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/domain")
public class DominioController {

	
	@Autowired
	private DominioService dominioService;

	@GetMapping("/tables")
	public ResponseEntity<DominioDTO> list() {
		DominioDTO response = dominioService.listAll();
		return ResponseEntity.ok(response);
	}

}
