package com.api.main.services;

import java.util.List;
import java.util.NoSuchElementException;
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
	public Set<UsuarioModel> findByCpfCnpjContaining(String keyword) {
		return usuarioRepository.findByCpfCnpjContaining(keyword);
	}

	@Transactional
	public Set<UsuarioModel> listUsersByDocumentId(Long keyword) {
		return usuarioRepository.listUsersByDocumentId(keyword);
	}

	@Transactional
	public UsuarioModel deleteById(Long id) {
		UsuarioModel toDelete = usuarioRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado documento com o id: " + id));
		usuarioRepository.deleteById(id);
		return toDelete;
	}

	@Transactional
	public UsuarioModel update(Long id, UsuarioModel toUpdateObject) {

		UsuarioModel originalResponse = usuarioRepository.findById(id).map((UsuarioModel record) -> {
			record.setNome(toUpdateObject.getNome());
			record.setCpfCnpj(toUpdateObject.getCpfCnpj());

			return usuarioRepository.save(record);

		}).orElse(null);

		if (originalResponse == null) {
			throw new NoSuchElementException("Não foi encontrado documento com o id: " + id);
		}

		return originalResponse;

	}

}
