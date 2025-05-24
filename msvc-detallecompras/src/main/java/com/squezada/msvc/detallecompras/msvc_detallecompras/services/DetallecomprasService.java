package com.squezada.msvc.detallecompras.msvc_detallecompras.services;

import com.squezada.msvc.detallecompras.msvc_detallecompras.models.Detallecompras;


import java.util.List;

public interface DetallecomprasService {

    List<Detallecompras> findAll();
    Detallecompras findById(Long id);
    Detallecompras save(Detallecompras detallecompras);
    List<Detallecompras> findByIdproducto(Long idProducto);
}
