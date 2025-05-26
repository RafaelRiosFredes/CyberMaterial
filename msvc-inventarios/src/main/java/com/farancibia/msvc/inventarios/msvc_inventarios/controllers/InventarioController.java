package com.farancibia.msvc.inventarios.msvc_inventarios.controllers;

import com.farancibia.msvc.inventarios.msvc_inventarios.dtos.InventarioDTO;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.entities.Inventario;
import com.farancibia.msvc.inventarios.msvc_inventarios.repositories.InventarioRepository;
import com.farancibia.msvc.inventarios.msvc_inventarios.services.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.HashAttributeSet;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/sucursales/{idSucursal}/productos/{idProducto}")
    public ResponseEntity<Inventario> getInventario(@PathVariable Long idSucursal, @PathVariable Long idProducto){
        return ResponseEntity.status(HttpStatus.OK).body(this.inventarioService.findByIdSucursalAndIdProducto(idSucursal, idProducto));
    }

    @PostMapping
    public ResponseEntity<Inventario> save(@Valid  @RequestBody Inventario inventario){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.inventarioService.save(inventario));
    }
}
