package com.api.main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.FinalidadeModel;
import com.api.main.models.InterferenciaModel;
import com.api.main.models.TipoFinalidadeModel;
import com.api.main.repositories.FinalidadeRepository;
import com.api.main.repositories.InterferenciaRepository;
import com.api.main.repositories.TipoFinalidadeRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class FinalidadeService {

	@Autowired
	FinalidadeRepository finalidadeRepository;
	@Autowired
	InterferenciaRepository interferenciaRepository;
	@Autowired
	TipoFinalidadeRepository tipoFinalidadeRepository;

	@Transactional
	public List<FinalidadeModel> listByInterferenciaId(Long id) {

		List<FinalidadeModel> response = readJsonStringAndConvert(id);
		return response;
	}

	public List<FinalidadeModel> readJsonStringAndConvert(Long id) {

		List<Object> result = finalidadeRepository.listByInterferenciaId(id);
		List<FinalidadeModel> response = new ArrayList<>();

		if (result == null) {
			return response; // Return an empty list if no results
		}

		// example of result [{"endereco": {"id": 1, "logradouro": "Rua Novaes Terceiro,
		// Casa 12"}}, {"endereco": {"id": 2, "logradouro": "Avenida Principal, Bloco
		// A"}}, {"endereco": {"id": 3, "logradouro": "Rua das Flores, Apartamento 5"}}]

		String json = result != null ? result.toString() : null;

		System.out.println(json);

		if (json != null) {

			// Since the structure is a list of objects containing 'endereco', extract them
			List<Map<String, FinalidadeModel>> tempList = new Gson().fromJson(json,
					new TypeToken<List<Map<String, FinalidadeModel>>>() {
					}.getType());

			// Iterate over the list and extract 'endereco' object from each map
			for (Map<String, FinalidadeModel> map : tempList) {
				FinalidadeModel obj = map.get("finalidade");
				if (obj != null) {
					response.add(obj);
				}
			}
		}

		return response;

	}

	@Transactional
	public FinalidadeModel save(FinalidadeModel finMod) {
		FinalidadeModel originalResponse = finalidadeRepository.save(finMod);
		return createSafeResponse(originalResponse);
	}

	@Transactional
	public FinalidadeModel update(Long id, FinalidadeModel toUpdateObject) {
		FinalidadeModel originalResponse = finalidadeRepository.findById(id).map((FinalidadeModel record) -> {
			record.setFinalidade(toUpdateObject.getFinalidade());
			record.setSubfinalidade(toUpdateObject.getSubfinalidade());
			record.setQuantidade(toUpdateObject.getQuantidade());
			record.setConsumo(toUpdateObject.getConsumo());

			// Edita a interferência relacionada
			if (toUpdateObject.getInterferencia() != null && toUpdateObject.getInterferencia().getId() != null) {
				Optional<InterferenciaModel> obj = interferenciaRepository
						.findById(toUpdateObject.getInterferencia().getId());
				obj.ifPresent(record::setInterferencia);
			} else {
				throw new IllegalArgumentException("É requerido a interferência relacionada!!!");
			}

			// Edita o tipo de interferência
			if (toUpdateObject.getTipoFinalidade() != null && toUpdateObject.getTipoFinalidade().getId() != null) {
				Optional<TipoFinalidadeModel> obj = tipoFinalidadeRepository
						.findById(toUpdateObject.getTipoFinalidade().getId());

				obj.ifPresent(record::setTipoFinalidade);
			} else {
				throw new IllegalArgumentException("É requerido o tipo de interferência relacionado!!!");
			}

			return finalidadeRepository.save(record);
		}).orElse(null);

		if (originalResponse == null) {
			throw new NoSuchElementException("Não foi encontrado a finalidade com o id: " + id);
		}

		// Remove a interferência e a adiciona novamente apenas com o id. Assim não dá o
		// loop no json (finalidade com interferencia com finalidade, ...)
		

		return createSafeResponse(originalResponse);
	}

	@Transactional
	public FinalidadeModel deleteById(Long id) {
		FinalidadeModel originalResponse = finalidadeRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Não foi encontrado documento com o id: " + id));
		finalidadeRepository.deleteById(id);
		return createSafeResponse(originalResponse);
	}

	public FinalidadeModel createSafeResponse(FinalidadeModel originalResponse) {

		FinalidadeModel safeResponse = new FinalidadeModel();

		safeResponse.setId(originalResponse.getId());
		safeResponse.setFinalidade(originalResponse.getFinalidade());
		safeResponse.setSubfinalidade(originalResponse.getSubfinalidade());
		safeResponse.setQuantidade(originalResponse.getQuantidade());
		safeResponse.setConsumo(originalResponse.getConsumo());
		safeResponse.setTotal(originalResponse.getTotal());
		safeResponse.setTipoFinalidade(new TipoFinalidadeModel(originalResponse.getTipoFinalidade().getId(), originalResponse.getTipoFinalidade().getDescricao()));
		safeResponse.setInterferencia(new InterferenciaModel(originalResponse.getInterferencia().getId()));

		return safeResponse;
	}

	@Transactional
	public void delete() {
		finalidadeRepository.deleteAll();
	}

}
