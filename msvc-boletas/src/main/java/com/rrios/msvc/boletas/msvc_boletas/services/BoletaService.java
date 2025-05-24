package com.rrios.msvc.boletas.msvc_boletas.services;

import com.rrios.msvc.boletas.msvc_boletas.models.entities.Boleta;

import java.util.List;

public interface BoletaService {
    List<Boleta> findAll();
    Boleta findById(Long id);
    Boleta save(Boleta boleta);
}
