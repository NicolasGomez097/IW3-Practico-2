package com.iw3.concesionario.business;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iw3.concesionario.model.Vendedor;
import com.iw3.concesionario.persistance.VendedorRepository;

@Service
public class VendedorBusiness implements IVendedorBusiness {
	private Logger log = LoggerFactory.getLogger(this.getClass());	

	@Autowired
	private VendedorRepository repo;

	@Override
	public List<Vendedor> list() throws BusinessException {
		try {
			return repo.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
	}

	@Override
	public Vendedor load(Integer idVendedor) throws BusinessException, NotFoundException {
		Optional<Vendedor> venta;
		
		try {
			venta = repo.findById(idVendedor);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
		
		if(!venta.isPresent())
			throw new NotFoundException("No se encuentra el vendedor con id " + idVendedor);
		
		return venta.get();		
	}

	@Override
	public Vendedor save(Vendedor vendedor) throws BusinessException {
		boolean isNew = vendedor.getId() == null;
		Vendedor out = null;
		try {
			out = repo.save(vendedor);
		}catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}

		if(!isNew)
			log.info("se guardo un vendedor, vendedor = "+vendedor.toString());
		else
			log.info("se inserto un vendedor, vendedor = "+vendedor.toString());
		return out;
	}

	@Override
	public void remove(Integer idVendedor) throws BusinessException, NotFoundException {
		Optional<Vendedor> venta;
		
		try {
			venta = repo.findById(idVendedor);			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
		
		if(!venta.isPresent())
			throw new NotFoundException("No se encuentra el vendedor con id " + idVendedor);		
		
		try {
			repo.deleteById(idVendedor);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
		
		log.info("se elimino el vendedor con id " + idVendedor);
	}

}
