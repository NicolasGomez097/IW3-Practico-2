package com.iw3.concesionario.business;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iw3.concesionario.model.Concesionario;
import com.iw3.concesionario.persistance.ConcesionarioRepository;

@Service
public class ConcesionarioBusiness implements IConcesionarioBusiness {
	private Logger log = LoggerFactory.getLogger(this.getClass());	

	@Autowired
	private ConcesionarioRepository repo;

	@Override
	public List<Concesionario> list() throws BusinessException {
		try {
			return repo.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
	}

	@Override
	public Concesionario load(Integer idConcesionario) throws BusinessException, NotFoundException {
		Optional<Concesionario> concesionario;
		
		try {
			concesionario = repo.findById(idConcesionario);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
		
		if(!concesionario.isPresent())
			throw new NotFoundException("No se encuentra el concesionario con id " + idConcesionario);
		
		return concesionario.get();
	}

	@Override
	public Concesionario save(Concesionario concesionario) throws BusinessException {
		boolean isNew = concesionario.getId() == null;
		Concesionario out = null;
		try {
			out = repo.save(concesionario);
		}catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
		if(!isNew)
			log.info("se guardo un concesionario, concesionario = "+concesionario.toString());
		else
			log.info("se inserto un concesionario, concesionario = "+concesionario.toString());
		return out;
	}

	@Override
	public void remove(Integer idConcesionario) throws BusinessException, NotFoundException {
		Optional<Concesionario> concesionario;
		
		try {
			concesionario = repo.findById(idConcesionario);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
		
		if(!concesionario.isPresent())
			throw new NotFoundException("No se encuentra el concesionario con id " + idConcesionario);		
		
		try {
			repo.deleteById(idConcesionario);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e);
		}
		
		log.info("se elimino el concesionario con id " + idConcesionario);
	}

}
