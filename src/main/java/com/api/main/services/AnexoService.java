package com.api.main.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

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
public class AnexoService {

	@Autowired
	private AnexoRepository anexoRepository;
	@Autowired
	private ProcessoRepository processoRepository;

	@Transactional
	public AnexoModel save(AnexoModel anexo) {

		// Inicializa um novo AnexoModel
		AnexoModel newAnexo = new AnexoModel();
		newAnexo.setNumero(anexo.getNumero());

		Set<ProcessoModel> processos = new HashSet<>();

		// Itera sobre os processos para garantir que eles estão corretamente salvos e
		// relacionados
		for (ProcessoModel processo : anexo.getProcessos()) {
			if (processo.getId() == null) {
				processo.setAnexo(newAnexo);
				processo = processoRepository.save(processo);
			}
			processos.add(processo);
		}

		newAnexo.setProcessos(processos);
		return anexoRepository.save(newAnexo);
	}

	@Transactional
	public List<AnexoModel> getAll() {
		return anexoRepository.findAll();
	}

	@Transactional
	public AnexoModel getById(Long id) {
		return anexoRepository.findById(id).orElse(null);
	}

	@Transactional
	public void deleteById(Long id) {
		anexoRepository.deleteById(id);
	}

	@Transactional
	public List<AnexoModel> listByKeyword(String keyword) {

		List<AnexoModel> response = readJsonStringAndConvert(keyword);

		return response;
	}

	public List<AnexoModel> readJsonStringAndConvert(String keyword) {

		List<Object> result = anexoRepository.listByKeyword(keyword);
		List<AnexoModel> response = new ArrayList<>();

		if (result == null) {
			System.out.println("No results found for the keyword: " + keyword);
			return response;
		}

		/*
		 * exemplo de json:
		 * [{"anexo":{"id":1,"numero":"197.123.456/2013","processos":[]}},
		 * {"anexo":{"id":2,"numero":"197.456.789/2015","processos":[]}},
		 * {"anexo":{"id":3,"numero":"198.456/2013","processos":[{"id":1,"numero":
		 * "197.123/2015"}]}}]
		 */

		String json = result != null ? result.toString() : null;

		if (json != null) {

			System.out.println("list anexo by key " + json);
			// Since the structure is a list of objects containing 'endereco', extract them
			List<Map<String, AnexoModel>> tempList = new Gson().fromJson(json,
					new TypeToken<List<Map<String, AnexoModel>>>() {
					}.getType());

			// Iterate over the list and extract 'endereco' object from each map
			for (Map<String, AnexoModel> map : tempList) {
				AnexoModel endereco = map.get("anexo");
				if (endereco != null) {
					response.add(endereco);
				}
			}
		}

		return response;

	}

	public List<AnexoModel> findAll() {
		// TODO Auto-generated method stub
		return anexoRepository.findAll();
	}

	@Transactional
	public AnexoModel update(Long id, AnexoModel object) {
		AnexoModel response = anexoRepository.findById(id).map((AnexoModel record) -> {
			record.setNumero(object.getNumero());

			return anexoRepository.save(record);
		}).orElse(null);

		if (response == null) {
			throw new NoSuchElementException("Não foi encontrado documento com o id: " + id);
		}

		return response;
	}
}
