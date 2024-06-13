package com.api.main.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.dto.InterferenciaDTO;
import com.api.main.models.EnderecoModel;
import com.api.main.models.InterferenciaModel;
import com.api.main.repositories.EnderecoRepository;
import com.api.main.repositories.InterferenciaRepository;

@Service
public class InterferenciaService {

	@Autowired
	private InterferenciaRepository interferenciaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Transactional
	public List<InterferenciaModel> list(String keyword) {
		return interferenciaRepository.list(keyword);
	}

	@Transactional
	public InterferenciaModel save(InterferenciaDTO interDTO, InterferenciaModel interMod) {
		InterferenciaModel interferenciaModel = interferenciaRepository.save(interMod);
		return interferenciaModel;
	}

	@Transactional
	public InterferenciaModel save(InterferenciaModel interModel) {

		if (interModel.getInterEndereco() != null) {

			EnderecoModel endereco = new EnderecoModel();
			endereco.setEndLogradouro(interModel.getInterEndereco().getEndLogradouro());
			endereco.setEndCidade(interModel.getInterEndereco().getEndCidade());
			endereco.setEndBairro(interModel.getInterEndereco().getEndBairro());
			endereco.setEndCep(interModel.getInterEndereco().getEndCep());
			endereco.setEndEstado(interModel.getInterEndereco().getEndEstado());

			endereco = enderecoRepository.save(endereco);

			interModel.setInterEndereco(endereco);

		}

		return interferenciaRepository.save(interModel);
	}

}
