package com.api.main.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.dto.InterferenciaDTO;
import com.api.main.models.EnderecoModel;
import com.api.main.models.InterferenciaModel;
import com.api.main.models.SubterraneaModel;
import com.api.main.repositories.EnderecoRepository;
import com.api.main.repositories.InterferenciaRepository;

@Service
public class InterferenciaService {

	@Autowired
	private InterferenciaRepository interferenciaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Transactional
	public List<InterferenciaModel> list(String keyword) {
		return interferenciaRepository.list(keyword);
	}

	@Transactional
	public InterferenciaModel save(InterferenciaModel requestedObject) {
	    // Se houver endereço preenchido
	    if (requestedObject.getInterEndereco() != null) {
	        EnderecoModel endereco = requestedObject.getInterEndereco();

	        // Verificar se há ID e buscar endereço existente
	        if (endereco.getEndId() != null) {
	            Optional<EnderecoModel> enderecoOptional = enderecoRepository.findById(endereco.getEndId());
	            enderecoOptional.ifPresent(existingEndereco -> {
	            	
	            	System.out.println(existingEndereco.getEndId());
	            	
	                // Atualizar atributos como Cidade e Cep
	                existingEndereco.setEndLogradouro(endereco.getEndLogradouro());
	                existingEndereco.setEndBairro(endereco.getEndBairro());
	                existingEndereco.setEndCidade(endereco.getEndCidade());
	                existingEndereco.setEndCep(endereco.getEndCep());

	                enderecoRepository.save(existingEndereco);
	                requestedObject.setInterEndereco(existingEndereco);
	            });
	        } else {
	            // Salvar novo endereço
	            EnderecoModel newEndereco = new EnderecoModel();
	            newEndereco.setEndLogradouro(endereco.getEndLogradouro());
	            newEndereco.setEndCidade(endereco.getEndCidade());
	            newEndereco.setEndCep(endereco.getEndCep());

	            EnderecoModel savedEndereco = enderecoRepository.save(newEndereco);
	            requestedObject.setInterEndereco(savedEndereco);
	        }
	    }

	    return interferenciaRepository.save(requestedObject);
	}

	@Transactional
	public List<InterferenciaModel> searchInterferenciasByLogradouro(String keyword) {
		return interferenciaRepository.searchInterferenciasByLogradouro(keyword);
	}

	@Transactional
	public InterferenciaModel deleteById(Long id) {
		InterferenciaModel response = interferenciaRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não encontrado, id: " + id));
		interferenciaRepository.deleteById(id);
		return response;
	}

}
