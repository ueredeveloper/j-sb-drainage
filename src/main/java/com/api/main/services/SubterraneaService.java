package com.api.main.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.dto.SubterraneaDTO;
import com.api.main.models.SubterraneaModel;
import com.api.main.repositories.SubterraneaRepository;

@Service
public class SubterraneaService {

	@Autowired
	private SubterraneaRepository subterraneaRepository;

	@Transactional
	public List<SubterraneaModel> list(String keyword) {
		return subterraneaRepository.list(keyword);
	}

	@Transactional
	public SubterraneaModel save(SubterraneaDTO subDTO, SubterraneaModel subMod) {
		return subterraneaRepository.save(subMod);
	}
}
