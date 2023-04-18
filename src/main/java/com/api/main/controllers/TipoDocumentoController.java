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
import org.springframework.web.bind.annotation.RestController;

import com.api.main.dto.TipoDocumentoDTO;
import com.api.main.models.TipoDocumentoModel;
import com.api.main.services.TipoDocumentoService;

@RestController
@CrossOrigin (origins="*", maxAge=3600)
@RequestMapping("/tipo-documento")
public class TipoDocumentoController {

  final TipoDocumentoService tdServ;

  public TipoDocumentoController (TipoDocumentoService tdServ){
    this.tdServ = tdServ;
  }

  @PostMapping
  public ResponseEntity<Object> save (@RequestBody @Valid TipoDocumentoDTO tdDTO){
    TipoDocumentoModel tdMod = new TipoDocumentoModel();

    BeanUtils.copyProperties(tdDTO, tdMod);
    return ResponseEntity.status(HttpStatus.CREATED).body(tdServ.save(tdMod));
  }
  @GetMapping
	public ResponseEntity<List<TipoDocumentoModel>> listAll () {
		return ResponseEntity.status(HttpStatus.CREATED).body(tdServ.listAll());
	}
  @DeleteMapping
  public ResponseEntity<String> deleteAll (){
	  tdServ.deleteAll();
	  return ResponseEntity.ok("Todos os tipos de documento deletados!!!");
  }
}



  
