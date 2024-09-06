package com.api.main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.EnderecoModel;
import com.api.main.models.InterferenciaModel;
import com.api.main.models.SituacaoProcessoModel;
import com.api.main.models.SubtipoOutorgaModel;
import com.api.main.models.TipoAtoModel;
import com.api.main.models.TipoInterferenciaModel;
import com.api.main.models.TipoOutorgaModel;
import com.api.main.repositories.EnderecoRepository;
import com.api.main.repositories.InterferenciaRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class InterferenciaService {

	@Autowired
	private InterferenciaRepository interferenciaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

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
				EnderecoModel newEnderecoModel = new EnderecoModel();
				newEnderecoModel.setLogradouro(endereco.getLogradouro());
				newEnderecoModel.setBairro(endereco.getBairro());
				newEnderecoModel.setCidade(endereco.getCidade());
				newEnderecoModel.setCep(endereco.getCep());
				newEnderecoModel.setEstado(endereco.getEstado());
				EnderecoModel savedEnderecoModel = enderecoRepository.save(newEnderecoModel);
				requestedObject.setEndereco(savedEnderecoModel);
			}
		}

		return interferenciaRepository.save(requestedObject);
	}

	/*
	 * @Transactional public List<Object[]> listByLogradouro(String keyword) {
	 * return interferenciaRepository.listByLogradouro(keyword); }
	 */

	public List<InterferenciaModel> listByLogradouro(String keyword) {
		List<Object[]> results = interferenciaRepository.listByLogradouro(keyword);
		List<InterferenciaModel> response = new ArrayList<>();

		if (results == null) {
			System.out.println("No results found for the keyword: " + keyword);
			return response; // Return an empty list if no results
		}

		System.out.println("Total results: " + results.size());

		for (Object[] result : results) {
			if (result == null) {
				System.out.println("Encountered a null result in the list, skipping...");
				continue; // Skip null results
			}

			InterferenciaModel interferencia = new InterferenciaModel();

			// Check for null and process each field
			String interferenciaJson = result[0] != null ? result[0].toString() : null;
			String tipoInterferenciaJson = result[1] != null ? result[1].toString() : null;
			String tipoOutorgaJson = result[2] != null ? result[2].toString() : null;
			String subtipoOutorgaJson = result[3] != null ? result[3].toString() : null;
			String situacaoProcessoJson = result[4] != null ? result[4].toString() : null;
			String tipoAtoJson = result[5] != null ? result[5].toString() : null;
			String enderecoJson = result[6] != null ? result[6].toString() : null;

			// Deserialize each part into its respective model, checking for null values
			if (interferenciaJson != null) {
				Map<String, InterferenciaModel> intMap = new Gson().fromJson(interferenciaJson,
						new TypeToken<Map<String, InterferenciaModel>>() {
						}.getType());
				if (intMap.containsKey("interferencia")) {
					interferencia = intMap.get("interferencia");
				}
			}

			if (tipoInterferenciaJson != null) {
				Map<String, TipoInterferenciaModel> tpMap = new Gson().fromJson(tipoInterferenciaJson,
						new TypeToken<Map<String, TipoInterferenciaModel>>() {
						}.getType());
				if (tpMap.containsKey("tipoInterferencia")) {
					interferencia.setTipoInterferencia(tpMap.get("tipoInterferencia"));
				}
			}

			if (tipoOutorgaJson != null) {
				Map<String, TipoOutorgaModel> toMap = new Gson().fromJson(tipoOutorgaJson,
						new TypeToken<Map<String, TipoOutorgaModel>>() {
						}.getType());
				if (toMap.containsKey("tipoOutorga")) {
					interferencia.setTipoOutorga(toMap.get("tipoOutorga"));
				}
			}

			if (subtipoOutorgaJson != null) {
				Map<String, SubtipoOutorgaModel> soMap = new Gson().fromJson(subtipoOutorgaJson,
						new TypeToken<Map<String, SubtipoOutorgaModel>>() {
						}.getType());
				if (soMap.containsKey("subtipoOutorga")) {
					interferencia.setSubtipoOutorga(soMap.get("subtipoOutorga"));
				}
			}

			if (situacaoProcessoJson != null) {
				Map<String, SituacaoProcessoModel> spMap = new Gson().fromJson(situacaoProcessoJson,
						new TypeToken<Map<String, SituacaoProcessoModel>>() {
						}.getType());
				if (spMap.containsKey("situacaoProcesso")) {
					interferencia.setSituacaoProcesso(spMap.get("situacaoProcesso"));
				}
			}

			if (tipoAtoJson != null) {
				Map<String, TipoAtoModel> taMap = new Gson().fromJson(tipoAtoJson,
						new TypeToken<Map<String, TipoAtoModel>>() {
						}.getType());
				if (taMap.containsKey("tipoAto")) {
					interferencia.setTipoAto(taMap.get("tipoAto"));
				}
			}

			if (enderecoJson != null) {
				Map<String, EnderecoModel> endMap = new Gson().fromJson(enderecoJson,
						new TypeToken<Map<String, EnderecoModel>>() {
						}.getType());
				if (endMap.containsKey("endereco")) {
					interferencia.setEndereco(endMap.get("endereco"));
				}
			}

			response.add(interferencia);
		}

		return response;
	}

	@Transactional
	public InterferenciaModel deleteById(Long id) {
		InterferenciaModel response = interferenciaRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não encontrado, id: " + id));
		interferenciaRepository.deleteById(id);
		return response;
	}

}
