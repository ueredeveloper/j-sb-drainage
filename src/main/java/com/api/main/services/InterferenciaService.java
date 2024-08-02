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
		if (requestedObject.getId() != null) {
			Optional<InterferenciaModel> interferenciaOptional = interferenciaRepository
					.findById(requestedObject.getId());
			if (interferenciaOptional.isPresent()) {
				InterferenciaModel existingInterferencia = interferenciaOptional.get();

				// Atualizar atributos da interferência, exceto interId
				existingInterferencia.setLatitude(requestedObject.getLatitude());
				existingInterferencia.setLongitude(requestedObject.getLongitude());
				existingInterferencia.setGeometry(requestedObject.getGeometry());
				existingInterferencia.setTipoInterferencia(requestedObject.getTipoInterferencia());

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
							existingInterferencia.setEndereco(existingEndereco);
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
						existingInterferencia.setEndereco(savedEndereco);
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
