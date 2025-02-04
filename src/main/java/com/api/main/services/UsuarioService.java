package com.api.main.services;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.dto.DTDemandaDTO;
import com.api.main.dto.DTUsuarioDTO;
import com.api.main.models.UsuarioModel;
import com.api.main.repositories.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ObjectMapper objectMapper;

	public UsuarioModel saveUsuario(UsuarioModel usuario) {
		return usuarioRepository.save(usuario);
	}

	public List<UsuarioModel> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	@Transactional
	public Set<UsuarioModel> listUsersByName(String name) {
		return usuarioRepository.listUsersByName(name);
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
	
	@Transactional
	public Set<DTUsuarioDTO> listUserByKeyword (String keyword) {
		
		System.out.println(keyword);
		// Recuperando os dados brutos da consulta
		Set<Object[]> rawResults = usuarioRepository.listUserByKeyword(keyword);
		Set<DTUsuarioDTO> result = new HashSet<>();

		// Iterando pelos resultados e mapeando para o DTO
		for (Object[] row : rawResults) {

			DTUsuarioDTO dto = new DTUsuarioDTO();

			/// Mapeando os campos da consulta para o DTO
	        dto.setUs_id(row[0] != null ? ((Number) row[0]).longValue() : null);
	        dto.setUs_nome((String) row[1]);
	        dto.setUs_cpf_cnpj(row[2] != null ? Long.parseLong(row[2].toString()) : null);
	        dto.setUs_doc_id(row[3] != null && !row[3].toString().trim().isEmpty() ? Long.parseLong(row[3].toString()) : null);

	        dto.setDoc_end(row[4] != null ? Long.parseLong(row[4].toString()) : null);
	        dto.setDoc_sei(row[5] != null ? Long.parseLong(row[5].toString()) : null);
	        dto.setProc_sei(row[6] != null ? Long.parseLong(row[6].toString()) : null);
	        dto.setEnd_id(row[7] != null ? Long.parseLong(row[7].toString()) : null);
	        dto.setEnd_logradouro((String) row[8]);
			
			// Adicionando o DTO ao conjunto de resultados
			result.add(dto);
		}

		return result;
	}

}
