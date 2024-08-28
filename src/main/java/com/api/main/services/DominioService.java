package com.api.main.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.dto.DominioDTO;
import com.api.main.dto.EstadoDTO;
import com.api.main.dto.LocalCaptacaoDTO;
import com.api.main.dto.SituacaoProcessoDTO;
import com.api.main.dto.SubsistemaDTO;
import com.api.main.dto.SubtipoOutorgaDTO;
import com.api.main.dto.TipoAtoDTO;
import com.api.main.dto.TipoInterferenciaDTO;
import com.api.main.dto.TipoOutorgaDTO;
import com.api.main.dto.TipoPocoDTO;
import com.api.main.repositories.DominioRepository;

@Service
public class DominioService {

	@Autowired
	private DominioRepository dominioRepository;

	public DominioDTO listAll() {
		List<Object[]> results = dominioRepository.listAll();

		Map<Long, TipoInterferenciaDTO> tipoInterferencia = new HashMap<>();
		Map<Long, TipoAtoDTO> tipoAto = new HashMap<>();
		Map<Long, TipoOutorgaDTO> tipoOutorga = new HashMap<>();
		Map<Long, SubtipoOutorgaDTO> subtipoOutorga = new HashMap<>();
		Map<Long, EstadoDTO> estado = new HashMap<>();
		Map<Long, SituacaoProcessoDTO> situacaoProcesso = new HashMap<>();
		Map<Long, SubsistemaDTO> subsistema = new HashMap<>();
		Map<Long, TipoPocoDTO> tipoPoco = new HashMap<>();
		Map<Long, LocalCaptacaoDTO> localCaptacao = new HashMap<>();

		for (Object[] row : results) {
			String tipo = (String) row[0];
			Long id = ((Number) row[1]).longValue();
			String descricao = (String) row[2];

			switch (tipo) {
			case "TipoAto":
				tipoAto.put(id, new TipoAtoDTO(id, descricao));
				break;
			case "TipoInterferencia":
				tipoInterferencia.put(id, new TipoInterferenciaDTO(id, descricao));
				break;
			case "TipoOutorga":
				tipoOutorga.put(id, new TipoOutorgaDTO(id, descricao));
				break;
			case "SubtipoOutorga":
				subtipoOutorga.put(id, new SubtipoOutorgaDTO(id, descricao));
				break;
			case "Estado":
				estado.put(id, new EstadoDTO(id, descricao));
				break;
			case "SituacaoProcesso":
				situacaoProcesso.put(id, new SituacaoProcessoDTO(id, descricao));
				break;
			case "Subsistema":
				subsistema.put(id, new SubsistemaDTO(id, descricao));
				break;
			case "TipoPoco":
				tipoPoco.put(id, new TipoPocoDTO(id, descricao));
				break;
			case "LocalCaptacao":
				localCaptacao.put(id, new LocalCaptacaoDTO(id, descricao));
				break;
			default:
				throw new IllegalArgumentException("Unknown type: " + tipo);
			}
		}

		return new DominioDTO(tipoInterferencia, tipoAto, tipoOutorga, subtipoOutorga, estado, situacaoProcesso,
				subsistema, tipoPoco, localCaptacao);
	}

}
