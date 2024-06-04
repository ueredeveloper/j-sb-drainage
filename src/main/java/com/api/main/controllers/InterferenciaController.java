package com.api.main.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.InterferenciaDTO;
import com.api.main.models.InterferenciaModel;
import com.api.main.services.InterferenciaService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/interference")
public class InterferenciaController {
	
	final InterferenciaService interService;

	public InterferenciaController(InterferenciaService interService) {
		super();
		this.interService = interService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid InterferenciaDTO interDTO) {
		InterferenciaModel interMod = new InterferenciaModel();
		BeanUtils.copyProperties(interDTO, interMod);
		return ResponseEntity.status(HttpStatus.CREATED).body(interService.save(interDTO, interMod));
		
	}

	@GetMapping("/list")
	public ResponseEntity<List<InterferenciaModel>> list(@RequestParam(required = false) String keyword) {
		List<InterferenciaModel> resultList = interService.list(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
}
