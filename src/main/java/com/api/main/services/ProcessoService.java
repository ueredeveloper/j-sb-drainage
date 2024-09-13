package com.api.main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.AnexoModel;
import com.api.main.models.ProcessoModel;
import com.api.main.repositories.AnexoRepository;
import com.api.main.repositories.ProcessoRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository processoRepository;

	@Autowired
	private AnexoRepository anexoRepository;

	@Transactional
	public ProcessoModel save(ProcessoModel objMod) {
		// Verifica se o anexo não tem ID (precisa ser salvo primeiro)
		if (objMod.getAnexo() != null && objMod.getAnexo().getId() == null) {
			AnexoModel anexo = objMod.getAnexo();
			anexo = anexoRepository.save(anexo); // Salva o anexo
			objMod.setAnexo(anexo); // Atualiza a referência do anexo no processo
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
	public List<ProcessoModel> listByKeyword(String keyword) {
		List<ProcessoModel> response = readJsonStringAndConvert(keyword);

		return response;
	}

	public List<ProcessoModel> readJsonStringAndConvert(String keyword) {

		List<Object> result = processoRepository.listByKeyword(keyword);
		List<ProcessoModel> response = new ArrayList<>();

		if (result == null) {
			System.out.println("No results found for the keyword: " + keyword);
			return response;
		}

		String json = result != null ? result.toString() : null;

		if (json != null) {

			System.out.println("list processo by key " + json);
			// Since the structure is a list of objects containing 'object', extract them
			List<Map<String, ProcessoModel>> tempList = new Gson().fromJson(json,
					new TypeToken<List<Map<String, ProcessoModel>>>() {
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