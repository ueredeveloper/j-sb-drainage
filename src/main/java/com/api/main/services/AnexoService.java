package com.api.main.services;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.AnexoModel;
import com.api.main.models.ProcessoModel;
import com.api.main.repositories.AnexoRepository;
import com.api.main.repositories.ProcessoRepository;

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
		return anexoRepository.listByKeyword(keyword);
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
