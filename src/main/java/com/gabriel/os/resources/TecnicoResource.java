package com.gabriel.os.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.os.dtos.TecnicoDTO;
import com.gabriel.os.services.TecnicoService;

@RestController //classe rest := get, post, put...
@RequestMapping(value = "/tecnicos") // endpoint inicial
public class TecnicoResource {
	
	//localhost:8080/tecnicos/1
	
	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
			TecnicoDTO objDTO = new TecnicoDTO(this.service.findById(id));
			return ResponseEntity.ok().body(objDTO);
	} 
}
