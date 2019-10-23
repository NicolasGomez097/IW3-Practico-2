package com.iw3.concesionario.business;

import java.util.List;

import com.iw3.concesionario.model.Venta;

public interface IVentaBusiness {
	public List<Venta> list() throws BusinessException;

	public Venta load(Integer idVenta) throws BusinessException, NotFoundException;

	public Venta save(Venta venta) throws BusinessException;

	public void remove(Integer  idVenta) throws BusinessException, NotFoundException;
}
