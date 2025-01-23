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

import com.api.main.dto.BaciaHidrograficaDTO;
import com.api.main.models.BaciaHidrograficaModel;
import com.api.main.services.BaciasHidrograficasService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/hydrographic-basins")
public class BaciaHidrograficaController {

	@Autowired
	private BaciasHidrograficasService service;

	@GetMapping("/find-by-point")
	public ResponseEntity<List<BaciaHidrograficaModel>> findByPoint (@RequestParam Double lat,
			@RequestParam Double lng) {
		List<BaciaHidrograficaModel> resultList = service.findByPoint(lat, lng);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
	
	@GetMapping("/list-all")
    public ResponseEntity<List<BaciaHidrograficaDTO>> getAllBaciasHidrograficas() {
        // Call the service to fetch the list
        List<BaciaHidrograficaDTO> resultList = service.listAll();

        // Return the result with HTTP status OK
        return ResponseEntity.status(HttpStatus.OK).body(resultList);
    }

}
