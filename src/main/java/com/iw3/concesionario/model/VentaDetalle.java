package com.iw3.concesionario.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property = "id", scope = VentaDetalle.class)
public class VentaDetalle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer cantidad;
	
	@ManyToOne()
	private Vehiculo vehiculo;
	
	@ManyToOne	
	private Venta venta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	
	
	@Override
	public String toString() {
		String json = "{";
		if(id != null)
			json += "'id':"+id+",";
		if(cantidad != null)
			json += "'cantidad':"+cantidad+",";
		if(vehiculo != null)
			json += "'idVehiculo':"+vehiculo.getId();		
		return json+"}";
	}	
}
