package com.api.main.services;

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
public class InterferenciaService {

	@Autowired
	private InterferenciaRepository interferenciaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Transactional
	public List<InterferenciaModel> listByKeword(String keyword) {
		return interferenciaRepository.listByKeword(keyword);
	}

	@Transactional
	public InterferenciaModel save(InterferenciaModel requestedObject) {
		InterferenciaModel savedInterferencia;

		// Verificar se há interId no objeto de interferência solicitado
		if (requestedObject.getInterId() != null) {
			Optional<InterferenciaModel> interferenciaOptional = interferenciaRepository
					.findById(requestedObject.getInterId());
			if (interferenciaOptional.isPresent()) {
				InterferenciaModel existingInterferencia = interferenciaOptional.get();

				// Atualizar atributos da interferência, exceto interId
				existingInterferencia.setInterLatitude(requestedObject.getInterLatitude());
				existingInterferencia.setInterLongitude(requestedObject.getInterLongitude());
				existingInterferencia.setInterGeometry(requestedObject.getInterGeometry());
				existingInterferencia.setInterferenciaTipo(requestedObject.getInterferenciaTipo());

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
							existingInterferencia.setInterEndereco(existingEndereco);
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
						existingInterferencia.setInterEndereco(savedEndereco);
					}
				}

				savedInterferencia = interferenciaRepository.save(existingInterferencia);
			} else {
				savedInterferencia = createNewInterferencia(requestedObject);
			}
		} else {
			savedInterferencia = createNewInterferencia(requestedObject);
		}

		return savedInterferencia;
	}

	private InterferenciaModel createNewInterferencia(InterferenciaModel requestedObject) {
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

		return interferenciaRepository.save(requestedObject);
	}

	@Transactional
	public List<InterferenciaModel> listByLogradouro(String keyword) {
		return interferenciaRepository.listByLogradouro(keyword);
	}

	@Transactional
	public InterferenciaModel deleteById(Long id) {
		InterferenciaModel response = interferenciaRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não encontrado, id: " + id));
		interferenciaRepository.deleteById(id);
		return response;
	}

}
