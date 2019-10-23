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
import com.iw3.concesionario.business.IVentaBusiness;
import com.iw3.concesionario.business.NotFoundException;
import com.iw3.concesionario.model.Venta;
import com.iw3.concesionario.utils.Constantes;

@RestController
@RequestMapping(Constantes.URL_BASE_VENTA)
public class VentaRestController {
	
	@Autowired
	private IVentaBusiness ventaBO;
	
	@GetMapping("")
	public ResponseEntity<List<Venta>> list() {
		try {
			return new ResponseEntity<List<Venta>>(ventaBO.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Venta>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Venta> load(@PathVariable("id") int idVenta) {
		try {
			return new ResponseEntity<Venta>(ventaBO.load(idVenta), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Venta>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Venta>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<String> insert(@RequestBody Venta venta) {
		try {
			ventaBO.save(venta);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_BASE_VEHICULO + "/" + venta.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "")
	public ResponseEntity<String> update(@RequestBody Venta venta) {
		try {
			ventaBO.save(venta);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int idVenta) {
		try {
			ventaBO.remove(idVenta);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
}
