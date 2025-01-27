package com.api.main.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.dto.HidrogeoFraturadoDTO;
import com.api.main.dto.HidrogeoPorosoDTO;
import com.api.main.models.HidrogeoFraturado;
import com.api.main.models.HidrogeoPoroso;
import com.api.main.repositories.HidrogeoFraturadoRepository;
import com.api.main.repositories.HidrogeoPososoRepository;

@Service
public class HidrogeoFraturadoService {

	@Autowired
	private HidrogeoFraturadoRepository repository;

	@Transactional
	public List<HidrogeoFraturado> findByPoint(Double lat, Double lng) {
		List<Object[]> result = repository.findByPoint(lat, lng);
		List<HidrogeoFraturado> response = new ArrayList<>();

		if (result == null || result.isEmpty()) {
			System.out.println("Nenhum resultado para a coordenada: " + lat + ", " + lng);
			return response;
		}

		// Convert query results to `BaciaHidrograficaModel`
		for (Object[] row : result) {

			// objectid, uh_label, bacia_codi, uh_codigo
			HidrogeoFraturado model = new HidrogeoFraturado();
			model.setObjectId(Long.parseLong((String) row[0].toString()));
			model.setCodPlan((String) row[1]);
			model.setSistema((String) row[2]);
			model.setSubsistema((String) row[3]);

			response.add(model);
		}

		return response;
	}

	@Transactional
	public List<HidrogeoFraturadoDTO> listAll() {
		// Fetch data from repository
		List<Object[]> resultList = repository.listAll();
		List<HidrogeoFraturadoDTO> response = new ArrayList<>();

		// Map the Object[] to DTO
		for (Object[] row : resultList) {
			HidrogeoFraturadoDTO dto = new HidrogeoFraturadoDTO();
			dto.setObjectId(Long.parseLong((String) row[0].toString()));
			dto.setCodPlan((String) row[1]);
			dto.setSistema((String) row[2]);
			dto.setSubsistema((String) row[3]);
			response.add(dto);
		}

		return response;
	}
	
	@Transactional
	public List<HidrogeoFraturadoDTO> listByCodPlan (String codPlan) {
		// Fetch data from repository
		List<Object[]> resultList = repository.listByCodPlan(codPlan);
		List<HidrogeoFraturadoDTO> response = new ArrayList<>();

		// Map the Object[] to DTO
		for (Object[] row : resultList) {
			HidrogeoFraturadoDTO dto = new HidrogeoFraturadoDTO();
			dto.setObjectId(Long.parseLong((String) row[0].toString()));
			dto.setCodPlan((String) row[1]);
			dto.setSistema((String) row[2]);
			dto.setSubsistema((String) row[3]);
			response.add(dto);
		}

		return response;
	}

}
