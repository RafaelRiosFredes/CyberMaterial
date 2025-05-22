package com.farancibia.msvc.inventarios.msvc_inventarios.controllers;

import com.farancibia.msvc.inventarios.msvc_inventarios.models.entities.Inventario;
import com.farancibia.msvc.inventarios.msvc_inventarios.repositories.InventarioRepository;
import com.farancibia.msvc.inventarios.msvc_inventarios.services.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/sucursal/{idSucursal}/producto/{idProducto}")
    public ResponseEntity<Inventario> getInventario(@PathVariable Long idSucursal, @PathVariable Long idProducto){
        return ResponseEntity.status(HttpStatus.OK).body(this.inventarioService.findByIdSucursalAndIdProducto(idSucursal, idProducto));
    }

}
