package com.api.main.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;
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

	@GetMapping("/list-by-keyword")
	public ResponseEntity<List<EnderecoModel>> list(@RequestParam(required = false) String keyword) {
		List<EnderecoModel> resultList = enderecoService.listByKeyword(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete (@RequestParam(required = false) Long id) {
	    try {
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
	    } catch (Exception ex) {
	        Throwable cause = ex;
	        while (cause != null && !(cause instanceof org.hibernate.exception.ConstraintViolationException)) {
	            cause = cause.getCause();
	        }
	        if (cause instanceof org.hibernate.exception.ConstraintViolationException) {
	            org.hibernate.exception.ConstraintViolationException constraintViolationException = (org.hibernate.exception.ConstraintViolationException) cause;
	            // Return detailed error message
	            Map<String, Object> errorDetails = new HashMap<>();
	            errorDetails.put("timestamp", LocalDateTime.now());
	            errorDetails.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	            errorDetails.put("error", "Internal Server Error");
	            errorDetails.put("message", constraintViolationException.getSQLException().getMessage());
	            errorDetails.put("path", "/address/delete");

	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
	        } else {
	            // Handle other exceptions
	            ex.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
	        }
	    }
	}


}
