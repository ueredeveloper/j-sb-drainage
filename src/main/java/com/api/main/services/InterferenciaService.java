package com.api.main.services;

import java.util.ArrayList;
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
import com.api.main.models.SituacaoProcessoModel;
import com.api.main.models.SubtipoOutorgaModel;
import com.api.main.models.TipoAtoModel;
import com.api.main.models.TipoInterferenciaModel;
import com.api.main.models.TipoOutorgaModel;
import com.api.main.repositories.EnderecoRepository;
import com.api.main.repositories.FinalidadeRepository;
import com.api.main.repositories.InterferenciaRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class InterferenciaService {

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
	public List<InterferenciaModel> listByLogradouro(String keyword) {
		List<Object[]> results = interferenciaRepository.listByLogradouro(keyword);
		List<InterferenciaModel> response = new ArrayList<>();

		if (results == null) {
			System.out.println("No results found for the keyword: " + keyword);
			return response; // Return an empty list if no results
		}

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
		InterferenciaModel response = interferenciaRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("{\"info\": \"interferência não encontrada\", \"id\": " + id + "}"));
		interferenciaRepository.deleteById(id);
		return response;
	}

}
