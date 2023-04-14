package com.api.main.services;

import javax.transaction.Transactional;

import com.api.main.models.TipoDocumentoModel;
import com.api.main.repositories.TipoDocumentoRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoDocumentoService {

	final TipoDocumentoRepository tdr;

	public TipoDocumentoService(TipoDocumentoRepository tdr) {
		this.tdr = tdr;
	}

	@Transactional
	public TipoDocumentoModel save(TipoDocumentoModel tdm) {
		return tdr.save(tdm);
	}

}