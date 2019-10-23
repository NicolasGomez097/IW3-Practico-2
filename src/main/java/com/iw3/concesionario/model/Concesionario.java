package com.iw3.concesionario.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Concesionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	@Column(length = 20)
	private String nombre;	
	
	@Column(length = 30)
	private String direccion;	
	private String teleforno;
	
	@OneToMany(mappedBy = "concesionario")
	private List<Venta> ventas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTeleforno() {
		return teleforno;
	}

	public void setTeleforno(String teleforno) {
		this.teleforno = teleforno;
	}	
	
	@Override
	public String toString() {
		String json = "{";
		if(id != null)
			json += "'id':"+id+",";
		if(nombre != null)
			json += "'nombre':"+nombre+",";
		if(direccion != null)
			json += "'direccion':"+direccion+",";
		if(teleforno != null)
			json += "'teleforno':"+teleforno+",";
		if(ventas != null) {
			json += "'idVentas':[";
			for(int i = 0; i < ventas.size()-1; i++) {
				json += ventas.get(i).getId() + ",";
			}
			json += ventas.get(ventas.size()-1) + "]";
		}
		
		if(json.endsWith(","))
				json = json.substring(0, json.length()-1);
		
		return json;			
	}
}
