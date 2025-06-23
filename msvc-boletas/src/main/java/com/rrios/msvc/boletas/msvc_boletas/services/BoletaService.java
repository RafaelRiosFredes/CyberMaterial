package com.rrios.msvc.boletas.msvc_boletas.services;

import com.rrios.msvc.boletas.msvc_boletas.dtos.BoletaDTO;
import com.rrios.msvc.boletas.msvc_boletas.dtos.DetallecomprasDTO;
import com.rrios.msvc.boletas.msvc_boletas.models.entities.Boleta;

import java.util.List;
import java.util.Optional;

public interface BoletaService {
    List<BoletaDTO> findAllDTOs();
    BoletaDTO findDTOById(Long id);
    Boleta save(BoletaDTO boleta);
    void deleteById(Long id);
}
