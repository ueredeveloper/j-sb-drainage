package com.api.main.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.main.models.AnexoModel;
import com.api.main.repositories.AnexoRepository;

@Service
public class AnexoService {
	
	
	final AnexoRepository repository;

	public AnexoService(AnexoRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public AnexoModel save(AnexoModel procMod) {
		return repository.save(procMod);
	}

	@Transactional
	public AnexoModel update(Long id, AnexoModel updateProcesso) {
		AnexoModel responseDocumento = repository.findById(id).map((AnexoModel record) -> {
			record.setAnNumero(updateProcesso.getAnNumero());
			record.setAnPrincipal(updateProcesso.getAnPrincipal());

			return repository.save(record);
		}).orElse(null);

		if (responseDocumento == null) {
			throw new NoSuchElementException("Não foi encontrado documento com o id: " + id);
		}

		return responseDocumento;
	}

	@Transactional
	public List<AnexoModel> list(String keyword) {
		return repository.list(keyword);
	}

	@Transactional
	public List<AnexoModel> listAnexos(Long proc_processo_principal) {
		return repository.listAnexos(proc_processo_principal);
	}

	@Transactional
	public AnexoModel deleteById(Long id) {
		AnexoModel deleteResponse = repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi anexo com id: " + id));

		repository.deleteById(id);
		return deleteResponse;
	}
	@Transactional
	public void delete() {
		repository.deleteAll();
	}

}
