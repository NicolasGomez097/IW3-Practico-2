package com.iw3.concesionario.business;

import java.util.List;

import com.iw3.concesionario.model.Vendedor;

public interface IVendedorBusiness {
	public List<Vendedor> list() throws BusinessException;

	public Vendedor load(Integer idVendedor) throws BusinessException, NotFoundException;

	public Vendedor save(Vendedor vendedor) throws BusinessException;

	public void remove(Integer idVendedor) throws BusinessException, NotFoundException;
}
