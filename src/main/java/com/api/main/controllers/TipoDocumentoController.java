package com.api.main.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.TipoDocumentoDTO;
import com.api.main.models.TipoDocumentoModel;
import com.api.main.services.TipoDocumentoService;

@RestController
@CrossOrigin (origins="*", maxAge=3600)
@RequestMapping("/tipo-documento")
public class TipoDocumentoController {

  final TipoDocumentoService tps;

  public TipoDocumentoController (TipoDocumentoService tps){
    this.tps = tps;
  }

  @PostMapping
  public ResponseEntity<Object> save (@RequestBody @Valid TipoDocumentoDTO tddto){
    TipoDocumentoModel tdm = new TipoDocumentoModel();

    BeanUtils.copyProperties(tddto, tdm);
    return ResponseEntity.status(HttpStatus.CREATED).body(tps.save(tdm));
  }
  @GetMapping
	public ResponseEntity<List<TipoDocumentoModel>> listall () {
		return ResponseEntity.status(HttpStatus.CREATED).body(tps.listAll());
	}
}



  
