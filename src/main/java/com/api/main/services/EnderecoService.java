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
	public EnderecoModel save(EnderecoModel endereco) {
		// Salva as interferências, se houver
		if (endereco.getEndInterferencias() != null && !endereco.getEndInterferencias().isEmpty()) {
			// Salva todas as interferências associadas ao endereço
			List<InterferenciaModel> interferencias = interRepo.saveAll(endereco.getEndInterferencias());
			// Atualiza o conjunto de interferências no endereço
			endereco.setEndInterferencias(new HashSet<>(interferencias));
		}

		// Salva o endereço com as interferências atualizadas
		EnderecoModel savedEndereco = endRepo.save(endereco);

		return savedEndereco;
	}

	@Transactional
	public List<EnderecoModel> list(String keyword) {
		return endRepo.list(keyword);
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
	public EnderecoModel update(Long id, EnderecoModel endereco) {
		EnderecoModel response = endRepo.findById(id).map((EnderecoModel record) -> {
			record.setEndLogradouro(endereco.getEndLogradouro());
			record.setEndCidade(endereco.getEndCidade());
			record.setEndCep(endereco.getEndCep());
			record.setEndBairro(endereco.getEndBairro());
			record.setEndEstado(endereco.getEndEstado());

			return endRepo.save(record);
		}).orElse(null);

		if (response == null) {
			throw new NoSuchElementException("Não foi encontrado endereço com o id: " + id);
		}

		return response;
	}

}
