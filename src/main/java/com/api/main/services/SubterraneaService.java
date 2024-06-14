package com.api.main.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.dto.SubterraneaDTO;
import com.api.main.models.EnderecoModel;
import com.api.main.models.SubterraneaModel;
import com.api.main.repositories.EnderecoRepository;
import com.api.main.repositories.SubterraneaRepository;

@Service
public class SubterraneaService {
	

	@Autowired
	private SubterraneaRepository subterraneaRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Transactional
	public List<SubterraneaModel> list(String keyword) {
		return subterraneaRepository.list(keyword);
	}

	@Transactional
	public SubterraneaModel save(SubterraneaDTO subDTO, SubterraneaModel subMod) {
		
		System.out.println("--------------------------- ");
		System.out.println(subDTO.getInterEndereco());

		if (subDTO.getInterEndereco() != null) {
			
			
			EnderecoModel endereco = new EnderecoModel();
			endereco.setEndLogradouro(subDTO.getInterEndereco().getEndLogradouro());

			endereco = enderecoRepository.save(endereco);
			
			subMod.setInterEndereco(endereco);

		}

		return subterraneaRepository.save(subMod);
	}
}
