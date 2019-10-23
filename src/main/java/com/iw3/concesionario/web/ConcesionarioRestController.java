package com.iw3.concesionario.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iw3.concesionario.business.BusinessException;
import com.iw3.concesionario.business.IConcesionarioBusiness;
import com.iw3.concesionario.business.NotFoundException;
import com.iw3.concesionario.model.Concesionario;
import com.iw3.concesionario.utils.Constantes;

@RestController
@RequestMapping(Constantes.URL_BASE_CONCESIONARIO)
public class ConcesionarioRestController {
	
	@Autowired
	private IConcesionarioBusiness concesionarioBO;
	
	@GetMapping("")
	public ResponseEntity<List<Concesionario>> list() {
		try {
			return new ResponseEntity<List<Concesionario>>(concesionarioBO.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Concesionario>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Concesionario> load(@PathVariable("id") int idConcesionario) {
		try {
			return new ResponseEntity<Concesionario>(concesionarioBO.load(idConcesionario), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Concesionario>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Concesionario>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<String> insert(@RequestBody Concesionario concesionario) {
		try {
			if(concesionario == null)
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
			concesionarioBO.save(concesionario);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_BASE_CONCESIONARIO + "/" + concesionario.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "")
	public ResponseEntity<String> update(@RequestBody Concesionario concesionario) {
		try {
			concesionarioBO.save(concesionario);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int idConcesionario) {
		try {
			concesionarioBO.remove(idConcesionario);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
}
