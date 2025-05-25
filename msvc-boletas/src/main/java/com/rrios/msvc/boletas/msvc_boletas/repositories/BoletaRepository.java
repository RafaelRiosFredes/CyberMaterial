package com.rrios.msvc.boletas.msvc_boletas.repositories;

import com.rrios.msvc.boletas.msvc_boletas.models.entities.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta,Long> {

}
