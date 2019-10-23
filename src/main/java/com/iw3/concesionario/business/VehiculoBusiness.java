package com.iw3.concesionario.business;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iw3.concesionario.model.Vehiculo;
import com.iw3.concesionario.persistance.VehiculoRepository;

@Service
public class VehiculoBusiness implements IVehiculoBusiness {
	private Logger log = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	private VehiculoRepository repo;
	
	@Override
	public List<Vehiculo> list() throws BusinessException {
		try {
			return repo.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
	}

	@Override
	public Vehiculo load(int idVehiculo) throws BusinessException, NotFoundException {
		Optional<Vehiculo> vehiculo;
		
		try {
			vehiculo = repo.findById(idVehiculo);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
		
		if(!vehiculo.isPresent())
			throw new NotFoundException("No se encuentra el vehiculo con id " + idVehiculo);
		
		return vehiculo.get();
	}

	@Override
	public Vehiculo save(Vehiculo vehiculo) throws BusinessException {
		boolean isNew = vehiculo.getId() == null;
		Vehiculo out = null;
		try {
			out = repo.save(vehiculo);
		}catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}

		if(!isNew)
			log.info("se guardo un vehiculo, vehiculo = "+vehiculo.toString());
		else
			log.info("se inserto un vehiculo, vehiculo = "+vehiculo.toString());
		return out;
	}

	@Override
	public void remove(int idVehiculo) throws BusinessException, NotFoundException {
		Optional<Vehiculo> vehiculo;
		
		try {
			vehiculo = repo.findById(idVehiculo);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
		
		if(!vehiculo.isPresent())
			throw new NotFoundException("No se encuentra el vehiculo con id " + idVehiculo);		
		
		try {
			repo.deleteById(idVehiculo);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
		
		log.info("se elimino el vehiculo con id " + idVehiculo);
	}

	@Override
	public Boolean exists(Vehiculo vehiculo) throws BusinessException {
		Optional<Vehiculo> op;
		op = repo.findByMarcaAndModeloAndVersionAndColor(
				vehiculo.getMarca(), 
				vehiculo.getModelo(),
				vehiculo.getVersion(), 
				vehiculo.getColor());
		return op.isPresent();
	}
	
	

}
