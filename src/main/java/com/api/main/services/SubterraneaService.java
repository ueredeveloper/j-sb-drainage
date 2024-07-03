package com.api.main.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<SubterraneaModel> listByKeyword(String keyword) {
		return subterraneaRepository.listByKeword(keyword);
	}

	@Transactional
	public SubterraneaModel save(SubterraneaModel requestedObject) {
		SubterraneaModel savedSubterranea;

		// Verificar se há interId no objeto solicitado
		if (requestedObject.getInterId() != null) {
			Optional<SubterraneaModel> subterraneaOptional = subterraneaRepository
					.findById(requestedObject.getInterId());
			if (subterraneaOptional.isPresent()) {
				SubterraneaModel existingSubterranea = subterraneaOptional.get();

				// Atualizar atributos da interferência, exceto interId
				existingSubterranea.setInterLatitude(requestedObject.getInterLatitude());
				existingSubterranea.setInterLongitude(requestedObject.getInterLongitude());
				existingSubterranea.setInterGeometry(requestedObject.getInterGeometry());
				existingSubterranea.setInterferenciaTipo(requestedObject.getInterferenciaTipo());
				existingSubterranea.setSubCaesb(requestedObject.getSubCaesb());
				existingSubterranea.setSubNivelEstatico(requestedObject.getSubNivelEstatico());
				existingSubterranea.setSubDinamico(requestedObject.getSubDinamico());

				// Atualizar ou criar endereço conforme necessário
				EnderecoModel endereco = requestedObject.getInterEndereco();
				if (endereco != null) {
					if (endereco.getEndId() != null) {
						Optional<EnderecoModel> enderecoOptional = enderecoRepository.findById(endereco.getEndId());
						if (enderecoOptional.isPresent()) {
							EnderecoModel existingEndereco = enderecoOptional.get();

							// Atualizar atributos do endereço
							existingEndereco.setEndLogradouro(endereco.getEndLogradouro());
							existingEndereco.setEndBairro(endereco.getEndBairro());
							existingEndereco.setEndCidade(endereco.getEndCidade());
							existingEndereco.setEndCep(endereco.getEndCep());
							existingEndereco.setEndEstado(endereco.getEndEstado());

							enderecoRepository.save(existingEndereco);
							existingSubterranea.setInterEndereco(existingEndereco);
						}
					} else {
						// Criar novo endereço
						EnderecoModel newEndereco = new EnderecoModel();

						newEndereco.setEndLogradouro(endereco.getEndLogradouro());
						newEndereco.setEndBairro(endereco.getEndBairro());
						newEndereco.setEndCidade(endereco.getEndCidade());
						newEndereco.setEndCep(endereco.getEndCep());
						newEndereco.setEndEstado(endereco.getEndEstado());

						EnderecoModel savedEndereco = enderecoRepository.save(newEndereco);
						existingSubterranea.setInterEndereco(savedEndereco);
					}
				}

				savedSubterranea = subterraneaRepository.save(existingSubterranea);
			} else {
				savedSubterranea = createNewSubterranea(requestedObject);
			}
		} else {
			savedSubterranea = createNewSubterranea(requestedObject);
		}

		return savedSubterranea;
	}

	private SubterraneaModel createNewSubterranea(SubterraneaModel requestedObject) {
		// Criar novo endereço se necessário
		if (requestedObject.getInterEndereco() != null) {
			EnderecoModel endereco = requestedObject.getInterEndereco();
			if (endereco.getEndId() == null) {
				EnderecoModel newEndereco = new EnderecoModel();
				newEndereco.setEndLogradouro(endereco.getEndLogradouro());
				newEndereco.setEndBairro(endereco.getEndBairro());
				newEndereco.setEndCidade(endereco.getEndCidade());
				newEndereco.setEndCep(endereco.getEndCep());
				newEndereco.setEndEstado(endereco.getEndEstado());

				EnderecoModel savedEndereco = enderecoRepository.save(newEndereco);
				requestedObject.setInterEndereco(savedEndereco);
			}
		}

		return subterraneaRepository.save(requestedObject);
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
						existingEndereco.setEndBairro(requestedObject.getInterEndereco().getEndBairro());
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
