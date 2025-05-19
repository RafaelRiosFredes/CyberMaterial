package com.squezada.msvc.detallecompras.msvc_detallecompras.services;

import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompras;

import java.util.List;

public interface DetallecomprasService {

    List<Detallecompras> findAll();
    Detallecompras findById(Long id);
    Detallecompras save(Detallecompras detallecompras);
}
