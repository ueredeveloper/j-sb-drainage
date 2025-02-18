package com.api.main.services;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.DemandaModel;
import com.api.main.models.EnderecoModel;
import com.api.main.models.FinalidadeModel;
import com.api.main.models.SituacaoProcessoModel;
import com.api.main.models.SubterraneaModel;
import com.api.main.models.SubtipoOutorgaModel;
import com.api.main.models.TipoAtoModel;
import com.api.main.models.TipoFinalidadeModel;
import com.api.main.models.TipoOutorgaModel;
import com.api.main.repositories.DemandaRepository;
import com.api.main.repositories.EnderecoRepository;
import com.api.main.repositories.FinalidadeRepository;
import com.api.main.repositories.SubterraneaRepository;

@Service
public class SubterraneaService {

	
	
	@Autowired
	private SubterraneaRepository subterraneaRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private FinalidadeRepository finalidadeRepository;

	@Autowired
	private DemandaRepository demandaRepository;

	@Transactional
	public SubterraneaModel save(SubterraneaModel requestedObject) {

		SubterraneaModel savedSubterranea;

		// Verificar se há interId no objeto solicitado
		if (requestedObject.getId() != null) {

			Optional<SubterraneaModel> subterraneaOptional = subterraneaRepository.findById(requestedObject.getId());

			if (subterraneaOptional.isPresent()) {

				SubterraneaModel existingSubterranea = subterraneaOptional.get();

				// Atualizar atributos da interferência, exceto interId
				updateSubterraneaAttributes(existingSubterranea, requestedObject);

				// Atualizar ou criar endereço conforme necessário
				saveOrUpdateEndereco(requestedObject);

				savedSubterranea = subterraneaRepository.save(existingSubterranea);
			} else {
				savedSubterranea = createNewSubterranea(requestedObject);
			}
		} else {
			savedSubterranea = createNewSubterranea(requestedObject);
		}

		return createSafeResponse(savedSubterranea);
	}

	private void updateSubterraneaAttributes(SubterraneaModel existingSubterranea, SubterraneaModel requestedObject) {

		existingSubterranea.setLatitude(requestedObject.getLatitude());
		existingSubterranea.setLongitude(requestedObject.getLongitude());

		// Salva coordenadas em formato geometry
		GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4674);

		// Cria um ponto com as coordenadas fornecidas
		Point point = geometryFactory
				.createPoint(new Coordinate(requestedObject.getLongitude(), requestedObject.getLatitude()));

		existingSubterranea.setGeometry(point);

		existingSubterranea.setCaesb(requestedObject.getCaesb());
		existingSubterranea.setNivelEstatico(requestedObject.getNivelEstatico());
		existingSubterranea.setNivelDinamico(requestedObject.getNivelDinamico());
		existingSubterranea.setProfundidade(requestedObject.getProfundidade());
		// Tipo Poço
		existingSubterranea.setTipoPoco(requestedObject.getTipoPoco());
		existingSubterranea.setVazaoOutorgavel(requestedObject.getVazaoOutorgavel());
		existingSubterranea.setVazaoTeste(requestedObject.getVazaoTeste());
		existingSubterranea.setVazaoSistema(requestedObject.getVazaoSistema());

		// existingSubterranea.setGeometry(requestedObject.getGeometry());
		// Relacionamentos
		existingSubterranea.setTipoInterferencia(requestedObject.getTipoInterferencia());
		existingSubterranea.setTipoOutorga(requestedObject.getTipoOutorga());
		existingSubterranea.setSubtipoOutorga(requestedObject.getSubtipoOutorga());
		existingSubterranea.setSituacaoProcesso(requestedObject.getSituacaoProcesso());
		existingSubterranea.setTipoAto(requestedObject.getTipoAto());

		existingSubterranea.setCodPlan(requestedObject.getCodPlan());
		existingSubterranea.setSistema(requestedObject.getSistema());
		existingSubterranea.setSubsistema(requestedObject.getSubsistema());

		// Guardar finalidades temporariamente e limpar no objeto para salvar
		// interferência
		Set<FinalidadeModel> finalidades = requestedObject.getFinalidades();
		requestedObject.setFinalidades(new HashSet<>()); // Limpa as finalidades temporariamente

