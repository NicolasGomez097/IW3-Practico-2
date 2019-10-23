package com.iw3.concesionario.persistance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iw3.concesionario.model.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo,Integer> {
	public Optional<Vehiculo> findByMarcaAndModeloAndVersionAndColor(String marca, String Modelo, String verison, String color);
}
	