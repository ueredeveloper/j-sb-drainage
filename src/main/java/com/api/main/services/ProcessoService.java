package com.api.main.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.main.models.DocumentoModel;
import com.api.main.models.ProcessoModel;
import com.api.main.repositories.ProcessoRepository;

@Service
public class ProcessoService {

	final ProcessoRepository procRepo;

	public ProcessoService(ProcessoRepository procRepo) {
		this.procRepo = procRepo;
	}

	@Transactional
	public ProcessoModel save(ProcessoModel procMod) {
		return procRepo.save(procMod);
	}

	@Transactional
	public List<ProcessoModel> list() {
		return procRepo.findAll();
	}

	@Transactional
	public List<ProcessoModel> findChildrens(Long proc_processo_principal) {
		return procRepo.findChildrens(proc_processo_principal);
	}

	@Transactional
	public void delete() {
		procRepo.deleteAll();
	}
	@Transactional
	public List<ProcessoModel> search(String keyword) {
		return procRepo.search(keyword);
	}

	@Transactional
	public ProcessoModel update(Long id, ProcessoModel updateProcesso) {
		ProcessoModel responseDocumento = procRepo.findById(id).map((ProcessoModel record) -> {
			record.setProcNumero(updateProcesso.getProcNumero());
			record.setProcPrincipal(updateProcesso.getProcPrincipal());

			return procRepo.save(record);
		}).orElse(null);

		if (responseDocumento == null) {
			throw new NoSuchElementException("Não foi encontrado documento com o id: " + id);
		}

		return responseDocumento;
	}

	@Transactional
	public ProcessoModel deleteById(Long id) {
		ProcessoModel deleteResponse = procRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado processo com o id: " + id));

		procRepo.deleteById(id);
		return deleteResponse;
	}


}
