package com.api.main.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	
	@Autowired
	private InterferenciaService interferenciaService;

	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid InterferenciaDTO interDTO) {
		InterferenciaModel interMod = new InterferenciaModel();
		BeanUtils.copyProperties(interDTO, interMod);
		return ResponseEntity.status(HttpStatus.CREATED).body(interferenciaService.save(interMod));

	}

	@GetMapping("/list-by-keyword")
	public ResponseEntity<List<InterferenciaModel>> list(@RequestParam(required = false) String keyword) {
		List<InterferenciaModel> resultList = interferenciaService.listByLogradouro(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteProcesso(@RequestParam(required = false) Long id) {
		if (id != null) {
			// Delete a specific object by ID
			InterferenciaModel deleteResponse = interferenciaService.deleteById(id);
			if (deleteResponse != null) {
				return ResponseEntity.ok(deleteResponse);
			} else {
				return ResponseEntity.notFound().build();
			}

		} else {
			// ERROR: Não faz sentido deletar tudo se não há id
			// Delete all objects
			// interferenciaService.delete();
			// return ResponseEntity.ok("Todos os objetos deletados!!!");
			return ResponseEntity.ok("Id da interferência não informado!!!");

		}
	}
}
