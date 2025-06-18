package com.rrios.msvc.boletas.msvc_boletas.services;

import com.rrios.msvc.boletas.msvc_boletas.dtos.BoletaDTO;
import com.rrios.msvc.boletas.msvc_boletas.dtos.DetallecomprasDTO;
import com.rrios.msvc.boletas.msvc_boletas.models.entities.Boleta;

import java.util.List;

public interface BoletaService {
    List<BoletaDTO> findAll();
    BoletaDTO findById(Long id);
    Boleta save(Boleta boleta);
}
