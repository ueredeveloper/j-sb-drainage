package com.api.main.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.DemandaPorEnderecoIdDTO;
import com.api.main.services.DemandaService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/demand")
public class DemandaController {

	@Autowired
	private DemandaService demandaService;

	@GetMapping("/search-by-end-id")
	public ResponseEntity<Set<DemandaPorEnderecoIdDTO>> getDemandaByAddressId(@RequestParam Long endId) {
		Set<DemandaPorEnderecoIdDTO> result = demandaService.getDemandaByAddressId(endId);
		return ResponseEntity.ok(result);
	}

}
