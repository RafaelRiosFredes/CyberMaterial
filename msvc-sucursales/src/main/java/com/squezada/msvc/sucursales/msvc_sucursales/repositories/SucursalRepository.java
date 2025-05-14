package com.squezada.msvc.sucursales.msvc_sucursales.repositories;

import com.squezada.msvc.sucursales.msvc_sucursales.models.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal,Long> {

}
