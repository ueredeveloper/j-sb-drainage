package com.api.main.services;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.EnderecoModel;
import com.api.main.models.InterferenciaModel;
import com.api.main.repositories.EnderecoRepository;
import com.api.main.repositories.InterferenciaRepository;

@Service
public class EnderecoService {
	

	
	@Autowired
	private EnderecoRepository endRepo;
	@Autowired
	private InterferenciaRepository interRepo;

	@Transactional
	public EnderecoModel save(EnderecoModel requestedEndereco) {
	    Long id = requestedEndereco.getId();
	    EnderecoModel response = null;

	    if (id != null && endRepo.existsById(id)) {
	        response = endRepo.findById(id).map((EnderecoModel record) -> {
	            record.setLogradouro(requestedEndereco.getLogradouro());
	            record.setCidade(requestedEndereco.getCidade());
	            record.setCep(requestedEndereco.getCep());
	            record.setBairro(requestedEndereco.getBairro());
	            record.setEstado(requestedEndereco.getEstado());
	            return endRepo.save(record);
	        }).orElse(null);
	    }

	    if (response == null) {
	        // Salva as interferências, se houver
	        if (requestedEndereco.getInterferencias() != null && !requestedEndereco.getInterferencias().isEmpty()) {
	            // Salva todas as interferências associadas ao endereço
	            List<InterferenciaModel> interferencias = interRepo.saveAll(requestedEndereco.getInterferencias());
	            // Atualiza o conjunto de interferências no endereço
	            requestedEndereco.setInterferencias(new HashSet<>(interferencias));
	        }

	        // Salva o endereço com as interferências atualizadas
	        response = endRepo.save(requestedEndereco);
	    }

	    return response;
	}


	@Transactional
	public List<EnderecoModel> listByKeyword(String keyword) {
		return endRepo.listByKeyword(keyword);
	}

	@Transactional
	public void delete() {
		endRepo.deleteAll();
	}

	@Transactional
	public EnderecoModel deleteById(Long id) {
		EnderecoModel deleted = endRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado endereço com o id: " + id));
		endRepo.deleteById(id);
		return deleted;
	}

	@Transactional
	public Optional<EnderecoModel> findById(Long id) {
		return endRepo.findById(id);
	}

	@Transactional
	public EnderecoModel update(Long id, EnderecoModel requestedEndereco) {
		EnderecoModel response = endRepo.findById(id).map((EnderecoModel record) -> {
			record.setLogradouro(requestedEndereco.getLogradouro());
			record.setCidade(requestedEndereco.getCidade());
			record.setCep(requestedEndereco.getCep());
			record.setBairro(requestedEndereco.getBairro());
			record.setEstado(requestedEndereco.getEstado());

			return endRepo.save(record);
		}).orElse(null);

		if (response == null) {
			throw new NoSuchElementException("Não foi encontrado endereço com o id: " + id);
		}

		return response;
	}

}
