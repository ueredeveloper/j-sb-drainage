package com.api.main.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.SubterraneaDTO;
import com.api.main.models.SubterraneaModel;
import com.api.main.services.SubterraneaService;

@RestController
@RequestMapping("/interference/subterranean")
public class SubterraneaController {

	
	@Autowired
	private SubterraneaService subterraneaService;

	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid SubterraneaDTO objDTO) {
		SubterraneaModel objMod = new SubterraneaModel();
		BeanUtils.copyProperties(objDTO, objMod);
		return ResponseEntity.status(HttpStatus.CREATED).body(subterraneaService.save(objMod));
	}

	@GetMapping("/list")
	public ResponseEntity<List<SubterraneaModel>> list(@RequestParam(required = false) String keyword) {
		List<SubterraneaModel> resultList = subterraneaService.listByKeyword(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody SubterraneaModel objMod) {
		SubterraneaModel toUpdate = subterraneaService.update(id, objMod);
		if (toUpdate != null) {
			return ResponseEntity.ok().body(toUpdate);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}