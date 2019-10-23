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
import com.iw3.concesionario.business.IVehiculoBusiness;
import com.iw3.concesionario.business.NotFoundException;
import com.iw3.concesionario.model.Vehiculo;
import com.iw3.concesionario.utils.Constantes;

@RestController
@RequestMapping(Constantes.URL_BASE_VEHICULO)
public class VehiculoRestController {
	
	@Autowired
	private IVehiculoBusiness vehiculoBO;
	
	@GetMapping("")
	public ResponseEntity<List<Vehiculo>> list() {
		try {
			return new ResponseEntity<List<Vehiculo>>(vehiculoBO.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Vehiculo>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Vehiculo> load(@PathVariable("id") int idcomida) {
		try {
			return new ResponseEntity<Vehiculo>(vehiculoBO.load(idcomida), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Vehiculo>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Vehiculo>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<String> insert(@RequestBody Vehiculo vehiculo) {
		try {
			String error = vehiculo.isValid();
			if(error != null) {
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("detalle_error", error);
				return new ResponseEntity<String>(responseHeaders, HttpStatus.BAD_REQUEST);
			}
			
			if(vehiculoBO.exists(vehiculo)) {
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("detalle_error", "El vehiculo ya existe.");
				return new ResponseEntity<String>(responseHeaders, HttpStatus.BAD_REQUEST);
			}
			
			vehiculoBO.save(vehiculo);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_BASE_VEHICULO + "/" + vehiculo.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "")
	public ResponseEntity<String> update(@RequestBody Vehiculo vehiculo) {
		try {
			vehiculoBO.save(vehiculo);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int idVehiculo) {
		try {
			vehiculoBO.remove(idVehiculo);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
}
