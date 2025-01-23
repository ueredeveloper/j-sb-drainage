package com.api.main.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.dto.HidrogeoPorosoDTO;
import com.api.main.models.HidrogeoPoroso;
import com.api.main.repositories.HidrogeoPososoRepository;

@Service
public class HidrogeoPorosoService {

	@Autowired
	private HidrogeoPososoRepository repository;

	@Transactional
	public List<HidrogeoPoroso> findByPoint(Double lat, Double lng) {
		List<Object[]> result = repository.findByPoint(lat, lng);
		List<HidrogeoPoroso> response = new ArrayList<>();

		if (result == null || result.isEmpty()) {
			System.out.println("Nenhum resultado para a coordenada: " + lat + ", " + lng);
			return response;
		}

		// Convert query results to `BaciaHidrograficaModel`
		for (Object[] row : result) {

			// objectid, uh_label, bacia_codi, uh_codigo
			HidrogeoPoroso model = new HidrogeoPoroso();
			model.setObjectId(Long.parseLong((String) row[0].toString()));
			model.setCodPlan((String) row[1]);
			model.setSistema((String) row[2]);

			response.add(model);
		}

		return response;
	}

	@Transactional
	public List<HidrogeoPorosoDTO> listAll() {
		// Fetch data from repository
		List<Object[]> resultList = repository.listAll();
		List<HidrogeoPorosoDTO> response = new ArrayList<>();

		// Map the Object[] to DTO
		for (Object[] row : resultList) {
			HidrogeoPorosoDTO dto = new HidrogeoPorosoDTO();
			dto.setObjectId(Long.parseLong((String) row[0].toString()));
			dto.setCodPlan((String) row[1]);
			dto.setSistema((String) row[2]);
			response.add(dto);
		}

		return response;
	}

}
