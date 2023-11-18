package com.api.main.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.main.models.ProcessoModel;
import com.api.main.repositories.ProcessoRepository;

@Service
public class ProcessoService {
	

	final ProcessoRepository repository;

	public ProcessoService(ProcessoRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public ProcessoModel save(ProcessoModel procMod) {
		return repository.save(procMod);
	}

	@Transactional
	public List<ProcessoModel> listChildrens(Long proc_processo_principal) {
		return repository.listChildrens(proc_processo_principal);
	}

	@Transactional
	public void delete() {
		repository.deleteAll();
	}

	@Transactional
	public List<ProcessoModel> list(String keyword) {
		return repository.list(keyword);
	}

	@Transactional
	public ProcessoModel update(Long id, ProcessoModel updateProcesso) {
		ProcessoModel responseDocumento = repository.findById(id).map((ProcessoModel record) -> {
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
	public ProcessoModel deleteById(Long id) {
		ProcessoModel deleteResponse = repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado processo com o id: " + id));

		repository.deleteById(id);
		return deleteResponse;
	}

}
