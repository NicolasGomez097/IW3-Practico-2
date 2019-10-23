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
import com.iw3.concesionario.business.IVendedorBusiness;
import com.iw3.concesionario.business.NotFoundException;
import com.iw3.concesionario.model.Vendedor;
import com.iw3.concesionario.utils.Constantes;

@RestController
@RequestMapping(Constantes.URL_BASE_VENDEDOR)
public class VendedorRestController {
	
	@Autowired
	private IVendedorBusiness vendedorBO;
	
	@GetMapping("")
	public ResponseEntity<List<Vendedor>> list() {
		try {
			return new ResponseEntity<List<Vendedor>>(vendedorBO.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Vendedor>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Vendedor> load(@PathVariable("id") int idVendedor) {
		try {
			return new ResponseEntity<Vendedor>(vendedorBO.load(idVendedor), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Vendedor>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Vendedor>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<String> insert(@RequestBody Vendedor vendedor) {
		try {
			vendedorBO.save(vendedor);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_BASE_VEHICULO + "/" + vendedor.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "")
	public ResponseEntity<String> update(@RequestBody Vendedor vendedor) {
		try {
			vendedorBO.save(vendedor);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int idVendedor) {
		try {
			vendedorBO.remove(idVendedor);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
}
