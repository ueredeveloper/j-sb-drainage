package com.api.main.services;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.dto.TemplateDTO;
import com.api.main.models.TemplateModel;
import com.api.main.repositories.TemplateRepository;

@Service
public class TemplateService {

	@Autowired
	private TemplateRepository templateRepository;

	@Transactional
	public TemplateModel save(TemplateDTO objectDTO, TemplateModel objectMod) {

		TemplateModel newObject = templateRepository.save(objectMod);
		return newObject;
	}

	@Transactional
	public List<TemplateModel> findAll() {

		return templateRepository.findAll();
	}

	@Transactional
	public Set<TemplateModel> listByKeyword(String keyword) {
		return templateRepository.listByKeyword(keyword);
	}

	@Transactional
	public Set<TemplateModel> listTemplatesByParams(String tipoDocumento, String tipoOutorga, String subtipoOutorga) {
		return templateRepository.listTemplatesByParams(tipoDocumento, tipoOutorga, subtipoOutorga);
	}

}