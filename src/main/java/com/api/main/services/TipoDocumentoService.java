package com.api.main.services;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.api.main.models.TipoDocumentoModel;
import com.api.main.repositories.TipoDocumentoRepository;

@Service
public class TipoDocumentoService {

	final TipoDocumentoRepository tdRepo;

	public TipoDocumentoService(TipoDocumentoRepository tdRepo) {
		this.tdRepo = tdRepo;
	}

	@Transactional
	public TipoDocumentoModel save(TipoDocumentoModel tdm) {
		return tdRepo.save(tdm);
	}

	@Transactional
	public List<TipoDocumentoModel> listAll() {
		return tdRepo.findAll();
	}
	@Transactional
	public void deleteAll () {
		tdRepo.deleteAll();
	}
	

}