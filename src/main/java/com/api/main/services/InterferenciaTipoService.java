package com.api.main.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.InterferenciaTipoModel;
import com.api.main.repositories.InterferenciaTipoRepository;

@Service
public class InterferenciaTipoService {

	@Autowired
	InterferenciaTipoRepository interferenciaTipoRepository;

	@Transactional
	public InterferenciaTipoModel save(InterferenciaTipoModel object) {
		return interferenciaTipoRepository.save(object);
	}

	@Transactional
	public List<InterferenciaTipoModel> listAll() {
		return interferenciaTipoRepository.listAll();
	}

}
