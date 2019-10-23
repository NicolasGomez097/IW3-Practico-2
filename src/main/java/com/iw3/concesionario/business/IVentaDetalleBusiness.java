package com.iw3.concesionario.business;

import java.util.List;

import com.iw3.concesionario.model.VentaDetalle;

public interface IVentaDetalleBusiness {
	public List<VentaDetalle> list() throws BusinessException;

	public VentaDetalle load(Integer idVentaDesc) throws BusinessException, NotFoundException;

	public VentaDetalle save(VentaDetalle ventaDescripcion) throws BusinessException;

	public void remove(Integer idVentaDesc) throws BusinessException, NotFoundException;
}
