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

import com.api.main.dto.FinalidadeAutorizadaDTO;
import com.api.main.models.FinalidadeAutorizadaModel;
import com.api.main.services.FinalidadeAutorizadaService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/purpose_authorized")
public class FinalidadeAutorizadaController {

	
	
	@Autowired
	private FinalidadeAutorizadaService finalidadeService;

	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid FinalidadeAutorizadaDTO finDTO) {
		FinalidadeAutorizadaModel finMod = new FinalidadeAutorizadaModel();
		BeanUtils.copyProperties(finDTO, finMod);
		return ResponseEntity.status(HttpStatus.CREATED).body(finalidadeService.save(finMod));
	}

	/*
	 * @PutMapping("/update") public ResponseEntity<Object>
	 * update(@RequestParam("id") long id, @RequestBody FinalidadeAutorizadaModel
	 * updateFinalidade) { FinalidadeAutorizadaModel updated = finalidadeService.update(id,
	 * updateFinalidade); if (updated != null) { return
	 * ResponseEntity.ok().body(updated); } else { return
	 * ResponseEntity.notFound().build(); } }
	 */

	@GetMapping("/list")
	public ResponseEntity<List<FinalidadeAutorizadaModel>> list(@RequestParam(required = false) String keyword) {
		List<FinalidadeAutorizadaModel> resultList = finalidadeService.list(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	/*
	 * @DeleteMapping("/delete") public ResponseEntity<Object>
	 * deleteFinalidade(@RequestParam(required = false) Long id) { if (id != null) {
	 * FinalidadeAutorizadaModel deleteResponse = finalidadeService.deleteById(id);
	 * if (deleteResponse != null) { return ResponseEntity.ok(deleteResponse); }
	 * else { return ResponseEntity.notFound().build(); } } else {
	 * finalidadeService.delete(); return
	 * ResponseEntity.ok("Todos os objetos deletados!!!"); } }
	 */
}
