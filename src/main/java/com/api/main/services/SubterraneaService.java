package com.api.main.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.dto.SubterraneaDTO;
import com.api.main.models.EnderecoModel;
import com.api.main.models.SubterraneaModel;
import com.api.main.repositories.EnderecoRepository;
import com.api.main.repositories.SubterraneaRepository;

@Service
public class SubterraneaService {

	@Autowired
	private SubterraneaRepository subterraneaRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Transactional
	public List<SubterraneaModel> list(String keyword) {
		return subterraneaRepository.list(keyword);
	}

	@Transactional
	public SubterraneaModel save(SubterraneaDTO subDTO, SubterraneaModel subMod) {

		if (subDTO.getInterEndereco() != null) {

			EnderecoModel endereco = new EnderecoModel();
			endereco.setEndLogradouro(subDTO.getInterEndereco().getEndLogradouro());

			endereco = enderecoRepository.save(endereco);

			subMod.setInterEndereco(endereco);

		}

		return subterraneaRepository.save(subMod);
	}

	@Transactional
	public SubterraneaModel update(Long id, SubterraneaModel requestedObject) {
		SubterraneaModel response = subterraneaRepository.findById(id).map((SubterraneaModel record) -> {

			record.setInterLatitude(requestedObject.getInterLatitude());
			record.setInterLongitude(requestedObject.getInterLongitude());
			record.setInterferenciaTipo(requestedObject.getInterferenciaTipo());

			// Se houver endereço preenchido
						if (requestedObject.getInterEndereco() != null) {
							
							// Se houver id, editar.
							if (requestedObject.getInterEndereco().getEndId() != null) {
								Optional<EnderecoModel> enderecoOptional = enderecoRepository
										.findById(requestedObject.getInterEndereco().getEndId());
								enderecoOptional.ifPresent(endereco -> {
									// Editar attributos como Cidade e Cep.
									EnderecoModel existingEndereco = endereco;
									existingEndereco.setEndLogradouro(requestedObject.getInterEndereco().getEndLogradouro());
									existingEndereco.setEndCidade(requestedObject.getInterEndereco().getEndCidade());
									existingEndereco.setEndCep(requestedObject.getInterEndereco().getEndCep());
									
									EnderecoModel updatedEndereco = enderecoRepository.save(existingEndereco);
									
									record.setInterEndereco(updatedEndereco);
								});

							// Se não houver id, salvar.
							} else {
								
								EnderecoModel newEndereco = new EnderecoModel();
										
										newEndereco.setEndLogradouro(requestedObject.getInterEndereco().getEndLogradouro());
										newEndereco.setEndCidade(requestedObject.getInterEndereco().getEndCidade());
										newEndereco.setEndCep(requestedObject.getInterEndereco().getEndCep());
										
										
										
										enderecoRepository.save(newEndereco);
								
								record.setInterEndereco(newEndereco);
							}
							
						}

			return subterraneaRepository.save(record);
		}).orElse(null);

		if (response == null) {
			throw new NoSuchElementException("Não foi encontrado interferência com o id: " + id);
		}

		return response;
	}
}
