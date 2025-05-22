package com.squezada.msvc.detallecompras.msvc_detallecompras.repositories;

import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallecomprasRepository  extends JpaRepository<Detallecompra, Long> {

    List<Detallecompra> findByidProducto(Long idProducto);

}
