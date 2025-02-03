package com.api.main.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.api.main.dto.DTUsuarioDTO;
import com.api.main.models.UsuarioModel;
import com.api.main.services.UsuarioService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/create")
	public ResponseEntity<UsuarioModel> createUsuario(@RequestBody UsuarioModel usuario) {
		UsuarioModel savedUsuario = usuarioService.saveUsuario(usuario);
		return ResponseEntity.ok(savedUsuario);
	}

	@GetMapping("/list-by-name")
	public ResponseEntity<Set<UsuarioModel>> listUsersByName(@RequestParam(required = false) String name) {
		Set<UsuarioModel> resultList = usuarioService.listUsersByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
	
	@GetMapping("/list-by-keyword")
	public ResponseEntity<Set<DTUsuarioDTO>> listUsersByKeyword (@RequestParam(required = false) String keyword) {
		Set<DTUsuarioDTO> resultList = usuarioService.listUserByKeyword(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
	
	@GetMapping("/list-by-cpf-cnpj")
	public ResponseEntity<Set<UsuarioModel>> findByCpfCnpjContaining(@RequestParam(required = false) String keyword) {
		Set<UsuarioModel> resultList = usuarioService.findByCpfCnpjContaining(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@GetMapping("/list-by-document-id")
	public ResponseEntity<Set<UsuarioModel>> listUsersByDocumentId(@RequestParam(required = false) Long id) {
		Set<UsuarioModel> resultList = usuarioService.listUsersByDocumentId(id);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteProcesso(@RequestParam(required = false) Long id) {
		if (id != null) {
			// Delete a specific object by ID
			UsuarioModel deleteResponse = usuarioService.deleteById(id);
			if (deleteResponse != null) {
				return ResponseEntity.ok(deleteResponse);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.ok("Informe um id existente ou não nulo!!!");
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody UsuarioModel toUpdateObject) {
		UsuarioModel updateRespose = usuarioService.update(id, toUpdateObject);
		if (updateRespose != null) {
			return ResponseEntity.ok().body(updateRespose);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
