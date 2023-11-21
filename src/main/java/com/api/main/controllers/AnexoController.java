package com.api.main.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.api.main.dto.AnexoDTO;
import com.api.main.models.AnexoModel;
import com.api.main.models.ProcessoModel;
import com.api.main.services.AnexoService;
import com.api.main.services.ProcessoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/attachment")
public class AnexoController {

	final AnexoService anexoService;
	final ProcessoService processoService;

	public AnexoController(AnexoService anexoService, ProcessoService processoService) {
		this.anexoService = anexoService;
		this.processoService = processoService;
	}

	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid AnexoDTO anexoDTO) {

	    AnexoModel anexoModel = new AnexoModel();
	    ProcessoModel processoModel = new ProcessoModel();

	    // Set AnexoModel properties
	    Long anId = anexoDTO.getAnId();
	    if (anId != null) {
	        anexoModel.setAnId(anId);
	    }
	    anexoModel.setAnNumero(anexoDTO.getAnNumero());

	    // Set ProcessoModel properties
	    ProcessoModel processoDTO = anexoDTO.getAnPrincipal();
	    if (processoDTO != null) {
	        Long procId = processoDTO.getProcId();
	        if (procId != null) {
	            processoModel.setProcId(procId);
	        }
	        processoModel.setProcNumero(processoDTO.getProcNumero());

	        // Save ProcessoModel first to obtain its ID
	        processoModel = processoService.save(processoModel);

	        // Associate AnexoModel with the saved ProcessoModel
	        anexoModel.setAnPrincipal(processoModel);
	    }

	    // Save AnexoModel with the associated ProcessoModel
	    AnexoModel savedAnexo = anexoService.save(anexoModel);

	    return ResponseEntity.status(HttpStatus.CREATED).body(savedAnexo);
	}


	/*
	 * @PostMapping("/create") public ResponseEntity<Object>
	 * save(@RequestBody @Valid AnexoDTO procDTO) {
	 * 
	 * AnexoModel procMod = new AnexoModel();
	 * 
	 * BeanUtils.copyProperties(procDTO, procMod); return
	 * ResponseEntity.status(HttpStatus.CREATED).body(anexoService.save(procMod)); }
	 */

	@GetMapping("/list")
	public ResponseEntity<List<AnexoModel>> list(@RequestParam(required = false) String keyword) {
		List<AnexoModel> resultList = anexoService.list(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody AnexoModel udpateProcesso) {
		AnexoModel updated = anexoService.update(id, udpateProcesso);
		if (updated != null) {
			return ResponseEntity.ok().body(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteProcesso(@RequestParam(required = false) Long id) {
		if (id != null) {
			// Delete a specific object by ID
			AnexoModel deleteResponse = anexoService.deleteById(id);
			if (deleteResponse != null) {
				return ResponseEntity.ok(deleteResponse);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			// Delete all objects
			anexoService.delete();
			return ResponseEntity.ok("Todos os objetos deletados!!!");
		}
	}

}
