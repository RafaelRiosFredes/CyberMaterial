package com.rrios.msvc.boletas.msvc_boletas.repositories;

import com.rrios.msvc.boletas.msvc_boletas.dtos.BoletaDTO;
import com.rrios.msvc.boletas.msvc_boletas.models.entities.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta,Long> {
    BoletaDTO findDTOByIdBoleta(Long id);
    Boleta save(BoletaDTO boleta);
}