		Set<DemandaModel> demandas = requestedObject.getDemandas();
		requestedObject.setDemandas(new HashSet<>());

		// Salvar finalidades associadas
		saveOrUpdateFinalidades(finalidades, requestedObject);

		saveOrUpdateDemandas(demandas, requestedObject);
	}

	@Transactional
	private SubterraneaModel createNewSubterranea(SubterraneaModel requestedObject) {

		// Salvar o endereço, se necessário
		saveOrUpdateEndereco(requestedObject);

		// Guardar finalidades temporariamente, organizar e limpar no objeto para salvar
		List<FinalidadeModel> finalidades = requestedObject.getFinalidades().stream()
				
				// Ordena as finalidades de acordo com a prioridade desejada:
				// 1. "Abastecimento Humano" vem primeiro
				// 2. "Criação de Animais" vem em segundo
				// 3. Outras finalidades ficam no final
				.sorted(Comparator.comparingInt(f -> {

					String purpouse = f.getFinalidade().toLowerCase();
					
					// Converte para minúsculo e compara. Assim aceita tanto Abastecimento como
					// abastecimento na comparação
					if (purpouse.contains(("aba")))
						return 1;
					if (purpouse.contains(("cri")))
						return 2;
					return 3; // Outras finalidades
				})).collect(Collectors.toList());


		Set<FinalidadeModel> sortedFinalidades = new LinkedHashSet<>();
		
		sortedFinalidades.addAll(finalidades);
		
		requestedObject.setFinalidades(new HashSet<>()); // Limpa as finalidades temporariamente

		Set<DemandaModel> demandas = requestedObject.getDemandas();
		
		requestedObject.setDemandas(new HashSet<>());

		// Salva coordenadas em formato geometry
		GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4674);
		
		System.out.println("sub serv create new lat " + requestedObject.getLatitude());

		// Cria um ponto com as coordenadas fornecidas
		Point point = geometryFactory
				.createPoint(new Coordinate(requestedObject.getLongitude(), requestedObject.getLatitude()));

		requestedObject.setGeometry(point);


		// Salvar a interferência primeiro
		SubterraneaModel savedInterferencia = subterraneaRepository.save(requestedObject);

		// Salvar finalidades associadas
		saveOrUpdateFinalidades(sortedFinalidades, savedInterferencia);

		saveOrUpdateDemandas(demandas, savedInterferencia);

		return savedInterferencia;
	}

	private void saveOrUpdateEndereco(SubterraneaModel requestedObject) {
		EnderecoModel endereco = requestedObject.getEndereco();

		if (endereco == null)
			return;

		if (endereco.getId() == null) {
			saveNewEndereco(requestedObject, endereco);
		} else {
			updateExistingEndereco(requestedObject, endereco);
		}
	}

	private void saveNewEndereco(SubterraneaModel requestedObject, EnderecoModel endereco) {
		EnderecoModel savedEndereco = enderecoRepository.save(endereco);
		requestedObject.setEndereco(savedEndereco); // Atualizar o endereço salvo
	}

	@Transactional
	private void updateExistingEndereco(SubterraneaModel requestedObject, EnderecoModel endereco) {
		enderecoRepository.findById(endereco.getId()).ifPresent(existingEndereco -> {
			updateEnderecoAttributes(existingEndereco, endereco);
			EnderecoModel updatedEndereco = enderecoRepository.save(existingEndereco);
			requestedObject.setEndereco(updatedEndereco);
		});
	}

	private void updateEnderecoAttributes(EnderecoModel existingEndereco, EnderecoModel newEndereco) {
		existingEndereco.setLogradouro(newEndereco.getLogradouro());
		existingEndereco.setBairro(newEndereco.getBairro());
		existingEndereco.setCidade(newEndereco.getCidade());
		existingEndereco.setCep(newEndereco.getCep());
	}

	@Transactional
	public SubterraneaModel update(Long id, SubterraneaModel requestedObject) {

		System.out.println("sub service, udpate  ");

		SubterraneaModel originalResponse = subterraneaRepository.findById(id).map((SubterraneaModel record) -> {

			// Atualizar atributos da interferência
			updateSubterraneaAttributes(record, requestedObject);

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
						// ADICIONAR ESTADO NA EDIÇÃO
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
			
			// Salva coordenadas em formato geometry
			GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4674);

			// Cria um ponto com as coordenadas fornecidas
			Point point = geometryFactory
					.createPoint(new Coordinate(requestedObject.getLongitude(), requestedObject.getLatitude()));

			record.setGeometry(point);

			SubterraneaModel persistedSubterranea = subterraneaRepository.save(record);

			Set<FinalidadeModel> finalidades = requestedObject.getFinalidades();
			if (finalidades != null && !finalidades.isEmpty()) {

				finalidades.forEach(item -> {

					// Finalidade sem id, salva e relaciona com a interferência
					if (item.getId() == null) {
						item.setInterferencia(persistedSubterranea);
						FinalidadeModel savedFinalidade = finalidadeRepository.save(item);
						record.getFinalidades().add(savedFinalidade);

						// Se tem id, apenas edita
					} else {
						item.setInterferencia(record);
						finalidadeRepository.save(item);
					}
				});

			}

			// Salvar as mudanças no banco de dados
			return persistedSubterranea;
		}).orElse(null);

		if (originalResponse == null) {
			throw new NoSuchElementException("Não foi encontrada interferência com o id: " + id);
		}

		return createSafeResponse(originalResponse);
	}

	public SubterraneaModel createSafeResponse(SubterraneaModel originalResponse) {

		SubterraneaModel safeResponse = new SubterraneaModel();

		safeResponse.setId(originalResponse.getId());
		safeResponse.setLatitude(originalResponse.getLatitude());
		safeResponse.setLongitude(originalResponse.getLongitude());
		safeResponse.setCaesb(originalResponse.getCaesb());
		// Dados do Poço
		safeResponse.setNivelEstatico(originalResponse.getNivelEstatico());
		safeResponse.setNivelDinamico(originalResponse.getNivelDinamico());
		safeResponse.setProfundidade(originalResponse.getProfundidade());

		safeResponse.setTipoPoco(originalResponse.getTipoPoco());
		// Vazões
		safeResponse.setVazaoOutorgavel(originalResponse.getVazaoOutorgavel());
		safeResponse.setVazaoTeste(originalResponse.getVazaoTeste());
		safeResponse.setVazaoSistema(originalResponse.getVazaoSistema());

		// Não permite referências cíclicas, que geram loop na criaçaõ do json
		safeResponse.setEndereco(new EnderecoModel(originalResponse.getEndereco().getId(),
				originalResponse.getEndereco().getLogradouro()));

		safeResponse.setTipoInterferencia(originalResponse.getTipoInterferencia());
		safeResponse.setTipoOutorga(new TipoOutorgaModel(originalResponse.getTipoOutorga().getId(),
				originalResponse.getTipoOutorga().getDescricao()));

		safeResponse.setSubtipoOutorga(new SubtipoOutorgaModel(originalResponse.getSubtipoOutorga().getId(),
				originalResponse.getSubtipoOutorga().getDescricao()));

		safeResponse.setSituacaoProcesso(new SituacaoProcessoModel(originalResponse.getSituacaoProcesso().getId(),
				originalResponse.getSituacaoProcesso().getDescricao()));

		safeResponse.setTipoAto(
				new TipoAtoModel(originalResponse.getTipoAto().getId(), originalResponse.getTipoAto().getDescricao()));

		Set<FinalidadeModel> finalidades = originalResponse.getFinalidades();
		Set<DemandaModel> demandas = originalResponse.getDemandas();

		if (finalidades != null) {
			finalidades.forEach(item -> {

				safeResponse.getFinalidades()
						.add(new FinalidadeModel(item.getId(), item.getFinalidade(), item.getSubfinalidade(),
								item.getQuantidade(), item.getConsumo(), item.getTotal(), new TipoFinalidadeModel(
										item.getTipoFinalidade().getId(), item.getTipoFinalidade().getDescricao())));
			});

		}
		if (demandas != null) {
			demandas.forEach(item -> {

				safeResponse.getDemandas()
						.add(new DemandaModel(item.getId(), item.getVazao(), item.getTempo(), item.getPeriodo(),
								item.getMes(), new TipoFinalidadeModel(item.getTipoFinalidade().getId(),
										item.getTipoFinalidade().getDescricao())));
			});

		}

		return safeResponse;
	}

	@Transactional
	public SubterraneaModel deleteById(Long id) {
		SubterraneaModel deleteResponse = subterraneaRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado o id: " + id));

		subterraneaRepository.deleteById(id);
		return deleteResponse;
	}

	@Transactional
	public void saveOrUpdateDemandas(Set<DemandaModel> demandas, SubterraneaModel requestedObject) {

		for (DemandaModel requestedDemanda : demandas) {
			DemandaModel savedDemanda;

			// Verificar se há id no objeto solicitado
			if (requestedDemanda.getId() != null) {
				Optional<DemandaModel> demandaOptional = demandaRepository.findById(requestedDemanda.getId());

				if (demandaOptional.isPresent()) {
					DemandaModel existingDemanda = demandaOptional.get();

					// Atualizar atributos da demanda existente
					existingDemanda.setVazao(requestedDemanda.getVazao());
					existingDemanda.setTempo(requestedDemanda.getTempo());
					existingDemanda.setPeriodo(requestedDemanda.getPeriodo());
					existingDemanda.setMes(requestedDemanda.getMes());

					/*
					 * if (requestedDemanda.getTipoFinalidade() != null) {
					 * existingDemanda.setTipoFinalidade(requestedDemanda.getTipoFinalidade()); }
					 */

					// Verificar se é necessário atualizar a interferência associada
					if (requestedDemanda.getInterferencia() != null) {
						existingDemanda.setInterferencia(requestedDemanda.getInterferencia());
					}

					savedDemanda = demandaRepository.save(existingDemanda);
				} else {
					savedDemanda = createNewDemanda(requestedDemanda, requestedObject);
				}
			} else {
				savedDemanda = createNewDemanda(requestedDemanda, requestedObject);
			}

			// Add saved demand to the subterranea model's demand set
			requestedObject.getDemandas().add(savedDemanda);
		}

		// Optionally save the SubterraneaModel if needed
		subterraneaRepository.save(requestedObject);
	}

	@Transactional
	public void saveOrUpdateFinalidades(Set<FinalidadeModel> finalidades, SubterraneaModel requestedObject) {

		for (FinalidadeModel requested : finalidades) {
			FinalidadeModel saved;

			// Verificar se há id no objeto solicitado
			if (requested.getId() != null) {
				Optional<FinalidadeModel> optional = finalidadeRepository.findById(requested.getId());

				if (optional.isPresent()) {
					FinalidadeModel existing = optional.get();

					// Atualizar atributos da demanda existente
					existing.setFinalidade(requested.getFinalidade());
					existing.setSubfinalidade(requested.getSubfinalidade());
					existing.setQuantidade(requested.getQuantidade());
					existing.setConsumo(requested.getConsumo());
					existing.setTotal(requested.getTotal());

					// Verificar se é necessário atualizar a interferência associada
					if (requested.getInterferencia() != null) {
						existing.setInterferencia(requested.getInterferencia());
					}

					saved = finalidadeRepository.save(existing);
				} else {
					saved = createNewFinalidade(requested, requestedObject);
				}
			} else {
				saved = createNewFinalidade(requested, requestedObject);
			}

			// Add saved demand to the subterranea model's demand set
			requestedObject.getFinalidades().add(saved);
		}

		// Optionally save the SubterraneaModel if needed
		subterraneaRepository.save(requestedObject);
	}

	private DemandaModel createNewDemanda(DemandaModel demanda, SubterraneaModel interferencia) {
		// Associar a interferência (SubterraneaModel) à nova demanda
		demanda.setInterferencia(interferencia);
		return demandaRepository.save(demanda);
	}

	private FinalidadeModel createNewFinalidade(FinalidadeModel toSave, SubterraneaModel interferencia) {

		// Associar a interferência (SubterraneaModel) à nova demanda
		toSave.setInterferencia(interferencia);
		return finalidadeRepository.save(toSave);
	}

}
