package com.api.main.services;

import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.dto.DTDemandaDTO;
import com.api.main.models.BaciaHidrograficaModel;
import com.api.main.models.EnderecoModel;
import com.api.main.models.FinalidadeModel;
import com.api.main.models.InterferenciaModel;
import com.api.main.models.InterferenciaTypeAdapter;
import com.api.main.models.SubterraneaModel;
import com.api.main.models.UnidadeHidrograficaModel;
import com.api.main.repositories.BaciaHidrograficaRepository;
import com.api.main.repositories.EnderecoRepository;
import com.api.main.repositories.FinalidadeRepository;
import com.api.main.repositories.InterferenciaRepository;
import com.api.main.repositories.UnidadeHidrograficaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
	@Autowired
	private BaciaHidrograficaRepository baciaRepository;
	@Autowired
	private UnidadeHidrograficaRepository unidadeRepository;

	@Autowired
	private ObjectMapper objectMapper;

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

				// Cria uma fábrica de geometrias
				GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4674);

				// Cria um ponto com as coordenadas fornecidas
				Point point = geometryFactory
						.createPoint(new Coordinate(requestedObject.getLongitude(), requestedObject.getLatitude()));

				existingInterferencia.setGeometry(point);
				System.out.println("save interferencia service, if id != null ");

				if (requestedObject.getBaciaHidrografica() != null) {
					Long objectId = requestedObject.getBaciaHidrografica().getObjectid();
					Optional<BaciaHidrograficaModel> baciaOpt = baciaRepository.findById(objectId);

					baciaOpt.ifPresent(requestedObject::setBaciaHidrografica);
				}

				if (requestedObject.getUnidadeHidrografica() != null) {
					Long unidadeId = requestedObject.getUnidadeHidrografica().getObjectid();
					Optional<UnidadeHidrograficaModel> unidadeOpt = unidadeRepository.findById(unidadeId);
					unidadeOpt.ifPresent(requestedObject::setUnidadeHidrografica);
				}

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

			System.out.println("save interferencia service, if id == null ");
			// Criação de uma nova interferência com finalidades
			savedInterferencia = createNewInterferencia(requestedObject);
		}

		return savedInterferencia;
	}

	private InterferenciaModel createNewInterferencia(InterferenciaModel requestedObject) {

		if (requestedObject.getBaciaHidrografica() != null) {
			Long objectId = requestedObject.getBaciaHidrografica().getObjectid();
			Optional<BaciaHidrograficaModel> baciaOpt = baciaRepository.findById(objectId);

			baciaOpt.ifPresent(requestedObject::setBaciaHidrografica);
		}

		if (requestedObject.getUnidadeHidrografica() != null) {
			Long unidadeId = requestedObject.getUnidadeHidrografica().getObjectid();
			Optional<UnidadeHidrograficaModel> unidadeOpt = unidadeRepository.findById(unidadeId);
			unidadeOpt.ifPresent(requestedObject::setUnidadeHidrografica);
		}

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

		System.out.println("int service, create new interferencia ");

		// Salva coordenadas em formato geometry
		GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4674);

		// Cria um ponto com as coordenadas fornecidas
		Point point = geometryFactory
				.createPoint(new Coordinate(requestedObject.getLongitude(), requestedObject.getLatitude()));

		requestedObject.setGeometry(point);

		// Salvar a interferência antes de associar as finalidades
		InterferenciaModel savedInterferencia = interferenciaRepository.save(requestedObject);

		// Aqui já se tem o id da interferência salva e então salva as finalidades
		if (finalidades != null && !finalidades.isEmpty()) {
			finalidades.forEach(finalidade -> {
				// Associar a interferência à finalidade
				finalidade.setInterferencia(savedInterferencia);
				finalidadeRepository.save(finalidade); // Salvar ou atualizar a finalidade
			});
		}

		return savedInterferencia;
	}

	@Transactional
	public Set<InterferenciaModel> listByLogradouro(String keyword) {
		Set<InterferenciaModel> response = readJsonStringAndConvert(keyword);
		return response;
	}

	public Set<InterferenciaModel> readJsonStringAndConvert(String keyword) {

		Set<String> result = interferenciaRepository.listByLogradouro(keyword);
		Set<InterferenciaModel> response = new HashSet<>();

		if (result == null) {
			return response; // Return an empty list if no results
		}

		String json = result.toString();

		// System.out.println(json);

		if (json != null && !json.isEmpty()) {
			Gson gson = new GsonBuilder().registerTypeAdapter(InterferenciaModel.class, new InterferenciaTypeAdapter())
					// Registrar o adaptador
					.create();
			// Since the structure is a list of objects containing 'interferencia', extract
			// them
			Set<Map<String, InterferenciaModel>> tempList = gson.fromJson(json,
					new TypeToken<Set<Map<String, InterferenciaModel>>>() {
					}.getType());

			// Iterate over the list and extract 'interferencia' object from each map
			for (Map<String, InterferenciaModel> map : tempList) {
				InterferenciaModel obj = map.get("interferencia");
				if (obj != null) {
					// Verifica se o objeto é do tipo SubterraneaModel e exibe a vazão
					if (obj instanceof SubterraneaModel) {
						SubterraneaModel subterranea = (SubterraneaModel) obj;
					}
					response.add(obj);
				}
			}
		}

		return response;
	}

	@Transactional
	public InterferenciaModel deleteById(Long id) {
		// Retrieve the entity by ID or throw an exception if not found
		InterferenciaModel response = interferenciaRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
				String.format("{\"info\": \"interferência não encontrada\", \"id\": %d}", id)));

		// Perform the deletion
		interferenciaRepository.deleteById(id);

		// Return a safe response object
		return createDeleteSafeResponse(response);
	}

	public InterferenciaModel createDeleteSafeResponse(InterferenciaModel originalResponse) {

		InterferenciaModel safeResponse = new InterferenciaModel();

		safeResponse.setId(originalResponse.getId());
		safeResponse.setLatitude(originalResponse.getLatitude());
		safeResponse.setLongitude(originalResponse.getLongitude());

		return safeResponse;
	}

	public Set<DTDemandaDTO> getDemandaByAddressId(Long endId) {
		// Recuperando os dados brutos da consulta
		Set<Object[]> rawResults = interferenciaRepository.listByAddressId(endId);
		Set<DTDemandaDTO> result = new HashSet<>();

		// Iterando pelos resultados e mapeando para o DTO
		for (Object[] row : rawResults) {

			DTDemandaDTO dto = new DTDemandaDTO();

			// Mapeando os campos da consulta para o DTO
			dto.setInt_id(((Number) row[0]).longValue());
			dto.setEnd_id(((Number) row[1]).longValue());
			dto.setEnd_logradouro((String) row[2]);
			dto.setInt_latitude((Double) row[3]);
			dto.setInt_longitude((Double) row[4]);
			dto.setSub_tp_id(((Number) row[5]).longValue());

			String jsonString = (String) row[6];
			if (jsonString != null) {
				try {
					// Converte a String JSON para JsonNode
					JsonNode jsonNode = objectMapper.readTree(jsonString);
					dto.setDt_demanda(jsonNode);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
			
			dto.setVol_anual_ma(((Number) row[7]).doubleValue());
			// Adicionando o DTO ao conjunto de resultados
			result.add(dto);
		}

		return result;
	}

}
