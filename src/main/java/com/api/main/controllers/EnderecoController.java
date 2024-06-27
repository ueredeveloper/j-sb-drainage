package com.api.main.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.EnderecoDTO;
import com.api.main.models.EnderecoModel;
import com.api.main.services.EnderecoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/address")
public class EnderecoController {

	
	@Autowired
	private EnderecoService enderecoService;


	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid EnderecoDTO endDTO) {
		EnderecoModel endMod = new EnderecoModel();
		BeanUtils.copyProperties(endDTO, endMod);
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(endMod));
	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody EnderecoModel updateRequest) {
		EnderecoModel updated = enderecoService.update(id, updateRequest);
		if (updated != null) {
			return ResponseEntity.ok().body(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/list")
	public ResponseEntity<List<EnderecoModel>> list(@RequestParam(required = false) String keyword) {
		List<EnderecoModel> resultList = enderecoService.list(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteProcesso(@RequestParam(required = false) Long id) {
		if (id != null) {
			// Delete a specific object by ID
			EnderecoModel response = enderecoService.deleteById(id);
			if (response != null) {
				return ResponseEntity.ok(response);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			// Delete all objects
			enderecoService.delete();
			return ResponseEntity.ok("Todos os endereços deletados!!!");
		}
	}

}
