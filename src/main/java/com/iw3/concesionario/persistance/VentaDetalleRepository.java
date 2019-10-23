package com.iw3.concesionario.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iw3.concesionario.model.VentaDetalle;

@Repository
public interface VentaDetalleRepository extends JpaRepository<VentaDetalle,Integer> {
}
