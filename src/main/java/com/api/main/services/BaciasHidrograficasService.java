package com.api.main.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.dto.BaciaHidrograficaDTO;
import com.api.main.models.BaciaHidrograficaModel;
import com.api.main.repositories.BaciaHidrograficaRepository;


@Service
public class BaciasHidrograficasService {

	@Autowired
	private BaciaHidrograficaRepository repository;

	@Transactional
	public List<BaciaHidrograficaModel> findByPoint(Double lat, Double lng) {
		List<Object[]> result = repository.findByPoint(lat, lng);
		List<BaciaHidrograficaModel> response = new ArrayList<>();

		if (result == null || result.isEmpty()) {
			System.out.println("Nenhum resultado para a coordenada: " + lat + ", " + lng);
			return response;
		}

		// Convert query results to `BaciaHidrograficaModel`
		for (Object[] row : result) {
			BaciaHidrograficaModel model = new BaciaHidrograficaModel();
			model.setBaciaNome((String) row[0]);
			model.setBaciaCod(Long.parseLong((String) row[1]));
			model.setObjectid(Long.parseLong((String) row[2].toString()));
			response.add(model);
		}

		return response;
	}

	 @Transactional
	    public List<BaciaHidrograficaDTO> listAll() {
	        // Fetch data from repository
	        List<Object[]> resultList = repository.listAll();
	        List<BaciaHidrograficaDTO> response = new ArrayList<>();

	        // Map the Object[] to DTO
	        for (Object[] row : resultList) {
	            BaciaHidrograficaDTO dto = new BaciaHidrograficaDTO();
	            dto.setObjectid(Long.parseLong((String) row[0].toString()));
	            dto.setBaciaNome((String) row[1]);
	            dto.setBaciaCod(Long.parseLong((String) row[2].toString()));
	            dto.setShape((String) row[3]);  // GeoJSON as String
	            response.add(dto);
	        }

	        return response;
	    }

}
