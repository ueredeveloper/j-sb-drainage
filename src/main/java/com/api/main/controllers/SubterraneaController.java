package com.api.main.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.SubterraneaDTO;
import com.api.main.models.InterferenciaModel;
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

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody SubterraneaModel objMod) {
		SubterraneaModel toUpdate = subterraneaService.update(id, objMod);
		if (toUpdate != null) {
			return ResponseEntity.ok().body(toUpdate);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestParam(required = false) Long id) {
		if (id != null) {
			// Delete a specific object by ID
			InterferenciaModel deleteResponse = subterraneaService.deleteById(id);
			if (deleteResponse != null) {
				return ResponseEntity.ok(deleteResponse);
			} else {
				return ResponseEntity.notFound().build();
			}

		} else {
			return ResponseEntity.ok("{\"info\": \"Id não informado!!!\"}");

		}
	}

}