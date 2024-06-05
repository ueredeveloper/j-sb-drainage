package com.api.main.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import com.api.main.dto.FinalidadeDTO;
import com.api.main.models.FinalidadeModel;
import com.api.main.services.FinalidadeService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/purpose")
public class FinalidadeController {

	final FinalidadeService finService;

	public FinalidadeController(FinalidadeService finService) {
		this.finService = finService;
	}

	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid FinalidadeDTO finalidadeDTO) {
		FinalidadeModel finalidadeModel = new FinalidadeModel();
		BeanUtils.copyProperties(finalidadeDTO, finalidadeModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(finService.save(finalidadeModel));
	}

	/*
	 * @PutMapping("/update") public ResponseEntity<Object>
	 * update(@RequestParam("id") long id, @RequestBody FinalidadeModel
	 * updateFinalidade) { FinalidadeModel updated = finService.update(id,
	 * updateFinalidade); if (updated != null) { return
	 * ResponseEntity.ok().body(updated); } else { return
	 * ResponseEntity.notFound().build(); } }
	 */

	@GetMapping("/list")
	public ResponseEntity<List<FinalidadeModel>> list(@RequestParam(required = false) String keyword) {
		List<FinalidadeModel> resultList = finService.list(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteFinalidade(@RequestParam(required = false) Long id) {
		if (id != null) {
			FinalidadeModel deleteResponse = finService.deleteById(id);
			if (deleteResponse != null) {
				return ResponseEntity.ok(deleteResponse);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			finService.delete();
			return ResponseEntity.ok("Todos os objetos deletados!!!");
		}
	}
}
