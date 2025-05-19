package com.squezada.msvc.detallecompras.msvc_detallecompras.services;

import java.util.List;

public interface DetallecomprasService {

    List<Detallecompra> findAll();
    Detallecompra findById(Long id);
    Detallecompra save(Detallecompra detallecompra);
}
