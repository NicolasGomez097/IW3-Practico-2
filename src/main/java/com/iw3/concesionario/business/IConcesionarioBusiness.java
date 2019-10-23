package com.iw3.concesionario.business;

import java.util.List;

import com.iw3.concesionario.model.Concesionario;

public interface IConcesionarioBusiness {
	public List<Concesionario> list() throws BusinessException;

	public Concesionario load(Integer idConcesionario) throws BusinessException, NotFoundException;

	public Concesionario save(Concesionario concesionario) throws BusinessException;

	public void remove(Integer idConcesionario) throws BusinessException, NotFoundException;
}
