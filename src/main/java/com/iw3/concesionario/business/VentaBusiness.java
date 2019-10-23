package com.iw3.concesionario.business;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iw3.concesionario.model.Venta;
import com.iw3.concesionario.persistance.VentaRepository;

@Service
public class VentaBusiness implements IVentaBusiness {
	private Logger log = LoggerFactory.getLogger(this.getClass());	

	@Autowired
	private VentaRepository repo;

	@Override
	public List<Venta> list() throws BusinessException {
		try {
			return repo.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
	}

	@Override
	public Venta load(Integer idVenta) throws BusinessException, NotFoundException {
		Optional<Venta> venta;
		
		try {
			venta = repo.findById(idVenta);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
		
		if(!venta.isPresent())
			throw new NotFoundException("No se encuentra la venta con id " + idVenta);
		
		return venta.get();		
	}

	@Override
	public Venta save(Venta venta) throws BusinessException {
		boolean isNew = venta.getId() == null;
		Venta out = null;
		try {
			out = repo.save(venta);
		}catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}

		if(!isNew)
			log.info("se guardo una venta, venta = "+venta.toString());
		else
			log.info("se inserto una venta, venta = "+venta.toString());
		return out;
	}

	@Override
	public void remove(Integer idVenta) throws BusinessException, NotFoundException {
		Optional<Venta> venta;
		
		try {
			venta = repo.findById(idVenta);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
		
		if(!venta.isPresent())
			throw new NotFoundException("No se encuentra la venta con id " + idVenta);		
		
		try {
			repo.deleteById(idVenta);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		
		log.info("se elimino la venta con id " + idVenta);
	}

}
