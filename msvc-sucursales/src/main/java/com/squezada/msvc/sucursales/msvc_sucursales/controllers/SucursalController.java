package com.squezada.msvc.sucursales.msvc_sucursales.controllers;


import com.squezada.msvc.sucursales.msvc_sucursales.models.entities.Sucursal;
import com.squezada.msvc.sucursales.msvc_sucursales.services.SucursalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sucursales")
@Validated
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<Sucursal>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.sucursalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.sucursalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Sucursal> save(@Valid @RequestBody Sucursal sucursal){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.sucursalService.save(sucursal));
    }
}
