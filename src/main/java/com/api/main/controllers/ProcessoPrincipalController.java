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

import com.api.main.dto.ProcessoPrincipalDTO;
import com.api.main.models.ProcessoModel;
import com.api.main.models.ProcessoPrincipalModel;
import com.api.main.models.ProcessoPrincipalModel;
import com.api.main.services.ProcessoPrincipalService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/processo-principal")
public class ProcessoPrincipalController {
	
	final ProcessoPrincipalService service;

	public ProcessoPrincipalController(ProcessoPrincipalService service) {
		super();
		this.service = service;
	}
	

	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid ProcessoPrincipalDTO procDTO) {

		ProcessoPrincipalModel procMod = new ProcessoPrincipalModel();

		BeanUtils.copyProperties(procDTO, procMod);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(procMod));
	}
	@PutMapping(value = "/update")
	public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody ProcessoPrincipalModel udpateProcesso) {
		ProcessoPrincipalModel updated = service.update(id, udpateProcesso);
		if (updated != null) {
			return ResponseEntity.ok().body(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/list")
	public ResponseEntity<List<ProcessoPrincipalModel>> list(@RequestParam(required = false) String keyword) {
		List<ProcessoPrincipalModel> resultList = service.list(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@GetMapping("/list-childrens")
	public ResponseEntity<List<ProcessoModel>> listChildrens (@RequestParam("id") Long id) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.listChildrens(id));
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteProcesso(@RequestParam(required = false) Long id) {
		if (id != null) {
			// Delete a specific object by ID
			ProcessoPrincipalModel deleteResponse = service.deleteById(id);
			if (deleteResponse != null) {
				return ResponseEntity.ok(deleteResponse);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			// Delete all objects
			service.delete();
			return ResponseEntity.ok("Processo principal deletado!!!");
		}
	}

}
