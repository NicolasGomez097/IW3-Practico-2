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
import com.iw3.concesionario.business.IVentaDetalleBusiness;
import com.iw3.concesionario.business.NotFoundException;
import com.iw3.concesionario.model.VentaDetalle;
import com.iw3.concesionario.utils.Constantes;

@RestController
@RequestMapping(Constantes.URL_BASE_VENTA_DETALLE)
public class VentaDetalleRestController {

	@Autowired
	private IVentaDetalleBusiness ventaDetalleBO;

	@GetMapping("")
	public ResponseEntity<List<VentaDetalle>> list() {
		try {
			return new ResponseEntity<List<VentaDetalle>>(ventaDetalleBO.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<VentaDetalle>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<VentaDetalle> load(@PathVariable("id") int idVentaDetalle) {
		try {
			return new ResponseEntity<VentaDetalle>(ventaDetalleBO.load(idVentaDetalle), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<VentaDetalle>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<VentaDetalle>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<String> insert(@RequestBody VentaDetalle ventaDetalle) {
		try {
			
			if(ventaDetalle.getCantidad() < 1) {
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("detalle_error", "La cantidad no puede ser menor a 1");
				return new ResponseEntity<String>(responseHeaders, HttpStatus.BAD_REQUEST);
			}
			
			ventaDetalleBO.save(ventaDetalle);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_BASE_VEHICULO + "/" + ventaDetalle.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "")
	public ResponseEntity<String> update(@RequestBody VentaDetalle ventaDetalle) {
		try {
			if(ventaDetalle.getCantidad() < 1) {
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("detalle_error", "La cantidad no puede ser menor a 1");
				return new ResponseEntity<String>(responseHeaders, HttpStatus.BAD_REQUEST);
			}
			
			ventaDetalleBO.save(ventaDetalle);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int idVentaDetalle) {
		try {
			ventaDetalleBO.remove(idVentaDetalle);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
}
