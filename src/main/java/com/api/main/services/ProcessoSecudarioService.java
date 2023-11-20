package com.api.main.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.main.models.ProcessoSecudarioModel;
import com.api.main.repositories.ProcessoSecudarioRepository;

@Service
public class ProcessoSecudarioService {
	

	final ProcessoSecudarioRepository repository;

	public ProcessoSecudarioService(ProcessoSecudarioRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public ProcessoSecudarioModel save(ProcessoSecudarioModel procMod) {
		return repository.save(procMod);
	}

	@Transactional
	public List<ProcessoSecudarioModel> listChildrens(Long proc_processo_principal) {
		return repository.listChildrens(proc_processo_principal);
	}

	@Transactional
	public List<ProcessoSecudarioModel> list(String keyword) {
		return repository.list(keyword);
	}

	@Transactional
	public ProcessoSecudarioModel update(Long id, ProcessoSecudarioModel updateProcesso) {
		ProcessoSecudarioModel responseDocumento = repository.findById(id).map((ProcessoSecudarioModel record) -> {
			record.setProcNumero(updateProcesso.getProcNumero());
			record.setProcPrincipal(updateProcesso.getProcPrincipal());

			return repository.save(record);
		}).orElse(null);

		if (responseDocumento == null) {
			throw new NoSuchElementException("Não foi encontrado documento com o id: " + id);
		}

		return responseDocumento;
	}

	@Transactional
	public ProcessoSecudarioModel deleteById(Long id) {
		ProcessoSecudarioModel deleteResponse = repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado processo com o id: " + id));

		repository.deleteById(id);
		return deleteResponse;
	}
	@Transactional
	public void delete() {
		repository.deleteAll();
	}

}
