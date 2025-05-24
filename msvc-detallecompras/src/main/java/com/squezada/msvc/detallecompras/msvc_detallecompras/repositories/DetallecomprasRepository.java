package com.squezada.msvc.detallecompras.msvc_detallecompras.repositories;

import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompras;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetallecomprasRepository  extends JpaRepository<Detallecompras, Long> {

    Optional<Detallecompras> findByIdProducto(Long productoId);

}
