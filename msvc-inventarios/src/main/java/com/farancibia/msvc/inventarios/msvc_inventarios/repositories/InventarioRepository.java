package com.farancibia.msvc.inventarios.msvc_inventarios.repositories;

import com.farancibia.msvc.inventarios.msvc_inventarios.models.entities.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Long> {

    Optional<Inventario> findByIdSucursalAndIdProducto(Long idSucursal, Long idProducto);
}
