package com.iw3.concesionario.business;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iw3.concesionario.model.VentaDetalle;
import com.iw3.concesionario.persistance.VentaDetalleRepository;

@Service
public class VentaDetalleBusiness implements IVentaDetalleBusiness {
	private Logger log = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	private VentaDetalleRepository repo;

	@Override
	public List<VentaDetalle> list() throws BusinessException {
		try {
			return repo.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
	}

	@Override
	public VentaDetalle load(Integer idVentaDetalle) throws BusinessException, NotFoundException {
		Optional<VentaDetalle> venta;
		
		try {
			venta = repo.findById(idVentaDetalle);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
		
		if(!venta.isPresent())
			throw new NotFoundException("No se encuentra el detalle de venta con id " + idVentaDetalle);
		
		return venta.get();		
	}

	@Override
	public VentaDetalle save(VentaDetalle ventaDetalle) throws BusinessException {
		boolean isNew = ventaDetalle.getId() == null;
		VentaDetalle out = null;
		try {
			out = repo.save(ventaDetalle);
		}catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}

		if(!isNew)
			log.info("se guardo un detalle de venta, detalle de venta = "+ventaDetalle.toString());
		else
			log.info("se inserto un detalle de venta, detalle de venta = "+ventaDetalle.toString());
		return out;
	}

	@Override
	public void remove(Integer idVentaDetalle) throws BusinessException, NotFoundException {
		Optional<VentaDetalle> venta;
		
		try {
			venta = repo.findById(idVentaDetalle);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
		
		if(!venta.isPresent())
			throw new NotFoundException("No se encuentra el detalle de venta con id " + idVentaDetalle);		
		
		try {
			repo.deleteById(idVentaDetalle);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
		
		log.info("se elimino el detalle de venta con id " + idVentaDetalle);

	}

}
