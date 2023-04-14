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
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.ProcessoDTO;
import com.api.main.models.ProcessoModel;
import com.api.main.services.ProcessoService;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
@RequestMapping("/processo")
public class ProcessoController {
	
	final ProcessoService procServ;
	
	public ProcessoController (ProcessoService procServ) {
		this.procServ = procServ;
	}

	@PostMapping
	public ResponseEntity<Object> save (@RequestBody @Valid ProcessoDTO procDTO){
		
	  ProcessoModel procMod = new ProcessoModel();
	
	  BeanUtils.copyProperties(procDTO, procMod);
	  return ResponseEntity.status(HttpStatus.CREATED).body(procServ.save(procMod));
	}
	@GetMapping
	public ResponseEntity<List<ProcessoModel>> listAll () {
		return ResponseEntity.status(HttpStatus.CREATED).body(procServ.listAll());
	}
	

}
