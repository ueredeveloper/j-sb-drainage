package com.api.main.services;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.AnexoModel;
import com.api.main.models.ProcessoModel;
import com.api.main.models.UsuarioModel;
import com.api.main.repositories.AnexoRepository;
import com.api.main.repositories.ProcessoRepository;
import com.api.main.repositories.UsuarioRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository processoRepository;

	@Autowired
	private AnexoRepository anexoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public ProcessoModel save(ProcessoModel objMod) {
		// Verifica se o anexo não tem ID (precisa ser salvo primeiro)
		// Check if Anexo needs to be saved or updated

		AnexoModel anexo = null;
		UsuarioModel usuario = null;

		if (objMod.getAnexo() != null) {
			if (objMod.getAnexo().getId() == null) {
				// Save new Anexo if ID is null
				anexo = anexoRepository.save(objMod.getAnexo());
				objMod.setAnexo(anexo);
			} else {
				// Update existing Anexo if ID is present
				AnexoModel existingAnexo = anexoRepository.findById(objMod.getAnexo().getId())
						.orElseThrow(() -> new EntityNotFoundException(
								"Anexo with ID " + objMod.getAnexo().getId() + " not found."));
				existingAnexo.setNumero(objMod.getAnexo().getNumero());
				anexo = anexoRepository.save(existingAnexo);
				objMod.setAnexo(anexo);
			}
		}

		// Check if Usuario needs to be saved or updated
		if (objMod.getUsuario() != null) {
			if (objMod.getUsuario().getId() == null) {
				// Save new Usuario if ID is null
				usuario = usuarioRepository.save(objMod.getUsuario());
				objMod.setUsuario(usuario);
			} else {
				// Update existing Usuario if ID is present
				UsuarioModel existingUsuario = usuarioRepository.findById(objMod.getUsuario().getId())
						.orElseThrow(() -> new EntityNotFoundException(
								"Usuario with ID " + objMod.getUsuario().getId() + " not found."));
				existingUsuario.setNome(objMod.getUsuario().getNome());
				existingUsuario.setCpfCnpj(objMod.getUsuario().getCpfCnpj());

				usuario = usuarioRepository.save(existingUsuario);
				objMod.setUsuario(usuario);
			}
		}

		// Check if Usuario needs to be saved or updated

		if (objMod.getId() == null) {
			// Save new Usuario if ID is null
			ProcessoModel originalResponse = processoRepository.save(objMod);
			return createSafeResponse(originalResponse);
		} else {
			// Update existing Usuario if ID is present
			ProcessoModel existingProcesso = processoRepository.findById(objMod.getId())
					.orElseThrow(() -> new EntityNotFoundException("Processo Id " + objMod.getId() + " not found."));

			existingProcesso.setNumero(objMod.getNumero());
			existingProcesso.setAnexo(anexo);
			existingProcesso.setUsuario(usuario);

			processoRepository.save(existingProcesso);
			return createSafeResponse(existingProcesso);

		}

	}

	@Transactional
	public ProcessoModel update(Long id, ProcessoModel updateProcesso) {
		Optional<ProcessoModel> optionalProcesso = processoRepository.findById(id);

		if (optionalProcesso.isPresent()) {
			ProcessoModel existingProcesso = optionalProcesso.get();

			// Update processo attributes
			existingProcesso.setNumero(updateProcesso.getNumero());

			// Check if Anexo needs to be saved or updated
			if (updateProcesso.getAnexo() != null) {
				if (updateProcesso.getAnexo().getId() == null) {
					// Save new Anexo if ID is null
					AnexoModel anexo = anexoRepository.save(updateProcesso.getAnexo());
					existingProcesso.setAnexo(anexo);
				} else {
					// Update existing Anexo if ID is present
					AnexoModel existingAnexo = anexoRepository.findById(updateProcesso.getAnexo().getId())
							.orElseThrow(() -> new EntityNotFoundException(
									"Anexo with ID " + updateProcesso.getAnexo().getId() + " not found."));
					existingAnexo.setNumero(updateProcesso.getAnexo().getNumero());
					anexoRepository.save(existingAnexo);
					existingProcesso.setAnexo(existingAnexo);
				}
			}

			// Check if Usuario needs to be saved or updated
			if (updateProcesso.getUsuario() != null) {
				if (updateProcesso.getUsuario().getId() == null) {
					// Save new Usuario if ID is null
					UsuarioModel usuario = usuarioRepository.save(updateProcesso.getUsuario());
					existingProcesso.setUsuario(usuario);
				} else {
					// Update existing Usuario if ID is present
					UsuarioModel existingUsuario = usuarioRepository.findById(updateProcesso.getUsuario().getId())
							.orElseThrow(() -> new EntityNotFoundException(
									"Usuario with ID " + updateProcesso.getUsuario().getId() + " not found."));
					existingUsuario.setNome(updateProcesso.getUsuario().getNome());
					existingUsuario.setCpfCnpj(updateProcesso.getUsuario().getCpfCnpj());
					usuarioRepository.save(existingUsuario);
					existingProcesso.setUsuario(existingUsuario);
				}
			}

			// Save the updated ProcessoModel
			ProcessoModel originalResponse = processoRepository.save(existingProcesso);

			return createSafeResponse(originalResponse);
		} else {
			throw new EntityNotFoundException("ProcessoModel with ID " + id + " not found.");
		}
	}

	public ProcessoModel createSafeResponse(ProcessoModel originalResponse) {
		ProcessoModel safeResponse = new ProcessoModel();

		safeResponse.setId(originalResponse.getId());
		safeResponse.setNumero(originalResponse.getNumero());

		// Only set Anexo if it exists and has an ID
		if (originalResponse.getAnexo() != null && originalResponse.getAnexo().getId() != null) {
			safeResponse.setAnexo(
					new AnexoModel(originalResponse.getAnexo().getId(), originalResponse.getAnexo().getNumero()));
		}

		// Only set Usuario if it exists and has an ID
		if (originalResponse.getUsuario() != null && originalResponse.getUsuario().getId() != null) {
			safeResponse.setUsuario(new UsuarioModel(originalResponse.getUsuario().getId(),
					originalResponse.getUsuario().getNome(), originalResponse.getUsuario().getCpfCnpj()));
		}

		return safeResponse;
	}

	@Transactional
	public Set<ProcessoModel> listByKeyword(String keyword) {
		Set<ProcessoModel> response = readJsonStringAndConvert(keyword);

		return response;
	}

	public Set<ProcessoModel> readJsonStringAndConvert(String keyword) {

		List<Object> result = processoRepository.listByKeyword(keyword);
		Set<ProcessoModel> response = new HashSet<>();

		//System.out.println(result);

		if (result == null) {
			System.out.println("No results found for the keyword: " + keyword);
			return response;
		}

		String json = result != null ? result.toString() : null;

		if (json != null) {

			//System.out.println("list processo by key " + json);
			// Since the structure is a list of objects containing 'object', extract them
			Set<Map<String, ProcessoModel>> tempList = new Gson().fromJson(json,
					new TypeToken<Set<Map<String, ProcessoModel>>>() {
					}.getType());

			// Iterate over the list and extract 'object' object from each map
			for (Map<String, ProcessoModel> map : tempList) {
				ProcessoModel object = map.get("processo");
				if (object != null) {
					response.add(object);
				}
			}
		}

		return response;

	}

	@Transactional
	public ProcessoModel deleteById(Long id) {
		ProcessoModel deleteResponse = processoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado processo com o id: " + id));

		processoRepository.deleteById(id);
		return createSafeResponse(deleteResponse);
	}

	@Transactional
	public void delete() {
		processoRepository.deleteAll();
	}

};