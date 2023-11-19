package com.api.main.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.main.models.ProcessoModel;
import com.api.main.models.ProcessoPrincipalModel;
import com.api.main.models.ProcessoPrincipalModel;
import com.api.main.repositories.ProcessoPrincipalRepository;

@Service
public class ProcessoPrincipalService {

	final ProcessoPrincipalRepository repository;

	public ProcessoPrincipalService(ProcessoPrincipalRepository repository) {
		super();
		this.repository = repository;
	}

	@Transactional
	public ProcessoPrincipalModel save(ProcessoPrincipalModel procMod) {
		return repository.save(procMod);
	}

	@Transactional
	public ProcessoPrincipalModel update(Long id, ProcessoPrincipalModel updateProcesso) {
		Optional<ProcessoPrincipalModel> optionalProcesso = repository.findById(id);

		if (optionalProcesso.isPresent()) {
			ProcessoPrincipalModel processo = optionalProcesso.get();
			processo.setProcNumero(updateProcesso.getProcNumero());
			return repository.save(processo);
		} else {
			throw new EntityNotFoundException("ProcessoPrincipalModel with ID " + id + " not found.");
		}
	}

	@Transactional
	public List<ProcessoPrincipalModel> list(String keyword) {
		return repository.list(keyword);
	}

	@Transactional
	public List<ProcessoModel> listChildrens(Long proc_processo_principal) {
		return repository.listChildrens(proc_processo_principal);
	}

	@Transactional
	public ProcessoPrincipalModel deleteById(Long id) {
		ProcessoPrincipalModel deleteResponse = repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado processo com o id: " + id));

		repository.deleteById(id);
		return deleteResponse;
	}

	@Transactional
	public void delete() {
		repository.deleteAll();
	}

};