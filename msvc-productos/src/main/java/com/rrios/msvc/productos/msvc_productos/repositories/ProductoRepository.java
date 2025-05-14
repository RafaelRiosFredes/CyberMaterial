package com.rrios.msvc.productos.msvc_productos.repositories;

import com.rrios.msvc.productos.msvc_productos.models.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
