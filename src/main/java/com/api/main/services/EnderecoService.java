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
import com.api.main.models.InterferenciaModel;
import com.api.main.repositories.EnderecoRepository;
import com.api.main.repositories.InterferenciaRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository endRepo;
	@Autowired
	private InterferenciaRepository interRepo;

	@Transactional
	public EnderecoModel save(EnderecoModel requestedEndereco) {
		Long id = requestedEndereco.getId();
		EnderecoModel response = null;

		if (id != null && endRepo.existsById(id)) {
			response = endRepo.findById(id).map((EnderecoModel record) -> {
				record.setLogradouro(requestedEndereco.getLogradouro());
				record.setCidade(requestedEndereco.getCidade());
				record.setCep(requestedEndereco.getCep());
				record.setBairro(requestedEndereco.getBairro());
				record.setEstado(requestedEndereco.getEstado());
				return endRepo.save(record);
			}).orElse(null);
		}

		if (response == null) {
			// Salva as interferências, se houver
			if (requestedEndereco.getInterferencias() != null && !requestedEndereco.getInterferencias().isEmpty()) {
				for (InterferenciaModel interferencia : requestedEndereco.getInterferencias()) {
					interferencia.setEndereco(requestedEndereco); // Relaciona a interferência com o endereço
				}

				// Salva todas as interferências associadas ao endereço
				List<InterferenciaModel> interferencias = interRepo.saveAll(requestedEndereco.getInterferencias());
				// Atualiza o conjunto de interferências no endereço
				requestedEndereco.setInterferencias(new HashSet<>(interferencias));
			}

			// Salva o endereço com as interferências atualizadas
			response = endRepo.save(requestedEndereco);
		}

		return response;
	}

	/*
	 * @Transactional public List<EnderecoModel> listByKeyword(String keyword) {
	 * return endRepo.listByKeyword(keyword); }
	 */

	@Transactional
	public List<EnderecoModel> listByKeyword(String keyword) {

		List<EnderecoModel> response = readJsonStringAndConvert(keyword);
		return response;
	}

	@Transactional
	public void delete() {
		endRepo.deleteAll();
	}

	@Transactional
	public EnderecoModel deleteById(Long id) {
		EnderecoModel deleted = endRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado endereço com o id: " + id));
		endRepo.deleteById(id);
		return deleted;
	}

	@Transactional
	public Optional<EnderecoModel> findById(Long id) {
		return endRepo.findById(id);
	}

	@Transactional
	public EnderecoModel update(Long id, EnderecoModel requestedEndereco) {
		EnderecoModel endereco = endRepo.findById(id).map((EnderecoModel record) -> {
			record.setLogradouro(requestedEndereco.getLogradouro());
			record.setCidade(requestedEndereco.getCidade());
			record.setCep(requestedEndereco.getCep());
			record.setBairro(requestedEndereco.getBairro());
			record.setEstado(requestedEndereco.getEstado());

			return endRepo.save(record);
		}).orElse(null);

		if (endereco == null) {
			throw new NoSuchElementException("Não foi encontrado endereço com o id: " + id);
		}
		
		// Cria novo objeto e assim só envia os dados necessários para a responsta.
		EnderecoModel response = new EnderecoModel();
		response.setId(endereco.getId());
		response.setLogradouro(endereco.getLogradouro());
		response.setCidade(endereco.getCidade());
		response.setCep(endereco.getCep());
		response.setBairro(endereco.getBairro());
		response.setEstado(endereco.getEstado());
		// Limpa as interferências relacionadas.
		response.setInterferencias( new HashSet<>());
		

		return response;
	}

	public List<EnderecoModel> readJsonStringAndConvert(String keyword) {

		List<Object> result = endRepo.listByKeyword(keyword);
		List<EnderecoModel> response = new ArrayList<>();

		if (result == null) {
			System.out.println("No results found for the keyword: " + keyword);
			return response; // Return an empty list if no results
		}
		
		//example of result [{"endereco": {"id": 1, "logradouro": "Rua Novaes Terceiro, Casa 12"}}, {"endereco": {"id": 2, "logradouro": "Avenida Principal, Bloco A"}}, {"endereco": {"id": 3, "logradouro": "Rua das Flores, Apartamento 5"}}]

		String endJson = result != null ? result.toString() : null;
		
		if (endJson != null) {
	        // Since the structure is a list of objects containing 'endereco', extract them
	        List<Map<String, EnderecoModel>> tempList = new Gson().fromJson(endJson,
	                new TypeToken<List<Map<String, EnderecoModel>>>() {}.getType());

	        // Iterate over the list and extract 'endereco' object from each map
	        for (Map<String, EnderecoModel> map : tempList) {
	            EnderecoModel endereco = map.get("endereco");
	            if (endereco != null) {
	                response.add(endereco);
	            }
	        }
	    }

		return response;

	}

}
