package com.squezada.msvc.detallecompras.msvc_detallecompras.controllers;


import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.DetalledecomprasDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompras;

import com.squezada.msvc.detallecompras.msvc_detallecompras.services.DetallecomprasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detallecompras")
@Validated
public class DetallecomprasController {

    @Autowired
    private DetallecomprasService detallecomprasService;

    @GetMapping("/{id}")
    public ResponseEntity<Detallecompras> getDetallecompras(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.detallecomprasService.findById(id));
    }


    @GetMapping
    public ResponseEntity<List<Detallecompras>> getAllDetallecompras(){
        List<Detallecompras> detalles = detallecomprasService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(detalles);
    }

    @PostMapping
    public ResponseEntity<Detallecompras> save(@Valid @RequestBody Detallecompras detallecompras){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.detallecomprasService.save(detallecompras));
    }


}
