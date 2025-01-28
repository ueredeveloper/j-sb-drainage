package com.api.main.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.EstadoModel;
import com.api.main.repositories.EstadoRepository;


@Service
public class EstadoService {
	
	@Autowired
	EstadoRepository estadoRespository;

	@Transactional
	public List<EstadoModel> findAll() {
		return estadoRespository.findAll();
	}

}
