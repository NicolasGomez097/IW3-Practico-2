package com.iw3.concesionario.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Vendedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	private String nombre;
	private String apellido;	
	private String dni;	
	private String telefono;
	
	@ManyToMany()
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public String toString() {
		String json = "{";
		if(id != null)
			json += "'id':"+id+",";
		if(nombre != null)
			json += "'nombre':"+nombre+",";
		if(apellido != null)
			json += "'apellido':"+apellido+",";
		if(telefono != null)
			json += "'telefono':"+telefono+",";
		if(dni != null)
			json += "'dni':"+dni;
		
		if(json.endsWith(","))
			json = json.substring(0, json.length()-1);
		
		return json+"}";
	}	
}
