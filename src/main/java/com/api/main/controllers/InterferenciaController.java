package com.api.main.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.DTDemandaDTO;
import com.api.main.dto.InterferenciaDTO;
import com.api.main.models.InterferenciaModel;
import com.api.main.services.InterferenciaService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/interference")
public class InterferenciaController {

	// v1.12.0
	@Autowired
	private InterferenciaService interferenciaService;

	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid InterferenciaDTO interDTO) {
		InterferenciaModel interMod = new InterferenciaModel();
		BeanUtils.copyProperties(interDTO, interMod);
		return ResponseEntity.status(HttpStatus.CREATED).body(interferenciaService.save(interMod));

	}

	@GetMapping("/list-by-keyword")
	public ResponseEntity<Set<InterferenciaModel>> list(@RequestParam(required = false) String keyword) {
		Set<InterferenciaModel> resultList = interferenciaService.listByLogradouro(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestParam(required = false) Long id) {
		if (id != null) {
			// Delete a specific object by ID
			InterferenciaModel deleteResponse = interferenciaService.deleteById(id);
			if (deleteResponse != null) {
				return ResponseEntity.ok(deleteResponse);
			} else {
				return ResponseEntity.notFound().build();
			}

		} else {
			return ResponseEntity.ok("{\"info\": \"Id não informado!!!\"}");

		}
	}

	@GetMapping("/search-by-end-id")
	public ResponseEntity<Set<DTDemandaDTO>> getDemandaByAddressId(@RequestParam Long endId) {
		Set<DTDemandaDTO> result = interferenciaService.getDemandaByAddressId(endId);
		return ResponseEntity.ok(result);
	}
}
