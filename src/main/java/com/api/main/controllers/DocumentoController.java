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

import com.api.main.dto.DocumentoDTO;
import com.api.main.models.DocumentoModel;
import com.api.main.services.DocumentoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/document")
public class DocumentoController {

	
	final DocumentoService service;
	//final AnexoRepository anRepostory;

	public DocumentoController(DocumentoService service) {
		this.service = service;
		//this.anRepostory = anRepostory;
	}
	@PostMapping("/create")
	public ResponseEntity<Object> save(@RequestBody @Valid DocumentoDTO docDTO) {
		/*DocumentoModel docMod = new DocumentoModel();
		BeanUtils.copyProperties(docDTO, docMod);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(docMod));*/
		try {
            DocumentoModel docMod = new DocumentoModel();
            BeanUtils.copyProperties(docDTO, docMod);
            
           // System.out.println(docDTO.getDocAnexo().getAnPrincipal().getProcNumero());
           
            // Save DocumentoModel
            DocumentoModel savedDocumento = service.save(docMod, docDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedDocumento);
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Documento and Anexo");
        }
		
		
		
	/*	 DocumentoModel docMod = new DocumentoModel();
	        BeanUtils.copyProperties(docDTO, docMod);
	        
	        

	       if (docDTO.getDocAnexo() != null) {
	        	 // Create AnexoModel
		        AnexoModel anexoModel = new AnexoModel();
		        AnexoDTO anexoDTO = docDTO.getDocAnexo();
		        BeanUtils.copyProperties(anexoDTO, anexoModel);

		        // Set relationship between AnexoModel and ProcessoModel
		        ProcessoModel processoAnexo = new ProcessoModel();
		        BeanUtils.copyProperties(anexoDTO.getAnPrincipal(), processoAnexo);
		        anexoModel.setAnPrincipal(processoAnexo);
	        
		        anRepostory.save(anexoModel);
	        }
	       
	        // Save both DocumentoModel and AnexoModel
	        DocumentoModel savedDocumento = service.save(docMod);
	        
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedDocumento);*/
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestParam("id") long id, @RequestBody DocumentoModel updateDocumento) {
		DocumentoModel updated = service.update(id, updateDocumento);
		if (updated != null) {
			return ResponseEntity.ok().body(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<DocumentoModel>> list(@RequestParam(required = false) String keyword) {
		List<DocumentoModel> resultList = service.list(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteProcesso(@RequestParam(required = false) Long id) {
		if (id != null) {
			// Delete a specific object by ID
			DocumentoModel deleteResponse = service.deleteById(id);
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
