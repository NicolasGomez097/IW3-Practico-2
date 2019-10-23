package com.iw3.concesionario.business;

import java.util.List;

import com.iw3.concesionario.model.Vehiculo;

public interface IVehiculoBusiness {
	
	public List<Vehiculo> list() throws BusinessException;

	public Vehiculo load(int idVehiculo) throws BusinessException, NotFoundException;

	public Vehiculo save(Vehiculo vehiculo) throws BusinessException;
	
	public Boolean exists(Vehiculo vehiculo) throws BusinessException;

	public void remove(int idVehiculo) throws BusinessException, NotFoundException;
	
}
