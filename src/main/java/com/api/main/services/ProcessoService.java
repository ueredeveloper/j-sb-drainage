package com.api.main.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.main.models.AnexoModel;
import com.api.main.models.ProcessoModel;
import com.api.main.repositories.ProcessoRepository;

@Service
public class ProcessoService {

	
	final ProcessoRepository repository;

	public ProcessoService(ProcessoRepository repository) {
		super();
		this.repository = repository;
	}

	@Transactional
	public ProcessoModel save(ProcessoModel procMod) {
		return repository.save(procMod);
	}

	@Transactional
	public ProcessoModel update(Long id, ProcessoModel updateProcesso) {
		Optional<ProcessoModel> optionalProcesso = repository.findById(id);

		if (optionalProcesso.isPresent()) {
			ProcessoModel processo = optionalProcesso.get();
			processo.setProcNumero(updateProcesso.getProcNumero());
			return repository.save(processo);
		} else {
			throw new EntityNotFoundException("ProcessoPrincipalModel with ID " + id + " not found.");
		}
	}

	@Transactional
	public List<ProcessoModel> list(String keyword) {
		return repository.list(keyword);
	}

	@Transactional
	public List<AnexoModel> listChildrens(Long proc_processo_principal) {
		return repository.listChildrens(proc_processo_principal);
	}

	@Transactional
	public ProcessoModel deleteById(Long id) {
		ProcessoModel deleteResponse = repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado processo com o id: " + id));

		repository.deleteById(id);
		return deleteResponse;
	}

	@Transactional
	public void delete() {
		repository.deleteAll();
	}

};