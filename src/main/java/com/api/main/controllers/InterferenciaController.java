package com.api.main.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.InterferenciaDTO;
import com.api.main.dto.SubterraneaDTO;
import com.api.main.models.InterferenciaModel;
import com.api.main.models.SubterraneaModel;
import com.api.main.services.InterferenciaService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/interference")
public class InterferenciaController {

	@Autowired
	private InterferenciaService interferenciaService;


	
	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid InterferenciaDTO interDTO) {
		InterferenciaModel interMod = new InterferenciaModel();
		BeanUtils.copyProperties(interDTO, interMod);
		return ResponseEntity.status(HttpStatus.CREATED).body(interferenciaService.save(interDTO, interMod));

	}

	@PostMapping("/create/subterranean")
	public ResponseEntity<Object> save(@RequestBody @Valid SubterraneaDTO subDTO) {
		SubterraneaModel subterraneaModel = new SubterraneaModel();
		// System.out.println(subterraneaModel.getTipoInterferencia().getId());
		BeanUtils.copyProperties(subDTO, subterraneaModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(interferenciaService.save(subterraneaModel));
	}

	@GetMapping("/list")
	public ResponseEntity<List<InterferenciaDTO>> list(@RequestParam(required = false) String keyword) {
		List<InterferenciaDTO> resultList = interferenciaService.searchInterferenciasByLogradouro(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
}
