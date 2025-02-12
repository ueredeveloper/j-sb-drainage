package com.api.main.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
public class AnexoService {

	@Autowired
	private AnexoRepository anexoRepository;
	@Autowired
	private ProcessoRepository processoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public AnexoModel save(AnexoModel requestedObject) {
		// Id do anexo se tiver
		Long id = requestedObject.getId();
		// Numero do anexo para busca
		String anexoNumero = requestedObject.getNumero();
		AnexoModel originalResponse = null;

		if (id != null && anexoRepository.existsById(id)) {
			originalResponse = anexoRepository.findById(id).map((AnexoModel record) -> {
				record.setNumero(anexoNumero);
				AnexoModel editedObject = anexoRepository.save(record);
				return createSafeResponse(editedObject);
			}).orElse(null);
		}

		if (originalResponse == null) {
			Set<ProcessoModel> processos = requestedObject.getProcessos();
			// Primeiro, salva o Anexo antes de processar os Processos
			requestedObject = anexoRepository.save(new AnexoModel(requestedObject.getNumero()));

			// Agora, salva os Processos, se houver
			if (processos != null && !processos.isEmpty()) {
				for (ProcessoModel object : processos) {
					if (object.getUsuario() != null) {
						if (object.getUsuario().getId() == null) {
							UsuarioModel user = usuarioRepository.save(object.getUsuario());
							object.setUsuario(user);
						} else {
							UsuarioModel existingUser = usuarioRepository.findById(object.getUsuario().getId())
									.orElseThrow(() -> new EntityNotFoundException(
											"Usuario with ID " + object.getUsuario().getId() + " not found."));
							existingUser.setNome(object.getUsuario().getNome());
							object.setUsuario(existingUser);
						}
					}

					object.setAnexo(requestedObject); // Agora, o Anexo já está salvo e gerenciado
				}

				// Salva os Processos associados ao Anexo
				List<ProcessoModel> objects = processoRepository.saveAll(processos);
				requestedObject.setProcessos(new HashSet<>(objects));
			}

			originalResponse = requestedObject;
		}

		return createSafeResponse(originalResponse);
	}

	@Transactional
	public List<AnexoModel> getAll() {
		return anexoRepository.findAll();
	}

	@Transactional
	public AnexoModel getById(Long id) {
		return anexoRepository.findById(id).orElse(null);
	}

	@Transactional
	public AnexoModel deleteById(Long id) {
		AnexoModel deleteResponse = anexoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado anexo com o id: " + id));

		anexoRepository.deleteById(id);
		return createSafeResponse(deleteResponse);
	}

	@Transactional
	public List<AnexoModel> listByKeyword(String keyword) {

		List<AnexoModel> response = readJsonStringAndConvert(keyword);

		return response;
	}

	public List<AnexoModel> readJsonStringAndConvert(String keyword) {

		List<Object> result = anexoRepository.listByKeyword(keyword);
		List<AnexoModel> response = new ArrayList<>();

		if (result == null) {
			System.out.println("No results found for the keyword: " + keyword);
			return response;
		}

		/*
		 * exemplo de json:
		 * [{"anexo":{"id":1,"numero":"197.123.456/2013","processos":[]}},
		 * {"anexo":{"id":2,"numero":"197.456.789/2015","processos":[]}},
		 * {"anexo":{"id":3,"numero":"198.456/2013","processos":[{"id":1,"numero":
		 * "197.123/2015"}]}}]
		 */

		String json = result != null ? result.toString() : null;

		if (json != null) {

			// System.out.println("list anexo by key " + json);
			// Since the structure is a list of objects containing 'endereco', extract them
			List<Map<String, AnexoModel>> tempList = new Gson().fromJson(json,
					new TypeToken<List<Map<String, AnexoModel>>>() {
					}.getType());

			// Iterate over the list and extract 'endereco' object from each map
			for (Map<String, AnexoModel> map : tempList) {
				AnexoModel endereco = map.get("anexo");
				if (endereco != null) {
					response.add(endereco);
				}
			}
		}

		return response;

	}

	public List<AnexoModel> findAll() {
		// TODO Auto-generated method stub
		return anexoRepository.findAll();
	}

	@Transactional
	public AnexoModel update(Long id, AnexoModel object) {
		return anexoRepository.findById(id).map(record -> {
			record.setNumero(object.getNumero());

			// Processar processos associados
			Set<ProcessoModel> novosProcessos = new HashSet<>();

			if (object.getProcessos() != null) {
				for (ProcessoModel novoProcesso : object.getProcessos()) {
					ProcessoModel processoExistente = processoRepository.findById(novoProcesso.getId()).orElse(null);

					if (processoExistente != null) {
						// Atualiza Anexo
						if (novoProcesso.getAnexo() != null && novoProcesso.getAnexo().getId() != null) {
							processoExistente.setAnexo(record);
						}

						// Atualiza Usuário
						if (novoProcesso.getUsuario() != null && novoProcesso.getUsuario().getId() != null) {
							UsuarioModel usuarioExistente = usuarioRepository
									.findById(novoProcesso.getUsuario().getId())
									.orElseThrow(() -> new EntityNotFoundException("Usuário com ID "
											+ novoProcesso.getUsuario().getId() + " não encontrado."));
							processoExistente.setUsuario(usuarioExistente);
						}
					} else {
						processoExistente = novoProcesso;
						processoExistente.setAnexo(record);
					}

					novosProcessos.add(processoRepository.save(processoExistente));
					// edições
					/*
					processo 00197-00000928/2024-19 usuario INC09 BRASAL INCORPORAÇÕES LTDA=> anexo 00197-00000928/2024-19
					
					ed1 anexo -> 00197-00000928/2024-19 rel - > 00197-00001533/2021-82 com José Saramago
						
					
					
				
					 */

					
				}
			}

			// Remove processos que não estão mais associados
			record.getProcessos().removeIf(proc -> !novosProcessos.contains(proc));
			record.getProcessos().addAll(novosProcessos);

			return createSafeResponse(anexoRepository.save(record));
			
		}).orElseThrow(() -> new NoSuchElementException("Não foi encontrado documento com o id: " + id));
	}

	public AnexoModel createSafeResponse(AnexoModel originalResponse) {
		AnexoModel safeResponse = new AnexoModel();
		safeResponse.setId(originalResponse.getId());
		safeResponse.setNumero(originalResponse.getNumero());

		// Inicialize a lista de processos no safeResponse, se ainda não estiver
		// inicializada
		if (safeResponse.getProcessos() == null) {
			safeResponse.setProcessos(new HashSet<>());
		}

		// Somente preencha os processos se originalResponse tiver processos
		if (originalResponse.getProcessos() != null && !originalResponse.getProcessos().isEmpty()) {
			for (ProcessoModel object : originalResponse.getProcessos()) {
				if (object != null && object.getUsuario() != null && object.getAnexo() != null) {
					safeResponse.getProcessos()
							.add(new ProcessoModel(object.getId(), object.getNumero(),
									new AnexoModel(object.getAnexo().getId(), object.getAnexo().getNumero()),
									new UsuarioModel(
											object.getUsuario().getId(), 
											object.getUsuario().getNome(), 
											object.getUsuario().getCpfCnpj())));
				}
			}
		}

		return safeResponse;
	}

}
