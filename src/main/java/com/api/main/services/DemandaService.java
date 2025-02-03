package com.api.main.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.dto.DemandaPorEnderecoIdDTO;
import com.api.main.repositories.DemandaRepository;

@Service
public class DemandaService {

	@Autowired
	private DemandaRepository demandaRepository;

	public Set<DemandaPorEnderecoIdDTO> getDemandaByAddressId(Long endId) {
		Set<Object[]> rawResults = demandaRepository.listByAddressId(endId);
		Set<DemandaPorEnderecoIdDTO> result = new HashSet<>();

		for (Object[] row : rawResults) {
			
			System.out.println(row);
			
			DemandaPorEnderecoIdDTO dto = new DemandaPorEnderecoIdDTO();
			dto.setIntId((Long) row[0]);
			dto.setEndId((Long) row[1]);
			dto.setEndLogradouro((String) row[2]);
			dto.setIntLatitude((Double) row[3]);
			dto.setIntLongitude((Double) row[4]);
			dto.setSubTpId((String) row[5]);
			dto.setDtDemanda((String) row[6]); // Set the JSON as String
			dto.setVolAnualMa((Double) row[7]);

			result.add(dto);
		}

		return result;
	}
}
