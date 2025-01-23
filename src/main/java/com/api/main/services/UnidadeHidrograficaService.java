package com.api.main.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.dto.UnidadeHidrograficaDTO;
import com.api.main.models.UnidadeHidrograficaModel;
import com.api.main.repositories.UnidadeHidrograficaRepository;

@Service
public class UnidadeHidrograficaService {

	@Autowired
	private UnidadeHidrograficaRepository repository;

	@Transactional
	public List<UnidadeHidrograficaModel> findByPoint(Double lat, Double lng) {
		List<Object[]> result = repository.findByPoint(lat, lng);
		List<UnidadeHidrograficaModel> response = new ArrayList<>();

		if (result == null || result.isEmpty()) {
			System.out.println("Nenhum resultado para a coordenada: " + lat + ", " + lng);
			return response;
		}

		// Convert query results to `BaciaHidrograficaModel`
		for (Object[] row : result) {

			// objectid, uh_label, bacia_codi, uh_codigo
			UnidadeHidrograficaModel model = new UnidadeHidrograficaModel();
			model.setObjectid(Long.parseLong((String) row[0].toString()));
			model.setUhCodigo(Long.parseLong((String) row[1].toString()));
			model.setUhLabel((String) row[2]);
			model.setBaciaCodi((String) row[3]);
			model.setUhNome((String) row[4]);

			response.add(model);
		}

		return response;
	}

	@Transactional
	public List<UnidadeHidrograficaDTO> listAll() {
		// Fetch data from repository
		List<Object[]> resultList = repository.listAll();
		List<UnidadeHidrograficaDTO> response = new ArrayList<>();

		// Map the Object[] to DTO
		for (Object[] row : resultList) {
			UnidadeHidrograficaDTO dto = new UnidadeHidrograficaDTO();
			dto.setObjectid(Long.parseLong((String) row[0].toString()));
			dto.setUhCodigo(Long.parseLong((String) row[1].toString()));
			dto.setUhLabel((String) row[2]);
			dto.setBaciaCodi((String) row[3]);
			dto.setUhNome((String) row[4]);
			response.add(dto);
		}

		return response;
	}

}
