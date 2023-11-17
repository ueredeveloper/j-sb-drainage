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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.ProcessoDTO;
import com.api.main.models.ProcessoModel;
import com.api.main.services.ProcessoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/processo")
public class ProcessoController {

	final ProcessoService service;

	public ProcessoController(ProcessoService service) {
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid ProcessoDTO procDTO) {

		ProcessoModel procMod = new ProcessoModel();

		BeanUtils.copyProperties(procDTO, procMod);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(procMod));
	}
	/*
	 * @GetMapping("/list") public ResponseEntity<List<ProcessoModel>> list() {
	 * return ResponseEntity.status(HttpStatus.CREATED).body(service.list()); }
	 * 
	 * // Buscar por parâmetro
	 * 
	 * @GetMapping("/list") public ResponseEntity<List<ProcessoModel>> list
	 * (@RequestParam String keyword) { List<ProcessoModel> searchResults =
	 * service.search(keyword); return
	 * ResponseEntity.status(HttpStatus.OK).body(searchResults); }
	 */

	@GetMapping("/list")
	public ResponseEntity<List<ProcessoModel>> list(@RequestParam(required = false) String keyword) {
		List<ProcessoModel> resultList = service.list(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@GetMapping("/childrens")
	public ResponseEntity<List<ProcessoModel>> listChildrens (@RequestParam("id") Long id) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.listChildrens(id));
	}

	@PutMapping(value = "")
	public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody ProcessoModel udpateProcesso) {
		ProcessoModel updated = service.update(id, udpateProcesso);
		if (updated != null) {
			return ResponseEntity.ok().body(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * @DeleteMapping("/deleteAll") public ResponseEntity<String> deleteAll() {
	 * service.delete(); return ResponseEntity.ok("Todos os objetos deletados!!!");
	 * }
	 * 
	 * @DeleteMapping("") public ResponseEntity<ProcessoModel>
	 * deleteById(@RequestParam Long id) {
	 * 
	 * ProcessoModel deleteResponse = service.deleteById(id); return
	 * ResponseEntity.ok(deleteResponse); }
	 */
	@DeleteMapping("")
	public ResponseEntity<Object> deleteProcesso(@RequestParam(required = false) Long id) {
		if (id != null) {
			// Delete a specific object by ID
			ProcessoModel deleteResponse = service.deleteById(id);
			if (deleteResponse != null) {
				return ResponseEntity.ok(deleteResponse);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			// Delete all objects
			service.delete();
			return ResponseEntity.ok("Todos os objetos deletados!!!");
		}
	}

}
