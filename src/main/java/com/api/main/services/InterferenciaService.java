package com.api.main.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.EnderecoModel;
import com.api.main.models.FinalidadeModel;
import com.api.main.models.InterferenciaModel;
import com.api.main.repositories.EnderecoRepository;
import com.api.main.repositories.FinalidadeRepository;
import com.api.main.repositories.InterferenciaRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class InterferenciaService {

	// v1.12.0
	@Autowired
	private InterferenciaRepository interferenciaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private FinalidadeRepository finalidadeRepository;

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
							EnderecoModel existingEnderecoModel = enderecoOptional.get();

							// Atualizar atributos do endereço
							existingEnderecoModel.setLogradouro(endereco.getLogradouro());
							existingEnderecoModel.setBairro(endereco.getBairro());
							existingEnderecoModel.setCidade(endereco.getCidade());
							existingEnderecoModel.setCep(endereco.getCep());
							existingEnderecoModel.setEstado(endereco.getEstado());
							enderecoRepository.save(existingEnderecoModel);
							existingInterferencia.setEndereco(existingEnderecoModel);
						}
					} else {
						// Criar novo endereço
						EnderecoModel newEnderecoModel = new EnderecoModel();
						newEnderecoModel.setLogradouro(endereco.getLogradouro());
						newEnderecoModel.setBairro(endereco.getBairro());
						newEnderecoModel.setCidade(endereco.getCidade());
						newEnderecoModel.setCep(endereco.getCep());
						newEnderecoModel.setEstado(endereco.getEstado());

						EnderecoModel savedEnderecoModel = enderecoRepository.save(newEnderecoModel);
						existingInterferencia.setEndereco(savedEnderecoModel);
					}
				}

				// Atualizar ou criar finalidades
				Set<FinalidadeModel> finalidades = requestedObject.getFinalidades();
				if (finalidades != null && !finalidades.isEmpty()) {
					existingInterferencia.getFinalidades().clear(); // Remove finalidades antigas

					finalidades.forEach(finalidade -> {
						finalidade.setInterferencia(existingInterferencia); // Associa a interferência
						finalidadeRepository.save(finalidade); // Salva ou atualiza a finalidade
					});

					existingInterferencia.getFinalidades().addAll(finalidades); // Adiciona as novas finalidades
				}

				savedInterferencia = interferenciaRepository.save(existingInterferencia);
			} else {

				// Se a interferência não existir, cria uma nova com finalidades
				savedInterferencia = createNewInterferencia(requestedObject);
			}
		} else {
			// Criação de uma nova interferência com finalidades
			savedInterferencia = createNewInterferencia(requestedObject);
		}

		return savedInterferencia;
	}

	private InterferenciaModel createNewInterferencia(InterferenciaModel requestedObject) {

		// Salvar o endereço, se necessário
		EnderecoModel endereco = requestedObject.getEndereco();
		if (endereco != null && endereco.getId() == null) {
			EnderecoModel savedEndereco = enderecoRepository.save(endereco);
			requestedObject.setEndereco(savedEndereco);
		}

		Set<FinalidadeModel> finalidades = requestedObject.getFinalidades();
		// Remove as finalidades da interferência para poder salvá-la.
		// Não é possível salvar com as finalidades, pois o id da interferência na
		// finalidade neste momento ainda está vazio.
		requestedObject.setFinalidades(null);

		// Salvar a interferência antes de associar as finalidades
		InterferenciaModel savedInterferencia = interferenciaRepository.save(requestedObject);

		// Aqui já se tem o id da interferência salva e então salva as finalidades
		if (finalidades != null && !finalidades.isEmpty()) {
			finalidades.forEach(finalidade -> {
				System.out.println("save finalidades inter id " + savedInterferencia.getId());
				// Associar a interferência à finalidade
				finalidade.setInterferencia(savedInterferencia);
				finalidadeRepository.save(finalidade); // Salvar ou atualizar a finalidade
			});
		}

		return savedInterferencia;
	}

	@Transactional
	public Set<InterferenciaModel> listByLogradouro(String keyword) {
		
		System.out.println("Utilizando set");

		Set<InterferenciaModel> response = readJsonStringAndConvert(keyword);
		return response;
	}

	public Set<InterferenciaModel> readJsonStringAndConvert(String keyword) {

		Set<String> result = interferenciaRepository.listByLogradouro(keyword);
		
		Set<InterferenciaModel> response = new HashSet<>();
	

		if (result == null) {
			System.out.println("No results found for the keyword: " + keyword);
			return response; // Return an empty list if no results
		}


		String json = result != null ? result.toString() : null;

		if (json != null) {

			System.out.println("list interferencia by key " + json.toString());
			// Since the structure is a list of objects containing 'endereco', extract them
			Set<Map<String, InterferenciaModel>> tempList = new Gson().fromJson(json,
					new TypeToken<Set<Map<String, InterferenciaModel>>>() {
					}.getType());

			// Iterate over the list and extract 'endereco' object from each map
			for (Map<String, InterferenciaModel> map : tempList) {
				InterferenciaModel obj = map.get("interferencia");
				if (obj != null) {
					response.add(obj);
				}
			}
		}

		return response;

	}

	@Transactional
	public InterferenciaModel deleteById(Long id) {
		InterferenciaModel response = interferenciaRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("{\"info\": \"interferência não encontrada\", \"id\": " + id + "}"));
		interferenciaRepository.deleteById(id);
		return response;
	}

}
