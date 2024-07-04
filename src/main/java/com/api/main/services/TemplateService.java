package com.api.main.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.models.TemplateModel;
import com.api.main.repositories.TemplateRepository;

@Service
public class TemplateService {

	@Autowired
	private TemplateRepository templateRepository;

	@Transactional
	public TemplateModel save(TemplateModel object) {
		return templateRepository.save(object);
	}

	public List<TemplateModel> findAll() {

		return templateRepository.findAll();
	}

}