package com.api.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.models.UsuarioModel;
import com.api.main.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<UsuarioModel> createUsuario(@RequestBody UsuarioModel usuario) {
		UsuarioModel savedUsuario = usuarioService.saveUsuario(usuario);
		return ResponseEntity.ok(savedUsuario);
	}

	/*
	 * @GetMapping public ResponseEntity<List<UsuarioModel>> getAllUsuarios() {
	 * List<UsuarioModel> usuarios = usuarioService.getAllUsuarios(); return
	 * ResponseEntity.ok(usuarios); }
	 */
	@GetMapping("/list")
	public ResponseEntity<List<UsuarioModel>> list(@RequestParam(required = false) String keyword) {
		List<UsuarioModel> resultList = usuarioService.list(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

}
