package com.farancibia.msvc.inventarios.msvc_inventarios.repositories;

import com.farancibia.msvc.inventarios.msvc_inventarios.models.entities.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario,Long> {

    Optional<Inventario> findByIdSucursalAndIdProducto(Long idSucursal, Long idProducto);
}
