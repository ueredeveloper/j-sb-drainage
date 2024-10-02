package com.api.main.services;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.UsuarioModel;
import com.api.main.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioModel saveUsuario(UsuarioModel usuario) {
		return usuarioRepository.save(usuario);
	}

	public List<UsuarioModel> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	@Transactional
	public Set<UsuarioModel> listUsersByName(String keyword) {
		return usuarioRepository.listUsersByName(keyword);
	}
	
	@Transactional
	public Set<UsuarioModel> listUsersByDocumentId(Long keyword) {
		return usuarioRepository.listUsersByDocumentId(keyword);
	}


}
