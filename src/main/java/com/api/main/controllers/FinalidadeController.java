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

import com.api.main.dto.FinalidadeDTO;
import com.api.main.models.FinalidadeModel;
import com.api.main.services.FinalidadeService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/purpouse")
public class FinalidadeController {

	@Autowired
	private FinalidadeService finalidadeService;

	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid FinalidadeDTO toSaveDTO) {
		FinalidadeModel toSaveModel = new FinalidadeModel();
		BeanUtils.copyProperties(toSaveDTO, toSaveModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(finalidadeService.save(toSaveModel));
	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody FinalidadeModel toUpdateObject) {
		FinalidadeModel updated = finalidadeService.update(id, toUpdateObject);
		if (updated != null) {
			return ResponseEntity.ok().body(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}


	@GetMapping("/list-by-inter-id")
	public ResponseEntity<List<FinalidadeModel>> listByInterferenciaId(@RequestParam(required = false) Long id) {
		List<FinalidadeModel> resultList = finalidadeService.listByInterferenciaId(id);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteFinalidade(@RequestParam(required = false) Long id) {
		if (id != null) {
			FinalidadeModel deleteResponse = finalidadeService.deleteById(id);
			if (deleteResponse != null) {
				return ResponseEntity.ok(deleteResponse);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			finalidadeService.delete();
			return ResponseEntity.ok("Todos os objetos deletados!!!");
		}
	}
}
