package com.squezada.msvc.detallecompras.msvc_detallecompras.repositories;

import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallecomprasRepository  extends JpaRepository<Detallecompra, Long> {
}
