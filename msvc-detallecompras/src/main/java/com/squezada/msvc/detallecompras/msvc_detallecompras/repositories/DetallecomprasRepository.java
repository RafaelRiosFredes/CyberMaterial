package com.squezada.msvc.detallecompras.msvc_detallecompras.repositories;

import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.DetalledecomprasDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompras;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetallecomprasRepository  extends JpaRepository<Detallecompras, Long> {



    List<DetalledecomprasDTO>findByIdBoleta(Long idBoleta);

    Optional<Detallecompras> findByIdProductoAndIdBoleta(Long idProducto, Long idBoleta);

}
