package com.squezada.msvc.sucursales.msvc_sucursales.controllers;


import com.squezada.msvc.sucursales.msvc_sucursales.dtos.SucursalDTO;
import com.squezada.msvc.sucursales.msvc_sucursales.models.Sucursal;
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
    SucursalService service;


    @PostMapping
    public SucursalDTO save(@RequestBody SucursalDTO dto) {
        return service.save(dto);
    }


    @GetMapping
    public List<SucursalDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDTO> findById(@PathVariable Long id) {
        SucursalDTO dto = service.findById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();

        }
    }
}
