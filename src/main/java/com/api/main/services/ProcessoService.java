package com.api.main.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.AnexoModel;
import com.api.main.models.ProcessoModel;
import com.api.main.models.UsuarioModel;
import com.api.main.repositories.AnexoRepository;
import com.api.main.repositories.ProcessoRepository;
import com.api.main.repositories.UsuarioRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository processoRepository;

	@Autowired
	private AnexoRepository anexoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public ProcessoModel save(ProcessoModel objMod) {
		// Verifica se o anexo não tem ID (precisa ser salvo primeiro)
		if (objMod.getAnexo() != null && objMod.getAnexo().getId() == null) {
			AnexoModel anexo = objMod.getAnexo();
			anexo = anexoRepository.save(anexo); // Salva o anexo
			objMod.setAnexo(anexo); // Atualiza a referência do anexo no processo
		}
		
		if (objMod.getUsuario() != null && objMod.getUsuario().getId() == null) {
			UsuarioModel usuario = objMod.getUsuario();
			usuario = usuarioRepository.save(usuario); // Salva o anexo
			objMod.setUsuario(usuario); // Atualiza a referência do anexo no processo
		}
		
		return processoRepository.save(objMod); // Salva o processo
	}

	@Transactional
	public ProcessoModel update(Long id, ProcessoModel updateProcesso) {
		Optional<ProcessoModel> optionalProcesso = processoRepository.findById(id);

		if (optionalProcesso.isPresent()) {
			ProcessoModel processo = optionalProcesso.get();
			processo.setNumero(updateProcesso.getNumero());
			return processoRepository.save(processo);
		} else {
			throw new EntityNotFoundException("ProcessoPrincipalModel with ID " + id + " not found.");
		}
	}

	@Transactional
	public Set<ProcessoModel> listByKeyword(String keyword) {
		Set<ProcessoModel> response = readJsonStringAndConvert(keyword);

		return response;
	}

	public Set<ProcessoModel> readJsonStringAndConvert(String keyword) {

		List<Object> result = processoRepository.listByKeyword(keyword);
		Set<ProcessoModel> response = new HashSet<>();
		
		System.out.println(result);

		if (result == null) {
			System.out.println("No results found for the keyword: " + keyword);
			return response;
		}

		String json = result != null ? result.toString() : null;
		
		

		if (json != null) {

			System.out.println("list processo by key " + json);
			// Since the structure is a list of objects containing 'object', extract them
			Set<Map<String, ProcessoModel>> tempList = new Gson().fromJson(json,
					new TypeToken<Set<Map<String, ProcessoModel>>>() {
					}.getType());

			// Iterate over the list and extract 'object' object from each map
			for (Map<String, ProcessoModel> map : tempList) {
				ProcessoModel object = map.get("processo");
				if (object != null) {
					response.add(object);
				}
			}
		}

		return response;

	}

	@Transactional
	public ProcessoModel deleteById(Long id) {
		ProcessoModel deleteResponse = processoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado processo com o id: " + id));

		processoRepository.deleteById(id);
		return deleteResponse;
	}

	@Transactional
	public void delete() {
		processoRepository.deleteAll();
	}

};