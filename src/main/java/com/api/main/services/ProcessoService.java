package com.api.main.services;

import java.util.List;
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

@Service
public class ProcessoService {

	
	@Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private AnexoRepository anexoRepository;

	@Transactional
	 public ProcessoModel save(ProcessoModel procMod) {
        // Verifica se o anexo não tem ID (precisa ser salvo primeiro)
        if (procMod.getAnexo() != null && procMod.getAnexo().getId() == null) {
            AnexoModel anexo = procMod.getAnexo();
            anexo = anexoRepository.save(anexo); // Salva o anexo
            procMod.setAnexo(anexo); // Atualiza a referência do anexo no processo
        }
        return processoRepository.save(procMod); // Salva o processo
    }

	@Transactional
	public ProcessoModel update(Long id, ProcessoModel updateProcesso) {
		Optional<ProcessoModel> optionalProcesso = processoRepository.findById(id);

		if (optionalProcesso.isPresent()) {
			ProcessoModel processo = optionalProcesso.get();
			processo.setProcNumero(updateProcesso.getProcNumero());
			return processoRepository.save(processo);
		} else {
			throw new EntityNotFoundException("ProcessoPrincipalModel with ID " + id + " not found.");
		}
	}

	@Transactional
	public List<ProcessoModel> list(String keyword) {
		return processoRepository.list(keyword);
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