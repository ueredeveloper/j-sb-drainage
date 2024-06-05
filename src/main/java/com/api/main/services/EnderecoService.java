package com.api.main.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.main.models.EnderecoModel;
import com.api.main.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	
	final EnderecoRepository endRepo;

	public EnderecoService(EnderecoRepository endRepo) {
		super();
		this.endRepo = endRepo;
	};
	
	
	@Transactional
	public EnderecoModel save(EnderecoModel endModel) {
		return endRepo.save(endModel);
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
	public EnderecoModel update(Long id, EnderecoModel updateDocumento) {
		EnderecoModel responseDocumento = endRepo.findById(id).map((EnderecoModel record) -> {
			record.setEndLogradouro(updateDocumento.getEndLogradouro());
			record.setEndCidade(updateDocumento.getEndCidade());
			record.setEndCep(updateDocumento.getEndCep());
			return endRepo.save(record);
		}).orElse(null);

		if (responseDocumento == null) {
			throw new NoSuchElementException("Não foi encontrado endereço com o id: " + id);
		}

		return responseDocumento;
	}
	
}
