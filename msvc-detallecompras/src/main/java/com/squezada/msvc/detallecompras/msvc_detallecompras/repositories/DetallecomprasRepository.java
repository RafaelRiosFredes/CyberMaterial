package com.squezada.msvc.detallecompras.msvc_detallecompras.repositories;

import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompras;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallecomprasRepository  extends JpaRepository<Detallecompras, Long> {

    List<Detallecompras> findByIdproducto(Long productoId);

}
