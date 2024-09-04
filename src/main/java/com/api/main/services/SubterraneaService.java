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
		if (requestedObject.getId() != null) {
			
			Optional<SubterraneaModel> subterraneaOptional = subterraneaRepository.findById(requestedObject.getId());
			
			if (subterraneaOptional.isPresent()) {
				
				SubterraneaModel existingSubterranea = subterraneaOptional.get();

				// Atualizar atributos da interferência, exceto interId
				existingSubterranea.setLatitude(requestedObject.getLatitude());
				existingSubterranea.setLongitude(requestedObject.getLongitude());
				existingSubterranea.setGeometry(requestedObject.getGeometry());
				existingSubterranea.setTipoInterferencia(requestedObject.getTipoInterferencia());
				existingSubterranea.setTipoOutorga(requestedObject.getTipoOutorga());
				existingSubterranea.setSubtipoOutorga(requestedObject.getSubtipoOutorga());
				existingSubterranea.setSituacaoProcesso(requestedObject.getSituacaoProcesso());
				existingSubterranea.setTipoAto(requestedObject.getTipoAto());
				existingSubterranea.setSubCaesb(requestedObject.getSubCaesb());
				existingSubterranea.setSubNivelEstatico(requestedObject.getSubNivelEstatico());
				existingSubterranea.setSubDinamico(requestedObject.getSubDinamico());

				// Atualizar ou criar endereço conforme necessário
				EnderecoModel endereco = requestedObject.getEndereco();
				if (endereco != null) {
					if (endereco.getId() != null) {
						Optional<EnderecoModel> enderecoOptional = enderecoRepository.findById(endereco.getId());
						if (enderecoOptional.isPresent()) {
							EnderecoModel existingEndereco = enderecoOptional.get();

							// Atualizar atributos do endereço
							existingEndereco.setLogradouro(endereco.getLogradouro());
							existingEndereco.setBairro(endereco.getBairro());
							existingEndereco.setCidade(endereco.getCidade());
							existingEndereco.setCep(endereco.getCep());
							existingEndereco.setEstado(endereco.getEstado());

							enderecoRepository.save(existingEndereco);
							existingSubterranea.setEndereco(existingEndereco);
						}
					} else {
						// Criar novo endereço
						EnderecoModel newEndereco = new EnderecoModel();

						newEndereco.setLogradouro(endereco.getLogradouro());
						newEndereco.setBairro(endereco.getBairro());
						newEndereco.setCidade(endereco.getCidade());
						newEndereco.setCep(endereco.getCep());
						newEndereco.setEstado(endereco.getEstado());

						EnderecoModel savedEndereco = enderecoRepository.save(newEndereco);
						existingSubterranea.setEndereco(savedEndereco);
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
		if (requestedObject.getEndereco() != null) {
			EnderecoModel endereco = requestedObject.getEndereco();
			if (endereco.getId() == null) {
				EnderecoModel newEndereco = new EnderecoModel();
				newEndereco.setLogradouro(endereco.getLogradouro());
				newEndereco.setBairro(endereco.getBairro());
				newEndereco.setCidade(endereco.getCidade());
				newEndereco.setCep(endereco.getCep());
				newEndereco.setEstado(endereco.getEstado());

				EnderecoModel savedEndereco = enderecoRepository.save(newEndereco);
				requestedObject.setEndereco(savedEndereco);
			}
		}

		return subterraneaRepository.save(requestedObject);
	}

	@Transactional
	public SubterraneaModel update(Long id, SubterraneaModel requestedObject) {
		SubterraneaModel response = subterraneaRepository.findById(id).map((SubterraneaModel record) -> {

			record.setLatitude(requestedObject.getLatitude());
			record.setLongitude(requestedObject.getLongitude());
			record.setTipoInterferencia(requestedObject.getTipoInterferencia());

			// Se houver endereço preenchido
			if (requestedObject.getEndereco() != null) {

				// Se houver id, editar.
				if (requestedObject.getEndereco().getId() != null) {
					Optional<EnderecoModel> enderecoOptional = enderecoRepository
							.findById(requestedObject.getEndereco().getId());
					enderecoOptional.ifPresent(endereco -> {
						// Editar attributos como Cidade e Cep.
						EnderecoModel existingEndereco = endereco;
						existingEndereco.setLogradouro(requestedObject.getEndereco().getLogradouro());
						existingEndereco.setBairro(requestedObject.getEndereco().getBairro());
						existingEndereco.setCidade(requestedObject.getEndereco().getCidade());
						existingEndereco.setCep(requestedObject.getEndereco().getCep());

						EnderecoModel updatedEndereco = enderecoRepository.save(existingEndereco);

						record.setEndereco(updatedEndereco);
					});

					// Se não houver id, salvar.
				} else {

					EnderecoModel newEndereco = new EnderecoModel();

					newEndereco.setLogradouro(requestedObject.getEndereco().getLogradouro());
					newEndereco.setCidade(requestedObject.getEndereco().getCidade());
					newEndereco.setCep(requestedObject.getEndereco().getCep());

					enderecoRepository.save(newEndereco);

					record.setEndereco(newEndereco);
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
