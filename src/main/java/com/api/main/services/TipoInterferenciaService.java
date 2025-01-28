package com.api.main.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.TipoInterferenciaModel;
import com.api.main.repositories.TipoInterferenciaRepository;


@Service
public class TipoInterferenciaService {
	
	@Autowired
	TipoInterferenciaRepository tipoInterferenciaRepository;

	@Transactional
	public TipoInterferenciaModel save(TipoInterferenciaModel object) {
		return tipoInterferenciaRepository.save(object);
	}

	@Transactional
	public List<TipoInterferenciaModel> listAll() {
		return tipoInterferenciaRepository.listAll();
	}

}
