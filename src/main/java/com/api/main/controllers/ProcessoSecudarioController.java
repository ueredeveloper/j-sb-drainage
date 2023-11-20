package com.api.main.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.ProcessoSecudarioDTO;
import com.api.main.models.ProcessoSecudarioModel;
import com.api.main.services.ProcessoSecudarioService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/processes-secondary")
public class ProcessoSecudarioController {

	
	final ProcessoSecudarioService service;

	public ProcessoSecudarioController(ProcessoSecudarioService service) {
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid ProcessoSecudarioDTO procDTO) {

		ProcessoSecudarioModel procMod = new ProcessoSecudarioModel();

		BeanUtils.copyProperties(procDTO, procMod);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(procMod));
	}


	@GetMapping("/list")
	public ResponseEntity<List<ProcessoSecudarioModel>> list(@RequestParam(required = false) String keyword) {
		List<ProcessoSecudarioModel> resultList = service.list(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody ProcessoSecudarioModel udpateProcesso) {
		ProcessoSecudarioModel updated = service.update(id, udpateProcesso);
		if (updated != null) {
			return ResponseEntity.ok().body(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteProcesso(@RequestParam(required = false) Long id) {
		if (id != null) {
			// Delete a specific object by ID
			ProcessoSecudarioModel deleteResponse = service.deleteById(id);
			if (deleteResponse != null) {
				return ResponseEntity.ok(deleteResponse);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			// Delete all objects
			service.delete();
			return ResponseEntity.ok("Todos os objetos deletados!!!");
		}
	}

}
