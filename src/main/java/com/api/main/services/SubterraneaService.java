package com.api.main.services;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.EnderecoModel;
import com.api.main.models.FinalidadeModel;
import com.api.main.models.SituacaoProcessoModel;
import com.api.main.models.SubterraneaModel;
import com.api.main.models.SubtipoOutorgaModel;
import com.api.main.models.TipoAtoModel;
import com.api.main.models.TipoFinalidadeModel;
import com.api.main.models.TipoOutorgaModel;
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
				existingSubterranea.setCaesb(requestedObject.getCaesb());
				existingSubterranea.setNivelEstatico(requestedObject.getNivelEstatico());
				existingSubterranea.setNivelDinamico(requestedObject.getNivelDinamico());

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

		return createSafeResponse(savedSubterranea);
	}

	@Transactional
	private SubterraneaModel createNewSubterranea(SubterraneaModel requestedObject) {

		// Salvar o endereço, se necessário
		saveEnderecoIfNeeded(requestedObject);

		// Guardar finalidades temporariamente e limpar no objeto para salvar
		// interferência
		Set<FinalidadeModel> finalidades = requestedObject.getFinalidades();
		requestedObject.setFinalidades(null); // Limpa as finalidades temporariamente

		// Salvar a interferência primeiro
		SubterraneaModel savedInterferencia = subterraneaRepository.save(requestedObject);

		// Salvar finalidades associadas
		saveFinalidades(finalidades, savedInterferencia);

		return savedInterferencia;
	}

	private void saveEnderecoIfNeeded(SubterraneaModel requestedObject) {
		EnderecoModel endereco = requestedObject.getEndereco();
		if (endereco != null && endereco.getId() == null) {
			EnderecoModel savedEndereco = enderecoRepository.save(endereco);
			requestedObject.setEndereco(savedEndereco); // Atualizar o endereço salvo
		}
	}

	private void saveFinalidades(Set<FinalidadeModel> finalidades, SubterraneaModel savedInterferencia) {
		if (finalidades != null && !finalidades.isEmpty()) {

			// Cria um novo Set para armazenar as finalidades salvas
			Set<FinalidadeModel> savedFinalidades = new HashSet<>();

			finalidades.forEach(finalidade -> {
				// Associar a interferência à finalidade
				finalidade.setInterferencia(savedInterferencia);

				// Salvar a finalidade e adicionar ao Set de finalidades salvas
				FinalidadeModel savedFinalidade = finalidadeRepository.save(finalidade);
				savedFinalidades.add(savedFinalidade);
			});

			// Adicionar todas as finalidades salvas ao objeto SubterraneaModel
			savedInterferencia.setFinalidades(savedFinalidades);

			// Atualiza a interferência com as finalidades associadas
			subterraneaRepository.save(savedInterferencia);
		}
	}

	@Transactional
	public SubterraneaModel update(Long id, SubterraneaModel requestedObject) {
		SubterraneaModel originalResponse = subterraneaRepository.findById(id).map((SubterraneaModel record) -> {

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

		if (originalResponse == null) {
			throw new NoSuchElementException("Não foi encontrado interferência com o id: " + id);
		}

		return createSafeResponse(originalResponse);
	}

	public SubterraneaModel createSafeResponse(SubterraneaModel originalResponse) {

		SubterraneaModel safeResponse = new SubterraneaModel();

		safeResponse.setId(originalResponse.getId());
		safeResponse.setLatitude(originalResponse.getLatitude());
		safeResponse.setLongitude(originalResponse.getLongitude());

		// Não permite referências cíclicas, que geram loop na criaçaõ do json
		safeResponse.setEndereco(new EnderecoModel(originalResponse.getEndereco().getId(),
				originalResponse.getEndereco().getLogradouro()));

		safeResponse.setTipoInterferencia(originalResponse.getTipoInterferencia());
		safeResponse.setTipoOutorga(new TipoOutorgaModel(originalResponse.getTipoOutorga().getId(),
				originalResponse.getTipoOutorga().getDescricao()));

		safeResponse.setSubtipoOutorga(
				new SubtipoOutorgaModel(originalResponse.getId(), originalResponse.getSubtipoOutorga().getDescricao()));

		safeResponse.setSituacaoProcesso(new SituacaoProcessoModel(originalResponse.getSituacaoProcesso().getId(),
				originalResponse.getSituacaoProcesso().getDescricao()));

		safeResponse.setTipoAto(
				new TipoAtoModel(originalResponse.getTipoAto().getId(), originalResponse.getTipoAto().getDescricao()));

		Set<FinalidadeModel> finalidades = originalResponse.getFinalidades();

		finalidades.forEach(f -> {

			safeResponse.getFinalidades().add(new FinalidadeModel(f.getId(), f.getFinalidade(), f.getSubfinalidade(),
					f.getQuantidade(), f.getConsumo(), f.getTotal(),
					new TipoFinalidadeModel(f.getTipoFinalidade().getId(), f.getTipoFinalidade().getDescricao())));
		});

		return safeResponse;
	}

	@Transactional
	public SubterraneaModel deleteById(Long id) {
		SubterraneaModel deleteResponse = subterraneaRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado o id: " + id));

		subterraneaRepository.deleteById(id);
		return deleteResponse;
	}
}
