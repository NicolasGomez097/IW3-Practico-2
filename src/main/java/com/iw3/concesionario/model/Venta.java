package com.iw3.concesionario.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "id",scope = Venta.class)
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	private Date fecha;	
	private Integer dniCliente;	
	private String nombreCliente;	
	private String telefono;
	
	@OneToMany(mappedBy = "venta",cascade = CascadeType.REMOVE)
	private List<VentaDetalle> desc;
	
	@ManyToOne()
	private Concesionario concesionario;
	
	@ManyToMany()
	@JoinTable(
			name = "vendedor_asignado",
			joinColumns = @JoinColumn(name = "venta_id"),
			inverseJoinColumns = @JoinColumn(name = "vendedor_id")
	)	
	private List<Vendedor> vendedores;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(Integer dniCliente) {
		this.dniCliente = dniCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public List<VentaDetalle> getDesc() {
		return desc;
	}

	public void setDesc(List<VentaDetalle> desc) {
		this.desc = desc;
	}

	public Concesionario getConcesionario() {
		return concesionario;
	}

	public void setConcesionario(Concesionario concesionario) {
		this.concesionario = concesionario;
	}

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	@Override
	public String toString() {
		String json = "{";
		if(id != null)
			json += "'id':"+id+",";
		if(fecha != null)
			json += "'fecha':"+fecha+",";
		if(dniCliente != null)
			json += "'dniCliente':"+dniCliente+",";
		if(nombreCliente != null)
			json += "'nombreCliente':"+nombreCliente+",";
		if(telefono != null)
			json += "'telefono':"+telefono+",";
		if(desc != null) {
			json += "'idDetalles':[";
			for(int i = 0; i < desc.size()-1; i++) {
				json += desc.get(i).getId()+",";
			}
			json += desc.get(desc.size()-1)+"],";
		}
		if(vendedores != null) {
			json += "'idVendedores':[";
			for(int i = 0; i < vendedores.size()-1; i++) {
				json += vendedores.get(i).getId() + ",";
			}
			json += vendedores.get(vendedores.size()-1) + "],";
		}
		if(concesionario != null) {
			json += "'idConcesionario':"+concesionario.getId();
		}
		
		if(json.endsWith(","))
				json = json.substring(0, json.length()-1);
		
		return json;			
	}
	
	
}
