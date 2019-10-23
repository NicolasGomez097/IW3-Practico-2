package com.iw3.concesionario.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Vehiculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="modelo",length = 30)
	private String modelo;
	@Column(name="marca",length = 30)
	private String marca;
	@Column(name="version",length = 30)
	private String version;
	private Double precio;
	private String color;
	private String descripcion;
	
	@JsonIgnore
	@OneToMany(mappedBy = "vehiculo")	
	private List<VentaDetalle> ventaDesc;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<VentaDetalle> getVentaDesc() {
		return ventaDesc;
	}

	public void setVentaDesc(List<VentaDetalle> ventaDesc) {
		this.ventaDesc = ventaDesc;
	}
	

	@Override
	public String toString() {
		String json = "{";
		if(id != null)
			json += "'id':"+id+",";
		if(marca != null)
			json += "'marca':"+marca+",";
		if(modelo != null)
			json += "'modelo':"+modelo+",";
		if(version != null)
			json += "'version':"+version+",";
		if(color != null)
			json += "'color':"+color+",";
		if(precio != null)
			json += "'precio':"+precio+",";
		if(descripcion != null)
			json += "'descripcion':"+descripcion;
		
		if(json.endsWith(","))
			json = json.substring(0, json.length()-1);
		
		return json+"}";
	}	
	
	public String isValid() {
		String out = "";
		if(marca == null)
			out += "La marca no puede ser nula,";
		if(modelo == null)
			out += "El modelo no puede ser nulo,";
		if(version == null)
			out += "La version no puede ser nula,";
		if(color == null)
			out += "El color no puede ser nulo,";
		if(precio == null)
			out += "El precio no puede ser nulo";
				
		if(out.endsWith(","))
			out = out.substring(0,out.length()-1);
		
		if(out.equals(""))
			return null;
		else
			return out;
	}
}
